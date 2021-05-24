package com.example.ass3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Chuck extends Service {
    private Thread thread;
    public static final String  url = "https://api.icndb.com/jokes/random";
    String stream;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void StartConnect()
    {
        thread = new Thread(new Connect());
        thread.start();
    }

    private class Connect implements Runnable
    {


        @Override
        public void run() {
            try {
                HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
                int respCode = con.getResponseCode();
                if(respCode == HttpURLConnection.HTTP_OK)
                {
                   getJoke();
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }

        private TemplateResponse getJoke() throws IOException, JSONException {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line;

            while((line = r.readLine()) != null)
            {
                sb.append(line);
            }

            stream = sb.toString();

            TemplateResponse t = new Gson().fromJson(stream,TemplateResponse.class);
            Log.d("JOKE", t.getValue().getJoke());
            return t;
        }

    }

}
