package com.developer.alfin.chipstock;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(version = 1, entities = {BarangKeluar.class})
public abstract class MyDatabaseKeluar extends RoomDatabase {

    private static MyDatabaseKeluar INSTANCE;

    static MyDatabaseKeluar getDatabaseKeluar(Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabaseKeluar.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, MyDatabaseKeluar.class, "FruitarianChips2_db")
                            .allowMainThreadQueries().build();
                }
            }
        }

        return INSTANCE;
    }
    public abstract RecordOutDAO recordOutDAO();
}
