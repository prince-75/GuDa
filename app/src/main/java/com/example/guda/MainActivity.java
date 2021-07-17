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
import android.widget.LinearLayout;
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
//        调用摄像头
        Button takephoto = findViewById(R.id.take_photo);
        picture = findViewById(R.id.picture);
        takephoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try//判断图片是否存在，存在则删除在创建，不存在则直接创建
                {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //判断运行设备的系统版本是否低于Android7.0
                if (Build.VERSION.SDK_INT >= 24)
                {
                    imageUri = FileProvider.getUriForFile(MainActivity.this,
                            "com.example.cameraalbumtest.fileprovider", outputImage);

                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                //使用隐示的Intent，调用摄像头，并把它存储
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                //调用会返回结果的开启方式，返回成功的话，则把它显示出来
            }
        });





//        Button takePhoto=(Button) findViewById(R.id.take_photo);
//        picture = (ImageView) findViewById(R.id.picture);
        //注册button事件并且写摄像头逻辑
//        takePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                //File用于存图片
//                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
//                //图片起名为output_image.jpg；通过getExternalCacheDir()存放在SD卡中的应用关联缓存目录中
//                try{
//                    if(outputImage.exists()){
//                        outputImage.delete();
//                    }
//                    outputImage.createNewFile();
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//                if (Build.VERSION.SDK_INT>=24){
//                    //如果高于Android7.0
//                    imageUri = FileProvider.getUriForFile(MainActivity.this,"com.example.guda.fileprovider",outputImage);
//                    //调用getUriForFile(context对象，任意字符串，File对象)将File对象转换成Uri对象
//                } else {
//                    //否则
//                    imageUri = Uri.fromFile(outputImage);
//                    //调用fromFile()将File对象转换成Uri对象
//                }
//
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);//指定图片的输出地址
//                startActivityForResult(intent,TAKE_PHOTO);//启动相机程序，结果返回到onActiviityResult()
//            }
//        });
    }

     //储存相机程序返回的结果
     protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
         switch (requestCode) {
             case TAKE_PHOTO:
                 if (resultCode == RESULT_OK) {
                     try {
                         Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                         picture.setImageBitmap(bitmap);
                         //将图片解析成Bitmap对象，并把它显现出来
                     } catch (FileNotFoundException e) {
                         e.printStackTrace();
                     }
                 }
                 break;
             default:
                 break;
         }
     }



//    protected void onActiviityResult(int requestCode, int resultCode,Intent data){
//        switch(requestCode){
//            case TAKE_PHOTO:
//                if(resultCode == RESULT_OK){
//                    try{
//                        //显示照片
//                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//                        picture.setImageBitmap(bitmap);
//                    }catch(FileNotFoundException e){
//                        e.printStackTrace();
//                    }
//                }
//                break;
//            default:
//                break;
//        }
//    }

}

