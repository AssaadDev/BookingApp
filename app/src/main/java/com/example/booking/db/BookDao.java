package com.example.booking.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertBook(Book book);

    @Query("SELECT service FROM bookData WHERE id = :id")
    String getTitle(int id);

    @Query("SELECT date FROM bookData WHERE id = :id")
    String getDay(int id);

    @Query("SELECT * FROM bookData WHERE user = :user ORDER BY id DESC")
    List<Book> getBookings(String user);

    @Query("SELECT COUNT(*) FROM bookData WHERE user = :user")
    int getRowCount(String user);

    @Query("DELETE FROM bookData WHERE id = :id")
    void deletebook(int id);

}
