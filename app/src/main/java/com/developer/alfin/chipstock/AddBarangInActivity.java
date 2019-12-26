package com.developer.alfin.chipstock;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
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

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;

public class AddBarangInActivity extends AppCompatActivity {

    ImageView imgChoose, btnBackIn;
    ImageButton setTgl;
    TextView txTitle;
    EditText edtKodeIn, edtNamaIn, edtStockIn, edtSatuanIn, edtTglExp;
    Button btnSaveIn;
    Bitmap bitmap;
    List<BarangMasuk> listMasuk;
    BarangMasuk barangMasuk;
    int year, month, day;
    boolean update;
    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barang_in);

        db = MyDatabase.getDatabase(getApplicationContext());


//        imgChoose = findViewById(R.id.choose_img);
        btnBackIn = findViewById(R.id.backIn);
        txTitle = findViewById(R.id.tv_titleAdd);
        setTgl = findViewById(R.id.btn_set_calender);
        edtKodeIn = findViewById(R.id.kode_barang);
        edtNamaIn = findViewById(R.id.nama_barang);
        edtStockIn = findViewById(R.id.stok_barang);
        edtSatuanIn = findViewById(R.id.satuan_barang);
        edtTglExp = findViewById(R.id.tgl_exp_edt);
        btnSaveIn = findViewById(R.id.btn_addIn);

        if ((barangMasuk = (BarangMasuk)  getIntent().getSerializableExtra("barangmasuk")) != null) {
            update = true;
            btnSaveIn.setText("Ubah");
            txTitle.setText("Ubah Data Barang Masuk");
            edtKodeIn.setText(barangMasuk.getKode());
            edtNamaIn.setText(barangMasuk.getNama());
            edtStockIn.setText(barangMasuk.getStok());
            edtSatuanIn.setText(barangMasuk.getSatuan());
            edtTglExp.setText(barangMasuk.getDate_exp());
        }


        btnBackIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddBarangInActivity.this, DashboardActivity.class));
            }
        });

        btnSaveIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (update) {

                    setUpdatedata();
                } else {

                    saveDataIn();
                }

            }
        });

        setTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTanggalExpIn();
            }
        });


//        imgChoose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGalery();
//            }
//        });

    }

    private void setUpdatedata() {

        BarangMasuk barangMasuk = new BarangMasuk();
        String kode = edtKodeIn.getText().toString();
        String nama = edtNamaIn.getText().toString();
        String stok = edtStockIn.getText().toString();
        String satuan = edtSatuanIn.getText().toString();
        String dateExp = edtTglExp.getText().toString();

        barangMasuk.setKode(kode);
        barangMasuk.setNama(nama);
        barangMasuk.setStok(stok);
        barangMasuk.setSatuan(satuan);
        barangMasuk.setDate_exp(dateExp);

        db.recordDAO().updateBarang(barangMasuk);

        Toast.makeText(AddBarangInActivity.this, "Barang Masuk telah diubah :)", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddBarangInActivity.this, ListBarangMasukActivity.class));
        finish();

        setResult(barangMasuk, 2);

    }

    private void saveDataIn() {
        BarangMasuk barangMasuk = new BarangMasuk();
        barangMasuk.setKode(edtKodeIn.getText().toString());
        barangMasuk.setNama(edtNamaIn.getText().toString());
        barangMasuk.setStok(edtStockIn.getText().toString());
        barangMasuk.setSatuan(edtSatuanIn.getText().toString());
        barangMasuk.setDate_exp(edtTglExp.getText().toString());

        db.recordDAO().insertBarang(barangMasuk);

        Toast.makeText(AddBarangInActivity.this, "Barang Masuk telah ditambahkan :)", Toast.LENGTH_SHORT).show();
        finish();

    }
    private void setResult(BarangMasuk barangMasuk, int flag) {
        setResult(flag, new Intent().putExtra("barangmasuk", barangMasuk ));
        finish();
    }

    public void getTanggalExpIn() {
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


    private void openGalery() {
        Intent opengallery = new Intent(Intent.ACTION_GET_CONTENT);
        opengallery.setType("image/*");
        startActivityForResult(opengallery, 192);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 192 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(uri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            if ( bitmap != null && !bitmap.isRecycled()) {
                bitmap = null;
            }

            bitmap = BitmapFactory.decodeFile(filePath);
            imgChoose.setBackgroundResource(0);
            imgChoose.setImageBitmap(bitmap);



        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
