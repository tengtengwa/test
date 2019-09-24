package com.example.testapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button startDownload;
    Button cancelDownload;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startDownload = findViewById(R.id.start_download);
        cancelDownload = findViewById(R.id.cancel_download);
        textView = findViewById(R.id.text_view);
        textView.setText("未下载");

        startDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    URL url = new URL("https://qd.myapp.com/myapp/qqteam/pcqq/PCQQ2019.exe");
                    DownloadTask downloadTask = new DownloadTask();
                    downloadTask.setInner(new inner() {
                        @Override
                        public void setProgress(String progress) {
                            textView.setText(progress);
                        }
                    });
                    downloadTask.execute(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        });

        cancelDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask().cancelDownload();
                textView.setText("下载取消");
            }
        });

    }


}

