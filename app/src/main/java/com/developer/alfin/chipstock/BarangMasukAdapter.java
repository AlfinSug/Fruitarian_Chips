package com.developer.alfin.chipstock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BarangMasukAdapter extends BaseAdapter {

    private List<BarangMasuk> masukList;

    public BarangMasukAdapter(List<BarangMasuk> masukList) {
        this.masukList = masukList;
    }

    @Override
    public int getCount() {
        return masukList.size();
    }

    @Override
    public Object getItem(int position) {
        return masukList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return masukList.get(position).getId();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imgIn;
        TextView tvKode, tvNama, tvStok, tvSatuan, tvTglExp;


            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_barang_masuk, null, true);

        final BarangMasuk bMasuk = masukList.get(position);

            tvKode = convertView.findViewById(R.id.tv_kode_in);
            tvNama = convertView.findViewById(R.id.tv_nama_in);
            tvStok = convertView.findViewById(R.id.tv_stok_in);
            tvSatuan = convertView.findViewById(R.id.tv_satuan_in);
            tvTglExp = convertView.findViewById(R.id.tv_tglexp_in);
//            imgIn = convertView.findViewById(R.id.img_dataIn);

        tvKode.setText(bMasuk.getKode());
        tvNama.setText(bMasuk.getNama());
        tvStok.setText(bMasuk.getStok());
        tvSatuan.setText(bMasuk.getSatuan());
        tvTglExp.setText(bMasuk.getDate_exp());

        return convertView;
    }
}
