package com.peoplestrong.mvvmdemo.database.db;

import android.content.Context;
import android.os.Build;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.peoplestrong.mvvmdemo.database.mylibrary.MyLibrary;
import com.peoplestrong.mvvmdemo.database.mylibrary.MyLibraryDao;

@Database(entities = {MyLibrary.class},version =1,exportSchema = false)
public abstract class LeaerAltDatabase extends RoomDatabase {
    private static LeaerAltDatabase instance;

    public static LeaerAltDatabase getInstanceDataBase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LeaerAltDatabase.class, "learnalt_db")
                    .build();

        }
        return instance;
    }
    public static void closeDatabase() {
        instance = null;
    }
    public abstract MyLibraryDao myLibraryDao();

}
