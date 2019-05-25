package com.example.myapplication;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest=(Button)findViewById(R.id.button);
        responseText=(TextView)findViewById(R.id.textView);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this,"asdasda",Toast.LENGTH_SHORT).show();
        if(v.getId()==R.id.button)
        {
            send();
        }
    }
    private void send()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run() {
                HttpURLConnection connection =null;
                BufferedReader reader =null;
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://39.96.172.141/TestTomcat8/HelloWorld")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responsedata = response.body().string();
                    showResponse(responsedata);
                    /*URL url = new URL("https://blog.csdn.net/mysimplelove/article/details/84063571");
                    connection =(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while((line = reader.readLine())!=null){
                        response.append(line);
                    }
                    showResponse(response.toString());*/
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                /*finally {
                    if(reader!=null)
                    {
                        try{
                            reader.close();
                        }catch(IOException e) {
                            e.printStackTrace();
                        }
                        if(connection!=null)
                            connection.disconnect();
                    }
                }*/
            }
        }).start();
    }
    private void showResponse(final String response)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });

    }

}