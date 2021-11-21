package com.example.lab1app;

import androidx.appcompat.app.AppCompatActivity;
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


//        resultsTextView = findViewById(R.id.resultsBox);
//        companiesGroup = findViewById(R.id.radioGroupCompanies);
//        productsGroup = findViewById(R.id.radioGroupProducts);
//
//        RadioButton rbCompany1 = (RadioButton)findViewById(R.id.radioButtonCompany1);
//        RadioButton rbCompany2 = (RadioButton)findViewById(R.id.radioButtonCompany2);
//        RadioButton rbCompany3 = (RadioButton)findViewById(R.id.radioButtonCompany3);
//        RadioButton rbProduct1 = (RadioButton)findViewById(R.id.radioButtonProduct1);
//        RadioButton rbProduct2 = (RadioButton)findViewById(R.id.radioButtonProduct2);
//        RadioButton rbProduct3 = (RadioButton)findViewById(R.id.radioButtonProduct3);
//        RadioButton rbProduct4 = (RadioButton)findViewById(R.id.radioButtonProduct4);
//
//        Button buttonOk = (Button)findViewById(R.id.buttonOk);
//        Button buttonCancel = (Button)findViewById(R.id.buttonCancel);
//
//        buttonCancel.setOnClickListener(cancelOnClick);
//        buttonOk.setOnClickListener(confirmOnClick);
    }

    View.OnClickListener cancelOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button)v;
            resultsTextView.setText("");
            companiesGroup.clearCheck();
            productsGroup.clearCheck();
        }
    };

    View.OnClickListener confirmOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button)v;
            CharSequence resultText;
            int chosenCompanyId = companiesGroup.getCheckedRadioButtonId();
            int chosenProductId = productsGroup.getCheckedRadioButtonId();

            if(chosenCompanyId == -1 || chosenProductId == -1) {
                resultText = "You have not chosen company or product";
            } else {
                String companyText = ((TextView) findViewById(chosenCompanyId)).getText().toString();
                String productText = ((TextView) findViewById(chosenProductId)).getText().toString();
                resultText = (CharSequence)("You have chosen " + companyText + " " + productText);
            }
            resultsTextView.setText(resultText);
        }
    };
}