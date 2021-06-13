package com.example.booking;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.booking.db.Book;
import com.example.booking.db.UserDatabase;

import java.util.List;

public class mybook extends AppCompatActivity {

    private ListView bookviev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybook);

        ActionBar action = getSupportActionBar();
        if (action != null) {
            action.setDisplayHomeAsUpEnabled(true);
        }

        bookviev = findViewById(R.id.listingdata);
        setList();

        bookviev.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long bookid = parent.getItemIdAtPosition(position);
                //Toast.makeText(getApplicationContext(),"my id is "+ (int) bookid,Toast.LENGTH_SHORT).show();
                deletebox(bookid);
            }
        });
    }
    private void deletebox(long idbook){
        final int bookid = (int) idbook;
       // deleteBook box = new deleteBook();
        String titl = getservice(bookid);
        final String day = getday(bookid);

        AlertDialog.Builder box = new AlertDialog.Builder(mybook.this);
        box.setTitle("Do you want to cancle "+titl+"?").setMessage("Are u sure u want to cancle your apointment on "+day+"?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete(bookid);
                Toast.makeText(getApplicationContext(),"Your apointment is cancled on "+day,Toast.LENGTH_LONG).show();
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        box.create();
        box.show();

       // Bundle args = new Bundle();
       // args.putInt("number", bookid);//The first parameter is the key that will be used to retrieve the value, which is the second parameter.
       // args.putString("title", titl);
       // args.putString("day", day);
       // box.setArguments(args);

       // box.show(getSupportFragmentManager(),"zzz");
    }

    private String getday(int bookid) {
        String day = UserDatabase.getDB(this).bookDao().getDay(bookid);
        return day;
    }

    private String getservice(int idserv) {
        String title = UserDatabase.getDB(this).bookDao().getTitle(idserv);
        return title;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


   /* public void deleteid(View view){
        ImageView dlt = findViewById(R.id.dltme);
        int x = (int) dlt.getTag();
        Toast.makeText(getApplicationContext(), x ,Toast.LENGTH_SHORT).show();
    }*/

    private void delete(int id){
        UserDatabase.getDB(this).bookDao().deletebook(id);
    }

    private void setList(){
        Intent intent = getIntent();
        String x = intent.getStringExtra("keyxy");

        List<Book> book = UserDatabase.getDB(this).bookDao().getBookings(x);
       // int s = UserDatabase.getDB(this).bookDao().getRowCount(x);
       // Toast.makeText(getApplicationContext(),"rows are "+s,Toast.LENGTH_SHORT).show();
        userListAdapter adapter = new userListAdapter(this, book);
        bookviev.setAdapter(adapter);
    }



}
