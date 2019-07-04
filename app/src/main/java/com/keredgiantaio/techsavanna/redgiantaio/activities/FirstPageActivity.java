package com.keredgiantaio.techsavanna.redgiantaio.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.keredgiantaio.techsavanna.redgiantaio.R;

public class FirstPageActivity extends AppCompatActivity {

    Button loginbtn,regbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        loginbtn=findViewById(R.id.btn_login);
        regbtn=findViewById(R.id.btn_register);


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstPageActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstPageActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
