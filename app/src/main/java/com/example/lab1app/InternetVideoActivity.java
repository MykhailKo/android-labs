package com.example.lab1app;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class InternetVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_video);
        VideoView videoView = findViewById(R.id.videoview);
        Bundle extras = getIntent().getExtras();
        Uri uri = Uri.parse(extras.getString("url"));
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);

        videoView.setMediaController(mediaController);
        videoView.start();
    }
}