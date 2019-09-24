package com.example.testapp;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.Response;

public class DownloadTask extends AsyncTask<URL, Integer, Long> {

    private boolean isCanceled = false;
    inner inner;

    public void setInner(com.example.testapp.inner inner) {
        this.inner = inner;
    }

    public void cancelDownload() {
        isCanceled = true;
    }

    @Override
    protected void onCancelled() {
        isCanceled = true;
    }

    @Override
    protected Long doInBackground(final URL... urls) {
        final long totalSize = 0;

        File file = new File(Environment.getExternalStorageDirectory(), "PCQQ2019.exe");
        Log.d("ttw", "run: " + file);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Response response = HttpUtil.sendHttpRequestWithOkHttp(urls[0]);
            long sumLen = response.body().contentLength();
            float sum = 0;
            long now;
            InputStream br = response.body().byteStream();
            FileOutputStream bw = new FileOutputStream(file);
            byte[] b = new byte[1024];
            while ((now = br.read(b)) != -1) {
                if (isCanceled) {
                    file.delete();
                    break;
                }
                bw.write(b);
                sum += now;
                publishProgress((int) ((sum / sumLen) * 100));
                Log.d("ttw", "read: ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (isCanceled) {
                file.delete();
            }

        }


        return totalSize;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        Log.d("ttw", "onProgressUpdate: " + (int) progress[0]);
        if (inner != null) {
            inner.setProgress("已下载" + (int) progress[0] + "%");
        }
    }

    @Override
    protected void onPostExecute(Long aLong) {


    }
}
