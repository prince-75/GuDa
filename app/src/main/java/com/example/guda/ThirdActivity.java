package com.example.guda;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int ALBUM_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int VIDEO_RESULT_CODE = 2;
    private static final int MY_PERMISSION_LIST = 3;
    private static final int MY_PERMISSION_ALBUM = 4;
    private static final int MY_PERMISSION_CAMERA = 5;

    private Button chooseAlbumBtn;
    private Button takePhotoBtn;
    private Button takeVideoBtn;
    private ImageView photoTv;
    private String photoPath;
    private String videoPath;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        //隐藏系统自带标题栏
        getSupportActionBar().hide();
        //导航栏按钮
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button4 = (Button)findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ThirdActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ThirdActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ThirdActivity.this,FourActivity.class);
                startActivity(intent);
            }
        });
        //摄像头组件
        initViews();
    }

    /**
     * 配置组件
     */
    private void initViews() {
        chooseAlbumBtn = findViewById(R.id.main_chooseAlbumBtn);
        takePhotoBtn = findViewById(R.id.main_takePhotoBtn);
        takeVideoBtn = findViewById(R.id.main_takeVideoBtn);
        photoTv = findViewById(R.id.main_photoTv);

        chooseAlbumBtn.setOnClickListener(this);
        takePhotoBtn.setOnClickListener(this);
        takeVideoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_chooseAlbumBtn: //相册选择
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_ALBUM);
                } else {
                    chooseAlbum(); //打开相册
                }
                break;
            case R.id.main_takePhotoBtn: //摄像头拍照
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA}, MY_PERMISSION_CAMERA);
                } else {
                    takeCamera(); //打开相机拍照
                }
                break;
            case R.id.main_takeVideoBtn: //摄像头录像
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA}, MY_PERMISSION_CAMERA);
                } else {
                    takeVideo(); //打开相机录像
                }
                break;
            default:

                break;
        }
    }

    /**
     * 打开相机录像
     */
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

    /**
     * 创建文件
     */
    private void createVideoFile() {
        //设置图片文件名，以当前时间的毫秒值为名称
        String videoName= Calendar.getInstance().getTimeInMillis()+ ".mp4";
        //创建图片文件
        file = new File(Environment.getExternalStorageDirectory()
                + "/" + getPackageName() + "/", videoName);
        //将图片的绝对路径设置给mImagePath，之后会用到
        videoPath= file.getAbsolutePath();
        //按设置好的目录层级创建
        file.getParentFile().mkdir();
        file.setWritable(true);
    }

    /**
     * 打开相机拍照
     */
    private void takeCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), "PhotoVideoTest");
        if (!file.exists()) {
            file.mkdir();
        }
        File photoFile = new File(file, PhotoBitmapUtils.getImageFileName());
        photoPath = photoFile.getAbsolutePath();
        Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case MY_PERMISSION_ALBUM:
                    chooseAlbum(); //打开相册
                    break;
                case MY_PERMISSION_CAMERA:
                    takeCamera(); //打开摄像头拍照
                    break;
                default:
                    break;
            }
        } else {
            Toast.makeText(ThirdActivity.this, "用户禁止授权", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 打开相册
     */
    private void chooseAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, ALBUM_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ALBUM_REQUEST_CODE: //相册选择的回调
                    Uri uri = data.getData(); //获取系统返回的照片uri
                    String[] strings = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uri, strings, null, null, null);
                    cursor.moveToFirst();
                    int index = cursor.getColumnIndex(strings[0]);
                    String path = cursor.getString(index); //获取图片路径
                    cursor.close();
                    Log.d("fantasychong_path", path);
                    Bitmap bitmap = BitmapFactory.decodeFile(PhotoBitmapUtils.amendRotatePhoto(path, ThirdActivity.this));
                    photoTv.setImageBitmap(bitmap);
                    break;
                case CAMERA_REQUEST_CODE: //摄像头拍照的回调
                    Bitmap bitmap1 = BitmapFactory.decodeFile(PhotoBitmapUtils.amendRotatePhoto(photoPath, ThirdActivity.this));
                    photoTv.setImageBitmap(bitmap1);
                    break;
                case VIDEO_RESULT_CODE: //摄像头录像的回调
                    Log.d("fantasychong_video", videoPath);
                    break;
                default:
                    break;
            }
        }
    }

}
