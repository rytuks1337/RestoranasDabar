package com.example.restoranasdabar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;

import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetDataFromWeb implements Runnable{

    private String src="";
    private Bitmap myBitmap;
    GetDataFromWeb(String url){
        this.src=url;
    }

    public void run() {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace().toString());
        }
    }
    public Bitmap getImage(){
        return myBitmap;
    }

}