package com.developer.alfin.chipstock;

import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class DashboardActivity extends AppCompatActivity {

    CardView viewOut, viewIn, addStock;
    private BottomSheetBehavior<LinearLayout> sheetBehavior;
    Button opsiBarangMasuk, opsiBarangKeluar;
    LinearLayout layoutSheetOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        viewIn = findViewById(R.id.btn_viewIn);
        viewOut = findViewById(R.id.btn_viewOut);
        addStock = findViewById(R.id.btn_addStock);
        layoutSheetOption = findViewById(R.id.sheet_option);
        opsiBarangMasuk = findViewById(R.id.btn_opsiIn);
        opsiBarangKeluar = findViewById(R.id.btn_opsiOut);

        chooseOption();

        viewIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ListBarangMasukActivity.class));
            }
        });

        viewOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ListBarangKeluarActivity.class));
            }
        });

    }

    private void chooseOption() {

        sheetBehavior = BottomSheetBehavior.from(layoutSheetOption);

        addStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                opsiBarangMasuk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(DashboardActivity.this, AddBarangInActivity.class));
                    }
                });

                opsiBarangKeluar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(DashboardActivity.this, AddBarangOutActivity.class));
                    }
                });
            }
        });
    }
}
