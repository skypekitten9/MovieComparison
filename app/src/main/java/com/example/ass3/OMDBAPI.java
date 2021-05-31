package com.example.ass3;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

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
    public LiveData<List<OMDBResponse>> result = new MutableLiveData<>();
    public List<OMDBResponse> result2 = new ArrayList<OMDBResponse>();
    private boolean done = false;

    public LiveData<List<OMDBResponse>> getResult() {
        return result;
    }
    public List<OMDBResponse> getResult2(){
        return result2;
    }
    public boolean getDone() {
        return done;
    }

    public OMDBAPI(MainActivity main) {
        apiKey = main.getResources().getString(R.string.apiKey);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void SearchShows(String searchTerm)
    {
        done = false;
        result2 = new ArrayList<OMDBResponse>();
        thread = new Thread(new SearchShows(searchTerm));
        thread.start();
    }

    private class SearchShows implements Runnable
    {
        LiveData<List<OMDBResponse>> data;
        String title;

        public SearchShows(String title) {
            this.title = title;
        }

        @Override
        public void run() {
            try {
                searchShows(title, apiKey);
                Log.d(TAG, "Shows");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public LiveData<List<OMDBResponse>> searchShowsStub(String searchTerm, String apiKey) throws IOException {
            String[] shows = new String[3];
            shows[0] ="{\"Title\":\"Batman: The Animated Series\",\"Year\":\"1992–1995\",\"Rated\":\"TV-PG\",\"Released\":\"05 Sep 1992\",\"Runtime\":\"23 min\",\"Genre\":\"Animation, Action, Adventure, Family, Sci-Fi\",\"Director\":\"N/A\",\"Writer\":\"Bob Kane, Eric Radomski, Bruce Timm, Paul Dini, Bill Finger\",\"Actors\":\"Kevin Conroy, Efrem Zimbalist Jr., Bob Hastings\",\"Plot\":\"The Dark Knight battles crime in Gotham City with occasional help from Robin and Batgirl.\",\"Language\":\"English\",\"Country\":\"USA\",\"Awards\":\"Won 1 Primetime Emmy. Another 4 wins & 19 nominations.\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BOTM3MTRkZjQtYjBkMy00YWE1LTkxOTQtNDQyNGY0YjYzNzAzXkEyXkFqcGdeQXVyOTgwMzk1MTA@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"9.0/10\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"9.0\",\"imdbVotes\":\"89,153\",\"imdbID\":\"tt0103359\",\"Type\":\"series\",\"totalSeasons\":\"4\",\"Response\":\"True\"}";
            shows[1] ="{\"Title\":\"Jesper\",\"Year\":\"1988\",\"Rated\":\"TV-PG\",\"Released\":\"05 Sep 1988\",\"Runtime\":\"23 min\",\"Genre\":\"Animation, Action, Adventure, Family, Sci-Fi\",\"Director\":\"N/A\",\"Writer\":\"Bob Kane, Eric Radomski, Bruce Timm, Paul Dini, Bill Finger\",\"Actors\":\"Kevin Conroy, Efrem Zimbalist Jr., Bob Hastings\",\"Plot\":\"The Dark Knight battles crime in Gotham City with occasional help from Robin and Batgirl.\",\"Language\":\"English\",\"Country\":\"USA\",\"Awards\":\"Won 1 Primetime Emmy. Another 4 wins & 19 nominations.\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BOTM3MTRkZjQtYjBkMy00YWE1LTkxOTQtNDQyNGY0YjYzNzAzXkEyXkFqcGdeQXVyOTgwMzk1MTA@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.0/10\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"7.0\",\"imdbVotes\":\"79,153\",\"imdbID\":\"tt0103359\",\"Type\":\"series\",\"totalSeasons\":\"4\",\"Response\":\"True\"}";
            shows[2] ="{\"Title\":\"Axel\",\"Year\":\"1994–1997\",\"Rated\":\"TV-PG\",\"Released\":\"05 Sep 1994\",\"Runtime\":\"23 min\",\"Genre\":\"Animation, Action, Adventure, Family, Sci-Fi\",\"Director\":\"N/A\",\"Writer\":\"Bob Kane, Eric Radomski, Bruce Timm, Paul Dini, Bill Finger\",\"Actors\":\"Kevin Conroy, Efrem Zimbalist Jr., Bob Hastings\",\"Plot\":\"The Dark Knight battles crime in Gotham City with occasional help from Robin and Batgirl.\",\"Language\":\"English\",\"Country\":\"USA\",\"Awards\":\"Won 1 Primetime Emmy. Another 4 wins & 19 nominations.\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BOTM3MTRkZjQtYjBkMy00YWE1LTkxOTQtNDQyNGY0YjYzNzAzXkEyXkFqcGdeQXVyOTgwMzk1MTA@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"9.0/10\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"6.0\",\"imdbVotes\":\"69,153\",\"imdbID\":\"tt0103359\",\"Type\":\"series\",\"totalSeasons\":\"4\",\"Response\":\"True\"}";


           List<OMDBResponse> result = new ArrayList<>();

            for(int i = 0; i < shows.length; i++)
            {
                if(i > 0) shows[i] = shows[i].substring(1);
                shows[i] = shows[i] + "}";
                String stream = shows[i].toString();
//                OMDBResponse t = new Gson().fromJson(stream,OMDBResponse.class);
                OMDBResponse t = new OMDBResponse();
                t.setTitle("Batman");
                t.setImdbRating("8.5");
                t.setYear("2006");
                result.add(t);
                result2.add(t);
            }
            MutableLiveData<List<OMDBResponse>> data = new MutableLiveData<List<OMDBResponse>>();
            data.postValue(result);
            return data;
        }

        public void searchShows(String searchTerm, String apiKey) throws IOException {
            String url = "https://www.omdbapi.com/?s=" + searchTerm + "&apikey=" + apiKey;
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String sb = "";
            String stream2;

            String line;
            while((line = r.readLine()) != null)
            {
                sb = sb + line;
            }
            if(sb == null || sb.equals("{\"Response\":\"False\",\"Error\":\"Movie not found!\"}"))
            {
                result2 = new ArrayList<OMDBResponse>();
                done = true;
                return;
            }

            String[] filterStart = sb.split("\\[");
            String[] filterEnd = filterStart[1].split("\\]");
            String[] shows = filterEnd[0].split("\\}");


            List<OMDBResponse> result = new ArrayList<>();
            for(int i = 0; i < shows.length; i++)
            {
                //{"Title":"Batman: The Animated Series","Year":"1992–1995","Rated":"TV-PG","Released":"05 Sep 1992","Runtime":"23 min","Genre":"Animation, Action, Adventure, Family, Sci-Fi","Director":"N/A","Writer":"Bob Kane, Eric Radomski, Bruce Timm, Paul Dini, Bill Finger","Actors":"Kevin Conroy, Efrem Zimbalist Jr., Bob Hastings","Plot":"The Dark Knight battles crime in Gotham City with occasional help from Robin and Batgirl.","Language":"English","Country":"USA","Awards":"Won 1 Primetime Emmy. Another 4 wins & 19 nominations.","Poster":"https://m.media-amazon.com/images/M/MV5BOTM3MTRkZjQtYjBkMy00YWE1LTkxOTQtNDQyNGY0YjYzNzAzXkEyXkFqcGdeQXVyOTgwMzk1MTA@._V1_SX300.jpg","Ratings":[{"Source":"Internet Movie Database","Value":"9.0/10"}],"Metascore":"N/A","imdbRating":"9.0","imdbVotes":"89,153","imdbID":"tt0103359","Type":"series","totalSeasons":"4","Response":"True"}
                if(i > 0) shows[i] = shows[i].substring(1);
                shows[i] = shows[i] + "}";
                String stream = shows[i];
                OMDBResponse t = new Gson().fromJson(stream,OMDBResponse.class);
                String id = t.getImdbID();
                String url2 = "https://www.omdbapi.com/?i=" + id + "&apikey=" + apiKey;
                HttpURLConnection conn2 = (HttpURLConnection) new URL(url2).openConnection();
                BufferedReader r2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
                StringBuilder sb2 = new StringBuilder();

                String line2;
                while((line2 = r2.readLine()) != null)
                {
                    sb2.append(line2);
                }

                stream2 = sb2.toString();

                OMDBResponse t1 = new Gson().fromJson(stream2,OMDBResponse.class);

                t.setImdbRating(t1.getImdbRating());

                result.add(t);
                result2.add(t);
            }

            done = true;

            MutableLiveData<List<OMDBResponse>> data = new MutableLiveData<List<OMDBResponse>>();
            data.postValue(result);
        }
    }
}
