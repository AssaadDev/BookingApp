package com.example.booking.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userData")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "fullname")
    private String full_name;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "phone")
    private int phone;
    @ColumnInfo(name = "password")
    private String password;


    public User(String full_name, String email,int phone, String password) {
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;   ///////////////////////// Popravi telefon
        this.password = password;

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
