package com.moviedb.upgrad.upgrad_movie_db;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by hp on 11-07-2016.
 */

public class UpGradMovieDBApplication extends Application {

    public static final String TAG = UpGradMovieDBApplication.class.getSimpleName();

    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
}
