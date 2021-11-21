package com.example.lab1app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class OutputFragment extends Fragment {

    private String resultText = "Your results will be displayed here";
    private TextView resultView;

    public OutputFragment() {
        super(R.layout.fragment_output);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInsatanceState) {
        resultText = requireArguments().getString("result");
        resultView = getView().findViewById(R.id.resultsBox);
        resultView.setText(resultText);

        Button buttonCancel = getView().findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener(cancelOnClick);
    }

    View.OnClickListener cancelOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            resultView.setText("Your results will be displayed here");
            getParentFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_input_container, InputFragment.class, null)
                    .commit();
            getParentFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .detach(OutputFragment.this)
                    .commit();
        }
    };
}

