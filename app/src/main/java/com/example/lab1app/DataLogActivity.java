package com.example.lab1app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DataLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_log);

        Button backToMainBtn = (Button)findViewById(R.id.buttonBackToMain);

        backToMainBtn.setOnClickListener(openMainOnClick);

        String fileData = ActionLogFileUtil.readActionLogFile(DataLogActivity.this);
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