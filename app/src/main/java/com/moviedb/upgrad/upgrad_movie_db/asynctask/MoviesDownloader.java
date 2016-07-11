package com.moviedb.upgrad.upgrad_movie_db.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviedb.upgrad.upgrad_movie_db.BuildConfig;
import com.moviedb.upgrad.upgrad_movie_db.R;
import com.moviedb.upgrad.upgrad_movie_db.UpGradMovieDBApplication;
import com.moviedb.upgrad.upgrad_movie_db.constants.KeyConstants;
import com.moviedb.upgrad.upgrad_movie_db.models.Movie;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by hp on 11-07-2016.
 */

public class MoviesDownloader extends AsyncTask<Object, Object, List<Movie>> {

    ProgressDialog progressDialog ;
    Context context ;
    MoviesDownloaderListener listener ;

    public MoviesDownloader(Context context, MoviesDownloaderListener listener) {
        this.context = context;
        this.listener = listener ;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        progressDialog.dismiss();
        listener.onMoviesDownloaded(movies);
    }

    @Override
    protected List<Movie> doInBackground(Object... voids) {
        List<Movie> movies = new ArrayList<>();
        UpGradMovieDBApplication application = (UpGradMovieDBApplication) context.getApplicationContext();
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        String url = "http://" + BuildConfig.MOVIES_DB_ROOT_URL
                + File.separator + KeyConstants.URI_MOVIES
                + "?" +  KeyConstants.PARAMETER_API_KEY + "=" + context.getResources().getString(R.string.api_key) ;
        JsonObjectRequest request = new JsonObjectRequest(url, null, future, future);
        application.addToRequestQueue(request);

        try {
            JSONObject response = future.get(10, TimeUnit.SECONDS); // Blocks for at most 10 seconds.

            ObjectMapper objectMapper = new ObjectMapper();
            movies = objectMapper.readValue(response.getJSONArray("results").toString(), new TypeReference<List<Movie>>() {});

            return movies ;
        } catch (InterruptedException e) {
            // Exception handling
        } catch (ExecutionException e) {
            // Exception handling
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static interface MoviesDownloaderListener {
        void onMoviesDownloaded(List<Movie> movies);
    }
}
