package com.ismailhakkiaydin.cizimislemleri;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText txtURL;
    private ImageView imgPhoto;

    private Handler hnd = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            imgPhoto.setImageBitmap((Bitmap) msg.obj);
        }
    };

    public class Downloader extends AsyncTask<String,Void,Void>{
        @Override
        protected Void doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setDoInput(true);
                connection.connect();

                if (connection.getResponseCode()==200){
                    InputStream input = connection.getInputStream();
                    Bitmap bmp = BitmapFactory.decodeStream(input);
                    Message msg = new Message();
                    msg.obj=bmp;
                    hnd.sendMessage(msg);

                    String path = Environment.getExternalStorageDirectory()+ "/";
                    String filename = "temp.jpg";
                    File file = new File(path,filename);
                    FileOutputStream fos = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.JPEG,100,fos);
                    MediaStore.Images.Media.insertImage(MainActivity.this.getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
                    fos.close();
                }
            }catch (Exception e){
                Log.e("csd", e.getMessage());
            }
            return null;

        }
    }
    private void init(){
        txtURL = findViewById(R.id.txtURL);
        imgPhoto=findViewById(R.id.imgPhoto);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(new Tuval(this));
      //  setContentView(new Surface(this));
        setContentView(R.layout.activity_main);
        init();

    }

    public void btn_Click(){
        Downloader d = new Downloader();
        d.execute(txtURL.getText().toString());
    }
}
