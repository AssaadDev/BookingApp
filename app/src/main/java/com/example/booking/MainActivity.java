package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booking.db.UserDatabase;

public class MainActivity extends AppCompatActivity {

    Button regbtn,logbtn;
    EditText ml,pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regbtn = findViewById(R.id.RegBtn);
        logbtn = findViewById(R.id.logbtn);

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ml = findViewById(R.id.emailLog);
                pw = findViewById(R.id.passlog);
                String regmail = ml.getText().toString();
                String regpw = pw.getText().toString();
                boolean status = testuser(regmail,regpw);

                if(regmail.equals("admin") && regpw.equals("admin")){
                    Toast.makeText(getApplicationContext(),"Hi Admin!",Toast.LENGTH_SHORT).show();
                }else{
                    if(status==true){
                        userview();
                        cleanAll();
                    }else{
                        cleanAll();
                        Toast.makeText(getApplicationContext(),"Email or password are wrong!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regview();
            }
        });
    }

    public void regview() {
        Intent reg = new Intent(this, Register.class);
        startActivity(reg);
    }
    public void userview() {
        Intent user = new Intent(this, userdash.class);
        String mailkey = ml.getText().toString();
        user.putExtra("key", mailkey);
        startActivity(user);
    }
    public Boolean testuser(String mail, String pass){

        Boolean x = UserDatabase.getDB(this).userDao().login(mail, pass);
        return x;
    }
    public void cleanAll(){
        ml.getText().clear();
        pw.getText().clear();
    }
}
