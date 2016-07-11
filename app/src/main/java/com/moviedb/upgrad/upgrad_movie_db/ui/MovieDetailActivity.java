package com.moviedb.upgrad.upgrad_movie_db.ui;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.view.MenuItem;
import android.widget.ImageView;

import com.moviedb.upgrad.upgrad_movie_db.R;
import com.moviedb.upgrad.upgrad_movie_db.constants.KeyConstants;
import com.moviedb.upgrad.upgrad_movie_db.databinding.ActivityMovieDetailBinding;
import com.moviedb.upgrad.upgrad_movie_db.models.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView mImgMoviewPoster ;

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovie = (Movie) getIntent().getSerializableExtra(KeyConstants.EXTRA_MOVIE);
        ActivityMovieDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        binding.setMovie(mMovie);

        mImgMoviewPoster = (ImageView) findViewById(R.id.img_movie_backdrop);

        getSupportActionBar().setTitle(mMovie.getTitle());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Uri uri = Uri.parse(KeyConstants.BASE_URL_IMAGE + mMovie.getPosterPath());
        Picasso.with(this).load(uri).into(mImgMoviewPoster);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
