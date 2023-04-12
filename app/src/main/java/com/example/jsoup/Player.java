package com.example.jsoup;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class Player extends AppCompatActivity {

    VideoView videoView;
    float posX ,posY;
    float curPosX ,curPosY;
    int a= 0;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.player);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("value");
        String path = bundle.getString("value");

        Start_Play(path);
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.seekTo(a);
        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
        a = videoView.getCurrentPosition();
    }

    public void Start_Play(String path){
        videoView = findViewById(R.id.vv);

        //加载指定的视频文件


        videoView.setVideoPath(path);


        mediaController = new MediaController(this);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
            }
        });
        //创建MediaController对象


        //VideoView与MediaController建立关联
        videoView.setMediaController(mediaController);

        videoView.start();
        //让VideoView获取焦点
        videoView.requestFocus();


    }




    @SuppressLint("ClickableViewAccessibility")
    private void setOnViewTouchListener(){
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        posX = event.getX();
                        posY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        if ((curPosX - posX > 0) && (Math.abs(curPosX - posX) > 25)){
                            Log.v("TAG","向左滑动");
                        }
                        else if ((curPosX - posX < 0) && (Math.abs(curPosX-posX) > 25)){
                            Log.v("TAG","向右滑动");
                        }
                        curPosX = event.getX();
                        curPosY = event.getY();


                        if ((curPosY - posY > 0) && (Math.abs(curPosY - posY) > 25)){
                            Log.v("TAG","向下滑动");

                        }
                        else if ((curPosY - posY < 0) && (Math.abs(curPosY-posY) > 25)){
                            Log.v("TAG","向上滑动");
                        }
                        break;
                }
                return true;
            }
        });
    }

}