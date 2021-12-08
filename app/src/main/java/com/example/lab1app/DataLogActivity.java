package com.example.lab1app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.util.StringUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DataLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_log);

        Button backToMainBtn = (Button)findViewById(R.id.buttonBackToMain);

        backToMainBtn.setOnClickListener(openMainOnClick);

        String fileData = readActionLogFile();
        TextView fileDataView = findViewById(R.id.DataFS);
        fileDataView.setText(fileData);

        String dbData = readActionLogDB();
        TextView dbDataView = findViewById(R.id.DataDB);
        dbDataView.setText(dbData);
    }

    private String readActionLogDB() {
        Context appCtx = getApplicationContext();
        AppDataBase db = Room.databaseBuilder(appCtx,
                AppDataBase.class, "applogdb").allowMainThreadQueries().build();

        UserActionLogDao actionDao = db.userActionDao();
        List<UserActionEntity> actionRows = actionDao.getAll();
        StringBuilder sb = new StringBuilder();
        for(UserActionEntity row : actionRows) {
            sb.append(row.toString()).append(",\n");
        }
        String content = sb.toString();
        return content;
    }

    private String readActionLogFile() {
        String filename = "user-action.log";
        Context context = DataLogActivity.this;
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = context.openFileInput(filename)) {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                sb.insert(0, "\n").insert(0,line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            String contents = sb.toString();
            return contents;
        }
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