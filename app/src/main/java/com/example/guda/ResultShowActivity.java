package com.example.guda;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.guda.Network.Constants;

public class ResultShowActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultshow);
        //隐藏系统自带标题栏
        getSupportActionBar().hide();
        Intent intent = getIntent();
        String video = intent.getStringExtra("videoName");
        Uri videoUri = Uri.parse(intent.getStringExtra("videoUri"));
        videoUri = Uri.parse(Constants.DataDIR+"/"+video);

        String path = Environment.getExternalStorageDirectory()
                + "/" + getPackageName() + "/";
        Toast.makeText(ResultShowActivity.this, path, Toast.LENGTH_SHORT).show();
//        File file = new File(path+"test.txt");
//        file.getParentFile().mkdir();
//        file.setWritable(true);
        VideoView videoView = (VideoView) findViewById(R.id.result_video);
        MediaController controller = new MediaController(ResultShowActivity.this);//实例化控制器
        videoView.setVideoURI(videoUri);
        /**
         * 将控制器和播放器进行互相关联
         */
        controller.setMediaPlayer(videoView);
        videoView.setMediaController(controller);
        videoView.start();

    }




}