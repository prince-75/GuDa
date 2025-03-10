package com.example.guda;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.guda.Network.Constants;
import com.example.guda.Network.WebService;
import com.example.guda.Network.util.WifiUtils;
import com.example.guda.utils.SwitchBotton;
import com.hwangjr.rxbus.RxBus;
import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.http.body.MultipartFormDataBody;
import com.koushikdutta.async.http.body.Part;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Calendar;

public class ThirdActivity extends BaseActivity implements View.OnClickListener {


    private static final int ALBUM_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int VIDEO_RESULT_CODE = 2;
    private static final int MY_PERMISSION_LIST = 3;
    private static final int MY_PERMISSION_ALBUM = 4;
    private static final int MY_PERMISSION_CAMERA = 5;
    private static final int MY_PERMISSION_VEDIO = 6;
    private static final String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };

    private Button chooseAlbumBtn;
    private Button takeVideoBtn;
    private String videoPath;
    private String videoName;
    private String video;
    private File file;
    private String ip;
    private MutableLiveData<Long> receivedFile = new MutableLiveData<Long>();

    private AsyncHttpServer mServer = new AsyncHttpServer();
    private AsyncServer mAsyncServer = new AsyncServer();
    private MultipartFormDataBody mBody;
    private FileOutputStream mFileOutputStream;
    WebService.FileUploadHolder fileUploadHolder = new WebService.FileUploadHolder();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        //隐藏系统自带标题栏
        getSupportActionBar().hide();
        Intent intent = getIntent();
        video = intent.getStringExtra("videoName");
        Toast.makeText(ThirdActivity.this, video,Toast.LENGTH_SHORT).show();
        requestPermission();
        startService();//启动服务
        wifiShow();//弹窗wifi地址
        // 滑块按钮
        SwitchBotton sbotton = (SwitchBotton)findViewById(R.id.sbotton);
        sbotton.setOnMbClickListener(new SwitchBotton.OnMClickListener() {
            @Override
            public void onClick(boolean isRight) {
                if (isRight){
                    startService();
                    wifiShow();
                }else {
                    mServer.stop();
                    mAsyncServer.stop();
                }
            }
        });

        //导航栏按钮
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button4 = (Button) findViewById(R.id.button4);
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
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
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
        receivedFile.observe(this, new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long time) {
                File video = fileUploadHolder.getRecievedFile();
                Intent intent = new Intent(ThirdActivity.this, ResultShowActivity.class);
                intent.putExtra("videoUri", video.getAbsolutePath());
                intent.putExtra("videoName", video.getName());
                startActivity(intent);
            }
        });
    }

    /**
     *  存储权限获取
     */
    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1024);
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    /**
     * 配置组件
     */
    private void initViews() {
        chooseAlbumBtn = findViewById(R.id.main_chooseAlbumBtn);
        takeVideoBtn = findViewById(R.id.main_takeVideoBtn);
        chooseAlbumBtn.setOnClickListener(this);
        takeVideoBtn.setOnClickListener(this);
    }

    private void wifiShow(){
        ip = WifiUtils.getDeviceIpAddress();
        AlertDialog alertDialog = new AlertDialog.Builder(ThirdActivity.this)
                .setTitle("成功开启服务")
                .setMessage(ip)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();
        alertDialog.show();
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
            case R.id.main_takeVideoBtn: //摄像头录像
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA}, MY_PERMISSION_VEDIO);
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
        }
        else {
            videoUri= Uri.fromFile(file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
        startActivityForResult(intent, VIDEO_RESULT_CODE);
    }

    /**
     * 创建视频文件
     */
    private void createVideoFile() {
        //设置图片文件名，以当前时间的毫秒值为名称
        videoName = Calendar.getInstance().getTimeInMillis() + "_" + video + ".mp4";
        //创建图片文件
        file = new File(Environment.getExternalStorageDirectory()
                + "/" + getPackageName() + "/", videoName);
        //将图片的绝对路径设置给mImagePath，之后会用到
        videoPath= file.getAbsolutePath();
        //按设置好的目录层级创建
        file.getParentFile().mkdir();
        file.setWritable(true);
//        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));

    }

    private void PlayVideo(WebService.FileUploadHolder fileUpload){

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case MY_PERMISSION_ALBUM:
                    chooseAlbum(); //打开相册
                    break;
                case MY_PERMISSION_VEDIO:
                    takeVideo(); //打开相机录像
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
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, ALBUM_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && null != data) {
//            switch (requestCode) {
//                case ALBUM_REQUEST_CODE:
                    Uri selectedVideo = data.getData();
                    String[] filePathColumn = {MediaStore.Video.Media.DATA, MediaStore.Images.Media.SIZE, MediaStore.Images.Media.DISPLAY_NAME};

                    Cursor cursor = getContentResolver().query(selectedVideo,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    videoName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    videoPath = cursor.getString(columnIndex);
                    //显示出文件路径
                    Toast.makeText(ThirdActivity.this, videoPath, Toast.LENGTH_SHORT).show();
                    cursor.close();
                //自动跳转
//            String uri = new String("http://%s:12345/files/%s");
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse(String.format(uri, ip, videoName)));
//            startActivity(intent);
//                case VIDEO_RESULT_CODE:
//                    videoName = file.getName();
//                    videoPath = file.getPath();

//            }
        }
    }


    /**
     * 开启服务
     */
    private void startService(){
        //视频列表
        mServer.get("/files", (AsyncHttpServerRequest request, AsyncHttpServerResponse response) -> {
            JSONArray array = new JSONArray();
            String dir = videoPath.replace(videoName, "");
            File file = new File(dir, videoName);
            if (file.exists() && file.isFile()) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", videoName);
                    long fileLen = file.length();
                    DecimalFormat df = new DecimalFormat("0.00");
                    if (fileLen > 1024 * 1024) {
                        jsonObject.put("size", df.format(fileLen * 1f / 1024 / 1024)
                                + "MB");
                    } else if (fileLen > 1024) {
                        jsonObject.put("size", df.format(fileLen * 1f / 1024) + "KB");
                    } else {
                        jsonObject.put("size", fileLen + "B");
                    }
                    array.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            response.send(array.toString());
        });

        //下载文件
        mServer.post("/files", (AsyncHttpServerRequest request, AsyncHttpServerResponse response)
                        -> {
                    final MultipartFormDataBody body = (MultipartFormDataBody) request.getBody();
                    body.setMultipartCallback((Part part) -> {
                        if (part.isFile()) {
                            body.setDataCallback((DataEmitter emitter, ByteBufferList bb) -> {
                                fileUploadHolder.write(bb.getAllByteArray());
                                bb.recycle();
                            });
                        } else {
                            if (body.getDataCallback() == null) {
                                body.setDataCallback((DataEmitter emitter, ByteBufferList bb) -> {
                                    try {
                                        String fileName = URLDecoder.decode(new String(bb
                                                .getAllByteArray()), "UTF-8");
                                        fileUploadHolder.setFileName(fileName);

//                                        Toast.makeText(ThirdActivity.this, fileName, Toast.LENGTH_SHORT).show();
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    bb.recycle();
                                });
                            }
                        }
                    });
                    request.setEndCallback((Exception e) -> {
                        fileUploadHolder.reset();
                        response.end();
                        RxBus.get().post(Constants.RxBusEventType.LOAD_BOOK_LIST, 0);
                        receivedFile.postValue(SystemClock.elapsedRealtime());

                    });
//                    PlayVideo(fileUploadHolder);
                }
        );

        //上传视频
        mServer.get("/files/.*", (AsyncHttpServerRequest request, AsyncHttpServerResponse
                response) -> {
            String fileName = request.getPath().replace("/files/", "");
            try {
                fileName = URLDecoder.decode(fileName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String dir = videoPath.replace(fileName, "");
            File file = new File(dir, fileName);
            if (file.exists() && file.isFile()) {
                try {
                    response.getHeaders().add("Content-Disposition", "attachment;filename=" +
                            URLEncoder.encode(file.getName(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                response.sendFile(file);
                return;
            }
            response.code(404).send("Not found!");
        });
        mServer.listen(mAsyncServer, Constants.HTTP_PORT);
    }

}