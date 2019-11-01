package com.example.myapplication.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.MainPageActivity;
import com.example.myapplication.R;

public class display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);
        String display = preferences.getString("display","");

        final Button btPlay =findViewById(R.id.btPlay);

        TextView displayInfo = findViewById(R.id.tvName);
        displayInfo.setText(display);

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent displayScreen = new Intent(display.this, MainPageActivity.class);
                startActivity(displayScreen);
            }
        });

        if(display.equals("User or password is incorrect"))
        {
            btPlay.setEnabled(false);
        }
    }


}
