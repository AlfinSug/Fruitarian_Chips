package com.developer.alfin.chipstock;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

public class ListBarangKeluarActivity extends AppCompatActivity {

    ListView listDataOut;
    List<BarangKeluar> dataListOut;
    BarangKeluarAdapter bKAdapter;
    private MyDatabaseKeluar myDatabaseKeluar;
    ImageView btnBackOut;
    BarangMasuk barangKeluar;
    private int pos1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_barang_keluar);

        btnBackOut = findViewById(R.id.backIn);

        myDatabaseKeluar = MyDatabaseKeluar.getDatabaseKeluar(getApplicationContext());

        listDataOut = findViewById(R.id.list_barang_keluar);

        getListBarangKeluar();

        btnBackOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListBarangKeluarActivity.this, DashboardActivity.class));
            }
        });

        listDataOut.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                final CharSequence[] items = {"Ubah Data", "Hapus Data"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ListBarangKeluarActivity.this);
                dialog.setTitle("Opsi");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {

                            ListBarangKeluarActivity.this.pos1 = position;
                            startActivityForResult( new Intent(ListBarangKeluarActivity.this, AddBarangOutActivity.class)
                                    .putExtra("barangkeluar", dataListOut.get(position)), 100);
                        }
                        else {

                            deletedDataBarag(id);
                        }
                    }
                });
                dialog.show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 100 && resultCode > 0) {
            if (resultCode == 1) {
                dataListOut.add((BarangKeluar) data.getSerializableExtra("barangkeluar"));
            }
            else if (resultCode == 2){
                dataListOut.set(pos1, (BarangKeluar) data.getSerializableExtra("barangkeluar"));

            }

        }
    }
    private void deletedDataBarag(long Itemid) {
        myDatabaseKeluar.recordOutDAO().deleteByIdKeluar(Itemid);
        getListBarangKeluar();
    }

    private void getListBarangKeluar() {
        dataListOut = myDatabaseKeluar.recordOutDAO().getAllBarangKeluar();
        bKAdapter = new BarangKeluarAdapter(dataListOut);
        listDataOut.setAdapter(bKAdapter);
    }
}
