package com.example.lab1app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TextView resultsTextView;
    private RadioGroup companiesGroup;
    private RadioGroup productsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_input_container, InputFragment.class, null)
                    .commit();
        }
        setContentView(R.layout.activity_main);

        Button buttonDataLog = (Button)findViewById(R.id.buttonData);

        buttonDataLog.setOnClickListener(openDataLogOnClick);
    }

    public void openDataLogActivity(View view) {
        Intent intentToOpenDataLogActivity = new Intent(this, DataLogActivity.class);
        startActivity(intentToOpenDataLogActivity);
    }

    View.OnClickListener openDataLogOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openDataLogActivity(v);
        }
    };
}