package com.example.saelyne.fasttp2_camera;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE=1;
    Button btn;
    VideoView myVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.start_record);
        btn.setOnClickListener (new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startCamera();
            }
        });
    }

    // Start Recording
    public void startCamera(){
        Intent intent= new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
        }
    }

    // Play the video on VideoView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        VideoView myVideoView = (VideoView)findViewById(R.id.videoView);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode==RESULT_OK){
            Uri video = intent.getData();
            myVideoView.setVideoURI(video);
            myVideoView.setMediaController(new MediaController(this));
            myVideoView.requestFocus();
            myVideoView.start();
        }
    }
}