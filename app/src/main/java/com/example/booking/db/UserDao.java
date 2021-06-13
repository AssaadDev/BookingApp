package com.example.booking.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);

    @Query("SELECT * FROM userData  WHERE email LIKE :email AND password LIKE :password")
    boolean login(String email, String password);

    @Query("SELECT * FROM userData WHERE email LIKE :email")
    boolean userCheck(String email);

    @Query("SELECT fullname FROM userData WHERE email LIKE :email")
    String giveMeName(String email);

}
