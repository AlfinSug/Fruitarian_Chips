package com.developer.alfin.chipstock;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class BarangKeluar implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String kode;
    private String nama;
    private String stok;
    private String satuan;
    private String date_exp;

    public BarangKeluar() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(String date_exp) {
        this.date_exp = date_exp;
    }
}
