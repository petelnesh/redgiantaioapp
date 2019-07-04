package com.keredgiantaio.techsavanna.redgiantaio.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.keredgiantaio.techsavanna.redgiantaio.R;

public class SingleSweepActivity extends AppCompatActivity {
Button startProject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_sweep);

        startProject=findViewById(R.id.btn_start);

        startProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SingleSweepActivity.this,Details1Activity.class);
                startActivity(intent);
            }
        });
    }
}
