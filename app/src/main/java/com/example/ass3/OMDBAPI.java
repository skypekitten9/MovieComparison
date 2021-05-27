package com.example.ass3;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OMDBAPI extends Service {

    private Thread thread;
    private String apiKey;
    public static final String baseurl = "http://www.omdbapi.com/";
    private static final String TAG = "OMDBAPI";

    public OMDBAPI(MainActivity main) {
        apiKey = main.getResources().getString(R.string.apiKey);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void SearchShows()
    {
        thread = new Thread(new SearchShows());
        thread.start();
    }

    private class SearchShows implements Runnable
    {
        String search= "?s=";
        LiveData<List<OMDBResponse>> data;
        String title = "batman";


        @Override
        public void run() {
            HttpURLConnection conn = null;
            try {
                List<OMDBResponse> shows = searchShows("batman", apiKey);
                Log.d(TAG, "Shows");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public List<OMDBResponse> searchShows(String searchTerm, String apiKey) throws IOException {
            String url = "http://www.omdbapi.com/?s=" + searchTerm + "&type=series&apikey=" + apiKey + "&format=" + "json";
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String sb = "";

            String line;
            while((line = r.readLine()) != null)
            {
                sb = sb + line;
            }
            if(sb == null) return new ArrayList<>();

            String[] filterStart = sb.split("\\[");
            String[] filterEnd = filterStart[1].split("\\]");
            String[] shows = filterEnd[0].split("\\}");


            List<OMDBResponse> result = new ArrayList<>();
            for(int i = 0; i < shows.length; i++)
            {
                //{"Title":"Batman: The Animated Series","Year":"1992–1995","Rated":"TV-PG","Released":"05 Sep 1992","Runtime":"23 min","Genre":"Animation, Action, Adventure, Family, Sci-Fi","Director":"N/A","Writer":"Bob Kane, Eric Radomski, Bruce Timm, Paul Dini, Bill Finger","Actors":"Kevin Conroy, Efrem Zimbalist Jr., Bob Hastings","Plot":"The Dark Knight battles crime in Gotham City with occasional help from Robin and Batgirl.","Language":"English","Country":"USA","Awards":"Won 1 Primetime Emmy. Another 4 wins & 19 nominations.","Poster":"https://m.media-amazon.com/images/M/MV5BOTM3MTRkZjQtYjBkMy00YWE1LTkxOTQtNDQyNGY0YjYzNzAzXkEyXkFqcGdeQXVyOTgwMzk1MTA@._V1_SX300.jpg","Ratings":[{"Source":"Internet Movie Database","Value":"9.0/10"}],"Metascore":"N/A","imdbRating":"9.0","imdbVotes":"89,153","imdbID":"tt0103359","Type":"series","totalSeasons":"4","Response":"True"}
                if(i > 0) shows[i] = shows[i].substring(1);
                shows[i] = shows[i] + "}";
                String stream = shows[i].toString();
                OMDBResponse t = new Gson().fromJson(stream,OMDBResponse.class);
                result.add(t);
            }


            return result;
        }
    }
}
