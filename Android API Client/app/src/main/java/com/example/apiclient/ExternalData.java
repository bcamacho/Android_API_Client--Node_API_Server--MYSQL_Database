package com.example.apiclient;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by brandycamacho on 5/5/15.
 */
public class ExternalData extends ContextWrapper {
    private String TAG = "LocalData class";
    // used to help control contex
    public ExternalData(Context base, String TAG) {
        super(base);
        this.TAG = TAG;
    }
    // TAG for loging and debuging
    public void log(String data) {
        Log.d(TAG, data);
    };

    public String mGetDataFromServer(String serverUrl){
        try{
            URL url = new URL(serverUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String scanner = new Scanner(in).useDelimiter("\\A").next();
                return scanner;
            }
            finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {

        };
    return "Failed";
    };

public String mSetDataFromServer(String serverUrl, String data){
    try {
        // Note if data needs to be encoded use URLEncoder
        // i.e --> data = URLEncoder.encode(data, "UTF-8");

        // instantiate the URL object with the target URL of the resource to
        // request
        URL url=new URL(serverUrl);
        // instantiate the HttpURLConnection with the URL object - A new
        // connection is opened every time by calling the openConnection
        // method of the protocol handler for this URL.
        // 1. This is the point where the connection is opened.
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // set connection output to true
        connection.setDoOutput(true);
        // instead of a GET, we're going to send using method="POST"
        connection.setRequestMethod("POST");
        // setting our content type, if using JSON this needs to be changed to application/json
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        // instantiate OutputStreamWriter using the output stream, returned
        // from getOutputStream, that writes to this connection.
        // 2. This is the point where you'll know if the connection was
        // successfully established. If an I/O error occurs while creating
        // the output stream, you'll see an IOException.
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        // write data to the connection. This is data that you are sending
        // to the server
        // 3. No. Sending the data is conducted here. We established the
        // connection with getOutputStream
        out.write(data);
        // Closes this output stream and releases any system resources
        // associated with this stream. At this point, we've sent all the
        // data. Only the outputStream is closed at this point, not the
        // actual connection
        out.close();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // OK
            System.out.print("Data sent successfully");
            // otherwise, if any other status code is returned, or no status
            // code is returned, do stuff in the else block
        } else {
            // Server returned HTTP error code.
        };
    } catch (MalformedURLException e) {
        // ...
    } catch (IOException e) {
        // ...
    };

    return "Failed";
    };
};
