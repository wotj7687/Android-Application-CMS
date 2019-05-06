package com.example.wotj7.a0128;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    String password = "pw";
    EditText editText;
    Button button;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText = findViewById(R.id.password);
        button = findViewById(R.id.loginbutton);


    }

    public void ForLogin(View view) {
        if(password.equals(editText.getText().toString()) ){
            Toast.makeText(this, "login success",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "u can't login", Toast.LENGTH_SHORT).show();
        }

    }

}
