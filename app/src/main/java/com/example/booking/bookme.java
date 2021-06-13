package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.booking.db.Book;
import com.example.booking.db.UserDatabase;


public class bookme extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookme);

        final Intent intent = getIntent();


        final DatePicker mydate = (DatePicker) findViewById(R.id.date);
        final Spinner time = findViewById(R.id.time);
        final Spinner service = findViewById(R.id.service);

        Button close = findViewById(R.id.close),
                done = findViewById(R.id.booknow);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bck();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = mydate.getDayOfMonth();
                int month = mydate.getMonth();
                String montString = generateMonth(month);
                String date = montString+". "+day+"."; // datum
                String houers =  time.getSelectedItem().toString(); // vrijeme
                String typeserv = service.getSelectedItem().toString(); //  usluga
                String user = intent.getStringExtra("keyx");  // userkey

                if(houers.equals("Time") || typeserv.equals("Type of service")){
                    Toast.makeText(getApplicationContext(),"All fields are needed!!!",Toast.LENGTH_SHORT).show();
                }else{
                    insert(user,date,houers,typeserv);
                }


            }
        });
    }

    private void insert(String user,String bookdate, String bookhouer, String bookservice){
        Book book = new Book(user,bookdate,bookhouer,bookservice);
        UserDatabase.getDB(this).bookDao().insertBook(book);
        Toast.makeText(getApplicationContext(),"See ya soon! ",Toast.LENGTH_SHORT).show();
        finish();
    }

    /*private String getUser(String key){
        String x = UserDatabase.getDB(this).userDao().getUser(key);
        return x;
    }*/


    public void bck(){
        finish();
    }

    public String generateMonth(int month){
        String monthString=null;
        switch(month) {
            case 0:
                monthString = "Jan";
                break;
            case 1:
                monthString = "Feb";
                break;
            case 2:
                monthString = "Mar";
                break;
            case 3:
                monthString = "Apr";
                break;
            case 4:
                monthString = "May";
                break;
            case 5:
                monthString = "Jun";
                break;
            case 6:
                monthString = "Jul";
                break;
            case 7:
                monthString = "Aug";
                break;
            case 8:
                monthString = "Sept";
                break;
            case 9:
                monthString = "Oct";
                break;
            case 10:
                monthString = "Nov";
                break;
            case 11:
                monthString = "Dec";
                break;
            default:
                monthString = "error";
        }
        return monthString;
    }


}
