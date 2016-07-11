package com.moviedb.upgrad.upgrad_movie_db.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.moviedb.upgrad.upgrad_movie_db.R;
import com.moviedb.upgrad.upgrad_movie_db.asynctask.MoviesDownloader;
import com.moviedb.upgrad.upgrad_movie_db.constants.KeyConstants;
import com.moviedb.upgrad.upgrad_movie_db.models.Movie;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MoviesDownloader.MoviesDownloaderListener {

    private RecyclerView mRcyclrMovies;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private MoviesAdapter mAdapterMovies ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Movies");

        mRcyclrMovies = (RecyclerView) findViewById(R.id.recycler_movies);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRcyclrMovies.setLayoutManager(mStaggeredLayoutManager);
        mAdapterMovies = new MoviesAdapter(this);
        mRcyclrMovies.setAdapter(mAdapterMovies);

        MoviesAdapter.OnItemClickListener onItemClickListener = new MoviesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                intent.putExtra(KeyConstants.EXTRA_MOVIE, mAdapterMovies.getItemAt(position));
                startActivity(intent);
            }
        };

        mAdapterMovies.setOnItemClickListener(onItemClickListener);

        new MoviesDownloader(this, this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_sort_by_popularity:
                Comparator<Movie> sortByPopularity = new Comparator<Movie>() {
                    @Override
                    public int compare(Movie movie, Movie t1) {
                        return (int) (movie.getPopularity() - t1.getPopularity());
                    }
                };
                Collections.sort(mAdapterMovies.getDataSource(), sortByPopularity);
                mAdapterMovies.notifyDataSetChanged();
                break ;

            case R.id.action_sort_by_rating :
                Comparator<Movie> sortByRating = new Comparator<Movie>() {
                    @Override
                    public int compare(Movie movie, Movie t1) {
                        return (int) (movie.getVoteAverage() - t1.getVoteAverage());
                    }
                };
                Collections.sort(mAdapterMovies.getDataSource(), sortByRating);
                mAdapterMovies.notifyDataSetChanged();
                break ;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMoviesDownloaded(List<Movie> movies) {
        mAdapterMovies.setDataSource(movies);
    }
}
