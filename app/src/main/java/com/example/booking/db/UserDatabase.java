package com.example.booking.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Book.class}, version = 2 ,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase INSTANCE =null;

    public abstract UserDao userDao();
    public abstract BookDao bookDao();

    public static UserDatabase getDB(Context context){
        if(INSTANCE== null){
            INSTANCE = Room.databaseBuilder(context,UserDatabase.class, "userDatabase").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }


}
