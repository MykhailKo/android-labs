package com.example.lab1app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class InputFragment extends Fragment {

    private RadioGroup companiesGroup;
    private RadioGroup productsGroup;

    public InputFragment() {
        super(R.layout.fragment_input);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInsatanceState) {
        companiesGroup = getView().findViewById(R.id.radioGroupCompanies);
        productsGroup = getView().findViewById(R.id.radioGroupProducts);

        companiesGroup.clearCheck();
        productsGroup.clearCheck();

        Button buttonOk = getView().findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(confirmOnClick);
    }

    View.OnClickListener confirmOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            CharSequence resultText;
            int chosenCompanyId = companiesGroup.getCheckedRadioButtonId();
            int chosenProductId = productsGroup.getCheckedRadioButtonId();

            if(chosenCompanyId == -1 || chosenProductId == -1) {
                resultText = "You have not chosen company or product";
            } else {
                String companyText = ((TextView) getView().findViewById(chosenCompanyId)).getText().toString();
                String productText = ((TextView) getView().findViewById(chosenProductId)).getText().toString();
                resultText = (CharSequence)("You have chosen " + companyText + " " + productText);
            }

            Bundle outputCtx = new Bundle();
            outputCtx.putString("result", (String) resultText);

            getParentFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_output_container, OutputFragment.class, outputCtx)
                .commit();
        }
    };
}
