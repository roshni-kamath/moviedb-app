package com.moviedb.upgrad.upgrad_movie_db;

import android.content.Context;

import com.moviedb.upgrad.upgrad_movie_db.asynctask.MoviesDownloader;
import com.moviedb.upgrad.upgrad_movie_db.ui.MainActivity;
import com.moviedb.upgrad.upgrad_movie_db.ui.MoviesAdapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by mac on 7/25/16.
 */

public class MainActivityTester {

    @Mock
    private MainActivity mContext ;

    @Captor
    private ArgumentCaptor<MoviesDownloader.MoviesDownloaderListener> mMoviesDownloaderListener ;

    private MoviesDownloader mMoviesDownloader ;

    @Before
    public void setUpMainActivity(){

        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        mMoviesDownloader = new MoviesDownloader(mContext, mMoviesDownloaderListener.capture()) ;
    }

    @Test
    public void loadMoviesFromTheApiAndLoadIntoTheView(){
        //mMoviesDownloader

    }
}
