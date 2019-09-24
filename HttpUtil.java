package com.example.testapp;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
    static Response sendHttpRequestWithOkHttp(final URL url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url.toString())
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
//
//
//interface RequestListener {
//    void onSuccess();
//
//    void onFailed();
//}