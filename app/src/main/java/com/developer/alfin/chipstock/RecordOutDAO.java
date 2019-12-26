package com.developer.alfin.chipstock;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface RecordOutDAO {

    // interface Barang Keluar
    @Insert
    long inserBarangKeluat(BarangKeluar barangKeluar);

    @Query("DELETE FROM BarangKeluar WHERE id = :id")
    int deleteByIdKeluar(long id);

    @Query("SELECT * FROM BarangKeluar ORDER BY date_exp ASC")
    List<BarangKeluar> getAllBarangKeluar();

    @Update
    void updateBarangKeluar(BarangKeluar barangKeluar);

}
