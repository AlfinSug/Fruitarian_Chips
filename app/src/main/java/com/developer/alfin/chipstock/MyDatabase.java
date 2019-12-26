package com.developer.alfin.chipstock;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(version = 1, entities = {BarangMasuk.class})
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase INSTANCE;

    static MyDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, MyDatabase.class, "FruitarianChips_db")
                            .allowMainThreadQueries().build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract RecordDAO recordDAO();


}
