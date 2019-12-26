package com.developer.alfin.chipstock;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddBarangOutActivity extends AppCompatActivity {

    ImageView imgChoose, btnBackOut;
    ImageButton setTgl;
    EditText edtKodeout, edtNamaout, edtStockout, edtSatuanout, edtTglExp;
    Button btnSaveOut;
    BarangKeluar bKeluar;
    boolean update;
    TextView txTitle;
    private MyDatabaseKeluar db;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barang_out);

        db = MyDatabaseKeluar.getDatabaseKeluar(getApplicationContext());

        imgChoose = findViewById(R.id.choose_img);
        btnBackOut = findViewById(R.id.backIn);
        txTitle = findViewById(R.id.tv_titleAdd);
        setTgl = findViewById(R.id.btn_set_calender_out);
        edtKodeout = findViewById(R.id.kode_barang_out);
        edtNamaout = findViewById(R.id.nama_barang_out);
        edtStockout = findViewById(R.id.stok_barang_out);
        edtSatuanout = findViewById(R.id.satuan_barang_out);
        edtTglExp = findViewById(R.id.tgl_exp_edt_out);
        btnSaveOut = findViewById(R.id.btn_addOut);
        txTitle = findViewById(R.id.tv_titleAdd);

        btnBackOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddBarangOutActivity.this, DashboardActivity.class));
            }
        });

        btnSaveOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (update) {
                    setUpdateBarangKeluar();
                }
                else {
                    savaDataOut();
                }
            }
        });

        setTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTglBarangKeluar();
            }
        });

        if ((bKeluar = (BarangKeluar)  getIntent().getSerializableExtra("barangkeluar")) != null) {
            update = true;
            btnSaveOut.setText("Ubah");
            txTitle.setText("Ubah Data Barang Keluar");
            edtKodeout.setText(bKeluar.getKode());
            edtNamaout.setText(bKeluar.getNama());
            edtStockout.setText(bKeluar.getStok());
            edtSatuanout.setText(bKeluar.getSatuan());
            edtTglExp.setText(bKeluar.getDate_exp());
        }

    }

    private void setTglBarangKeluar() {

        final Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtTglExp.setText(dayOfMonth + "-" + month + "-" + year);
            }
        }, year, month, day);

        pickerDialog.show();
    }

    private void savaDataOut() {

        String kode = edtKodeout.getText().toString();
        String nama = edtNamaout.getText().toString();
        String stok = edtStockout.getText().toString();
        String satuan = edtSatuanout.getText().toString();
        String tglexp = edtTglExp.getText().toString();

        BarangKeluar barangKeluar = new BarangKeluar();
        barangKeluar.setKode(kode);
        barangKeluar.setNama(nama);
        barangKeluar.setStok(stok);
        barangKeluar.setSatuan(satuan);
        barangKeluar.setDate_exp(tglexp);

        db.recordOutDAO().inserBarangKeluat(barangKeluar);

        Toast.makeText(AddBarangOutActivity.this, "Barang Keluar telah ditambahkan :)", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void setUpdateBarangKeluar() {

        String kode = edtKodeout.getText().toString();
        String nama = edtNamaout.getText().toString();
        String stok = edtStockout.getText().toString();
        String satuan = edtSatuanout.getText().toString();
        String tglexp = edtTglExp.getText().toString();

        BarangKeluar barangKeluar = new BarangKeluar();
        barangKeluar.setKode(kode);
        barangKeluar.setNama(nama);
        barangKeluar.setStok(stok);
        barangKeluar.setSatuan(satuan);
        barangKeluar.setDate_exp(tglexp);

        db.recordOutDAO().updateBarangKeluar(barangKeluar);

        Toast.makeText(AddBarangOutActivity.this, "Barang Keluar berhasil diubah :)", Toast.LENGTH_SHORT).show();
        finish();
    }
}
