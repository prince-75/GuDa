package com.example.guda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {//implements View.OnClickListener
      //调用摄像头
    public static final int TAKE_PHOTO=1;
    private ImageView picture;
    private Uri imageUri;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏系统自带标题栏
        getSupportActionBar().hide();
        //导航栏按钮
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"哇，你学会转换界面了哟，真棒",Toast.LENGTH_SHORT).show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,FourActivity.class);
                startActivity(intent);
            }
        });
        //调用摄像头
        Button takePhoto=(Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);
            //注册button事件并且写摄像头逻辑
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //File用于存图片
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                    //图片起名为output_image.jpg；通过getExternalCacheDir()存放在SD卡中的应用关联缓存目录中
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch(IOException e){
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24){
                    //如果高于Android7.0
                    imageUri = FileProvider.getUriForFile(MainActivity.this,"FileToUri",outputImage);
                    //调用getUriForFile(context对象，任意字符串，File对象)将File对象转换成Uri对象
                } else {
                    //否则
                    imageUri = Uri.fromFile(outputImage);
                    //调用fromFile()将File对象转换成Uri对象
                }

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);//指定图片的输出地址
                startActivityForResult(intent,TAKE_PHOTO);//启动相机程序，结果返回到onActiviityResult()
            }
        });
     }

    protected void onActiviityResult(int requestCode, int resultCode,Intent data){
        switch(requestCode){
            case TAKE_PHOTO:
                if(resultCode == RESULT_OK){
                    try{
                        //显示照片
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    }catch(FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }


    /*@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);//创建实例
                dialog.setTitle("This is Dialog");
                dialog.setMessage("Something important");
                //dialog.setCancelable
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {//确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {//取消按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }*/
}

