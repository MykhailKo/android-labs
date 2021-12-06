package com.example.lab1app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DataLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_log);

        Button backToMainBtn = (Button)findViewById(R.id.buttonBackToMain);

        backToMainBtn.setOnClickListener(openMainOnClick);
    }

    public void openMainActivity (View view) {
        Intent intentToOpenMainActivity = new Intent(this, MainActivity.class);
        startActivity(intentToOpenMainActivity);
    }

    View.OnClickListener openMainOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openMainActivity(v);
        }
    };
}