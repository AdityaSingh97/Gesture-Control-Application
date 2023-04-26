package com.example.mobass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class SecondScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen2);
        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        Toast.makeText(this,String.valueOf(pos), Toast.LENGTH_SHORT).show();
        String videopath;
        videopath = "android.resource://com.example.mobass2/" + R.raw.decrease;
        if(pos == 0){
            videopath = "android.resource://com.example.mobass2/" + R.raw.lightson;
        }else if(pos == 1){
            videopath = "android.resource://com.example.mobass2/" + R.raw.lightsoff;
        }else if(pos == 2){
            videopath = "android.resource://com.example.mobass2/" + R.raw.fanon;
        }else if(pos == 3){
            videopath = "android.resource://com.example.mobass2/" + R.raw.fanoff;
        }else if(pos == 4){
            videopath = "android.resource://com.example.mobass2/" + R.raw.increasefanspeed;
        }else if(pos == 5){
            videopath = "android.resource://com.example.mobass2/" + R.raw.decrease;
        }else if(pos == 6){
            videopath = "android.resource://com.example.mobass2/" + R.raw.setthermo;
        }else if(pos == 7){
            videopath = "android.resource://com.example.mobass2/" + R.raw.zero;
        }else if(pos == 8){
            videopath = "android.resource://com.example.mobass2/" + R.raw.one;
        }else if(pos == 9){
            videopath = "android.resource://com.example.mobass2/" + R.raw.two;
        }else if(pos == 10){
            videopath = "android.resource://com.example.mobass2/" + R.raw.three;
        }else if(pos == 11){
            videopath = "android.resource://com.example.mobass2/" + R.raw.four;
        }else if(pos == 12){
            videopath = "android.resource://com.example.mobass2/" + R.raw.five;
        }else if(pos == 13){
            videopath = "android.resource://com.example.mobass2/" + R.raw.six;
        }else if(pos == 14){
            videopath = "android.resource://com.example.mobass2/" + R.raw.seven;
        }else if(pos == 15){
            videopath = "android.resource://com.example.mobass2/" + R.raw.eight;
        }else if(pos == 16){
            videopath = "android.resource://com.example.mobass2/" + R.raw.nine;
        }


       // String videopath = "android.resource://com.example.mobass2/" + R.raw.decreasefanspeed;
        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        //videoView.setVideoPath("android.resource://" + getPackageName() + "/" +R.raw.DecreaseFanSpeed);
        videoView.start();

        Button practice = (Button) findViewById(R.id.Practice);
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SecondScreen.this, ThirdScreen.class);
                startActivity(intent2);
            }
        });
    }


}