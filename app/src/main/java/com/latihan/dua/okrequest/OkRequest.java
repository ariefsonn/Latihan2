package com.latihan.dua.okrequest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkRequest {

   public static String getUrl(String url){

      OkHttpClient ok = new OkHttpClient.Builder().
              readTimeout(1000, TimeUnit.MILLISECONDS)
              .writeTimeout(1000, TimeUnit.MILLISECONDS).
              build();

       Request request = new Request.Builder().url(url).build();

       try {
           Response r = ok.newCall(request).execute();

           return r.body().string();
       } catch (IOException e) {
           e.printStackTrace();
           return "";
       }
   }
}
