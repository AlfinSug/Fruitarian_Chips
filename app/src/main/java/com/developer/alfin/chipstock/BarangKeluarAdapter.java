package com.developer.alfin.chipstock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BarangKeluarAdapter extends BaseAdapter {

    private List<BarangKeluar> keluarList;

    public BarangKeluarAdapter(List<BarangKeluar> keluarList) {
        this.keluarList = keluarList;
    }

    @Override
    public int getCount() {
        return keluarList.size();
    }

    @Override
    public Object getItem(int position) {
        return keluarList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return keluarList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imgout;
        TextView tvKode, tvNama, tvStok, tvSatuan, tvTglExp;


        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(R.layout.item_barang_keluar, null, true);

        final BarangKeluar bKeluar = keluarList.get(position);

        tvKode = convertView.findViewById(R.id.tv_kode_out);
        tvNama = convertView.findViewById(R.id.tv_nama_out);
        tvStok = convertView.findViewById(R.id.tv_stok_out);
        tvSatuan = convertView.findViewById(R.id.tv_satuan_out);
        tvTglExp = convertView.findViewById(R.id.tv_tglexp_out);

//        byte[] imgDatain = bMasuk.getTgl_exp();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(imgDatain, 0, imgDatain.length);
//        viewHolder.imgIn.setImageBitmap(bitmap);

        tvKode.setText(bKeluar.getKode());
        tvNama.setText(bKeluar.getNama());
        tvStok.setText(bKeluar.getStok());
        tvSatuan.setText(bKeluar.getSatuan());
        tvTglExp.setText(bKeluar.getDate_exp());
        return convertView;
    }
}
