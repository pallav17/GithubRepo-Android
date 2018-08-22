package com.csc470.palla.githubquery.utlis;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public final static String BASE_URL = "https://api.github.com/users/pallav17/repos";
    private final static String TAG = NetworkUtils.class.getSimpleName();

    public static URL buildURL(String githubStringUrl){
        Uri uri = Uri.parse(githubStringUrl); // parse string to URL

        URL url = null;
        try {
            url = new URL(uri.toString());
        }catch (Exception e){
           e.printStackTrace();
        }

        Log.w(TAG, "Build URL -----------------------------------------" + url.toString());

        return url;
    }

    public static String getResponse(URL buildUrl) throws IOException {
        // Make HTTP connection - establish connection
        HttpURLConnection urlConnection = (HttpURLConnection) buildUrl.openConnection();

        try {
         InputStream inputStream =  urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()){
                return scanner.next();
            }else {
                return null;
            }
        }finally {
            urlConnection. disconnect();
        }

    }
}
