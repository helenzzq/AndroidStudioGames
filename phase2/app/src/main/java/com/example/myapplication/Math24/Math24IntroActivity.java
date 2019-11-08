package com.example.myapplication.Math24;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.TextView;

public class Math24Intro extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private TextView introTitle, introBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24_intro);

        introTitle = findViewById(R.id.math24intro);
        introBody = findViewById(R.id.introBody);
        back = findViewById(R.id.btn_back2);
        back.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back2:
                Intent back = new Intent(Math24Intro.this, Math24Activity.class);
                startActivity(back);
                break;
        }
    }
}