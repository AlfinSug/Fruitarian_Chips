package com.developer.alfin.chipstock;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface RecordDAO {

    @Insert
    long insertBarang(BarangMasuk bmRecord);

    @Query("DELETE FROM BarangMasuk WHERE id = :id")
    int deleteById(long id);

    @Query("SELECT * FROM BarangMasuk ORDER BY date_exp ASC")
    List<BarangMasuk> getAll();

    @Update
    void updateBarang(BarangMasuk upBarang);

    @Query("UPDATE BarangMasuk SET kode = :kodein, nama = :namain, stok = :stokin, satuan = :satuanin, date_exp = :date_exp_in WHERE id = :id")
    int UpdateById(String kodein, String namain, String stokin, String satuanin, String date_exp_in, long id);





}
