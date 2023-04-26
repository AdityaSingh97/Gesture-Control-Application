package com.example.mobass2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ThirdScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen2);

        findViewById(R.id.Record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
                intent.putExtra("android.intent.extras.CAMERA-FACING", 1);
                startActivityForResult(intent,1);
            }
        });

        findViewById(R.id.upload1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ThirdScreen.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            try {

                File newfile;

                AssetFileDescriptor videoAsset = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
                FileInputStream in = videoAsset.createInputStream();

                File filepath = Environment.getExternalStorageDirectory();
                File dir = new File(filepath.getAbsolutePath() + "/" + "Smarthome" + "/");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                newfile = new File(dir, "save_" + System.currentTimeMillis() + ".mp4");

                if (newfile.exists()) newfile.delete();


                OutputStream out = new FileOutputStream(newfile);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                in.close();
                out.close();

                Log.v("", "Copy file successful.");


                // videoUri = data.getData();
                // videoview.setVideoURI(videoUri);
                // videoview.start();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                VideoView videoView = new VideoView(this);
                videoView.setVideoURI(data.getData());
                videoView.start();
                // saveVideoToInternalStorage();
                builder.setView(videoView).show();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
          // AlertDialog.Builder builder = new AlertDialog.Builder(this);
          // VideoView videoView = new VideoView(this);
           //videoView.setVideoURI(data.getData());
          // videoView.start();
           // saveVideoToInternalStorage();
          // builder.setView(videoView).show();

    private void saveVideoToInternalStorage () {

        File newfile;
        String filePath = "DCIM/";
        try {

            File currentFile = new File(filePath);
            String fileName = "video1";

            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("videoDir", Context.MODE_PRIVATE);


            newfile = new File(directory, fileName);

            if(currentFile.exists()){

                InputStream in = new FileInputStream(currentFile);
                OutputStream out = new FileOutputStream(newfile);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                in.close();
                out.close();

                Log.v("", "Video file saved successfully.");

            }else{
                Log.v("", "Video saving failed. Source file missing.");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}