package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.booking.db.User;
import com.example.booking.db.UserDatabase;

public class Register extends AppCompatActivity {
    private Button btnreg;
    EditText name,mail,phone,pw,cpw;
    RadioButton male,female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnreg = (Button) findViewById(R.id.regme);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


    }
    public void register(){

        name = findViewById(R.id.fullname);
        String fname = name.getText().toString();

        mail = findViewById(R.id.regmail);
        String email = mail.getText().toString();

        phone = (EditText)findViewById(R.id.regphone);
        String val = phone.getText().toString();
        int phonenumb = 0;
        if(!val.isEmpty()){
            phonenumb = Integer.parseInt(val);         // popravi!!!!!!!
        }


        pw = findViewById(R.id.regpw);
        String pass = pw.getText().toString();

        cpw = findViewById(R.id.cregpw);
        String cpass = cpw.getText().toString();

        boolean test = chck(email);
        if(fname.isEmpty() || email.isEmpty() || pass.isEmpty() || cpass.isEmpty() || val.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill all inputs!",Toast.LENGTH_SHORT).show();
        }else{
            if(!test){
                if(cpass.equals(pass)){
                    User user = new User(fname,email,phonenumb,pass);
                    UserDatabase.getDB(this).userDao().insertUser(user);
                    Toast.makeText(getApplicationContext(),"You are registered successfully!",Toast.LENGTH_SHORT).show();
                    goback();
                }else {
                    Toast.makeText(getApplicationContext(),"Your password doesnt match!",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(),"That email is allready in use!",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void goback(){
        finish();
    }
    public boolean chck(String mail){
        Boolean x = UserDatabase.getDB(this).userDao().userCheck(mail);
        return x;
    }

}
