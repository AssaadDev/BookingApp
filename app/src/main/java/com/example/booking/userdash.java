package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.booking.db.UserDatabase;

public class userdash extends AppCompatActivity {

    Button btnMyBook,logout,booking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdash);
        TextView helloname = (TextView) findViewById(R.id.username);

        Intent intent = getIntent();
        String mailkey = intent.getStringExtra("key");

        String name = myname(mailkey);
        helloname.setText("Hello "+ name);

        btnMyBook = findViewById(R.id.mybk);
        logout = findViewById(R.id.logout);
        booking = findViewById(R.id.bkme);

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbooking();
            }
        });
        btnMyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmybook();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lgout();
            }
        });
    }
    public void openbooking(){
        Intent intent = getIntent();
        String x = intent.getStringExtra("key");
        Intent booking =new Intent(this, bookme.class);
        booking.putExtra("keyx", x);
        startActivity(booking);
    }
    public void openmybook(){
        Intent intent = getIntent();
        String x = intent.getStringExtra("key");
        Intent mybook = new Intent(this, mybook.class);
        mybook.putExtra("keyxy", x);
        startActivity(mybook);
    }
    public void lgout(){
        finish();
     }

    public String myname(String mail){
        String x = UserDatabase.getDB(this).userDao().giveMeName(mail);
        return x;
    }
}
