package com.example.lab1app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoPlayer;
    Button playButton;
    Button pauseButton;
    Button stopButton;
    Button internetVideoButton;
    Button choseVideoFileButton;
    TextView internetUrlInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoPlayer = findViewById(R.id.videoPlayer);

        MediaController mediaController = new MediaController(this);
        videoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoPlayer);

        internetUrlInput = findViewById(R.id.internetUrlInput);

        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);
        internetVideoButton = findViewById(R.id.internetButton);
        choseVideoFileButton = findViewById(R.id.fileButton);

        playButton.setOnClickListener(playVideoOnClick);
        pauseButton.setOnClickListener(pauseVideoOnClick);
        stopButton.setOnClickListener(stopVideoOnClick);
        choseVideoFileButton.setOnClickListener(openVideoFileOnClick);
        internetVideoButton.setOnClickListener(openInternetVideoUrlOnClick);

    }

    View.OnClickListener playVideoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            videoPlayer.start();
        }
    };

    View.OnClickListener pauseVideoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            videoPlayer.pause();
        }
    };

    View.OnClickListener stopVideoOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            videoPlayer.stopPlayback();
            videoPlayer.resume();
        }
    };

    private void openVideoFromInternet() {
        String url = internetUrlInput.getText().toString();
        System.out.println(url);
        Intent openInternetVideoIntent = new Intent(this, InternetVideoActivity.class);
        openInternetVideoIntent.putExtra("url", url);
        startActivity(openInternetVideoIntent);
    }

    View.OnClickListener openInternetVideoUrlOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openVideoFromInternet();
        }
    };

    View.OnClickListener openVideoFileOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            startActivityForResult(intent, requestcode);
        }
    };

    int requestcode = 1;

    public void onActivityResult(int requestcode, int resultCode, Intent data){
        super.onActivityResult(requestcode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if (data == null){
                return;
            }
            Uri uri = data.getData();
            videoPlayer.setVideoURI(uri);
        }
    }
}