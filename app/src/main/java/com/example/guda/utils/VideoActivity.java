package com.example.guda.utils;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.core.content.FileProvider;

import com.example.guda.BaseActivity;
import com.example.guda.R;
import com.example.guda.ThirdActivity;

import java.io.File;
import java.util.Calendar;

public class VideoActivity extends BaseActivity {

    private Button takeVideoBtn;
    private String videoPath;
    private String videoName;
    private File file;

    private static final int VIDEO_RESULT_CODE = 2;
    private static final int MY_PERMISSION_VEDIO = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        //隐藏系统自带标题栏
        getSupportActionBar().hide();
        Intent intent = getIntent();
        Uri videoUri = Uri.parse(intent.getStringExtra("videoUri"));
        String video = intent.getStringExtra("videoName");

//        Toast.makeText(VideoActivity.this, video,Toast.LENGTH_SHORT).show();
        //导航栏按钮
        String path = Environment.getExternalStorageDirectory()
                + "/" + getPackageName() + "/";
        Toast.makeText(VideoActivity.this, path, Toast.LENGTH_SHORT).show();
        File file = new File(path+"test.txt");
        file.getParentFile().mkdir();
        file.setWritable(true);
        VideoView videoView = (VideoView) findViewById(R.id.main_video);
        MediaController controller = new MediaController(VideoActivity.this);//实例化控制器
        videoView.setVideoURI(videoUri);
        /**
         * 将控制器和播放器进行互相关联
         */
        controller.setMediaPlayer(videoView);
        videoView.setMediaController(controller);
        videoView.start();
        Button takeVideoBtn = (Button) findViewById(R.id.main_takeVideoBtn);
        takeVideoBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoActivity.this, ThirdActivity.class);
                intent.putExtra("videoName", video);
                startActivity(intent);
            }
        });

    }

    private void takeVideo() {
        Intent intent= new Intent();
        //启动相机
        intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        //创建文件
        createVideoFile();
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri videoUri;
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            videoUri= FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);
        }else {
            videoUri= Uri.fromFile(file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
        startActivityForResult(intent, VIDEO_RESULT_CODE);
    }

    private void createVideoFile() {
        //设置图片文件名，以当前时间的毫秒值为名称
        videoName = Calendar.getInstance().getTimeInMillis()+ ".mp4";
        //创建图片文件
        file = new File(Environment.getExternalStorageDirectory()
                + "/" + getPackageName() + "/", videoName);
        //将图片的绝对路径设置给mImagePath，之后会用到
        videoPath= file.getAbsolutePath();
        //按设置好的目录层级创建
        file.getParentFile().mkdir();
        file.setWritable(true);
    }



}