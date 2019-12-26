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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListBarangMasukActivity extends AppCompatActivity {

    ListView listDataIn;
    List<BarangMasuk> dataListIn;
    BarangMasukAdapter bmAdapter;
    private MyDatabase myDatabase;
    private MyDatabaseKeluar myDatabaseKeluar;
    ImageView btnBackIn;
    BarangMasuk barangMasuk;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_barang_masuk);

        btnBackIn = findViewById(R.id.backIn);

        myDatabase = MyDatabase.getDatabase(getApplicationContext());

        listDataIn = findViewById(R.id.list_barang_masuk);

        getListBarang();

        btnBackIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListBarangMasukActivity.this, DashboardActivity.class));
            }
        });

        listDataIn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {

                final CharSequence[] items = {"Ubah Data", "Hapus Data"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ListBarangMasukActivity.this);
                dialog.setTitle("Opsi");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {

                            ListBarangMasukActivity.this.pos = position;
                            startActivityForResult( new Intent(ListBarangMasukActivity.this, AddBarangInActivity.class)
                            .putExtra("barangmasuk", dataListIn.get(position)), 100);
                        }
                        else {

                           deletedData(id);
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
                dataListIn.add((BarangMasuk) data.getSerializableExtra("barangmasuk"));
            }
               else if (resultCode == 2){
                   dataListIn.set(pos, (BarangMasuk) data.getSerializableExtra("barangmasuk"));

            }
            
        }
    }

    private void deletedData(final long Itemid) {

        myDatabase.recordDAO().deleteById(Itemid);
        getListBarang();

    }

    private void getListBarang() {
        dataListIn = myDatabase.recordDAO().getAll();
        bmAdapter = new BarangMasukAdapter(dataListIn);
        listDataIn.setAdapter(bmAdapter);
    }
}
