package com.moviedb.upgrad.upgrad_movie_db.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moviedb.upgrad.upgrad_movie_db.R;
import com.moviedb.upgrad.upgrad_movie_db.constants.KeyConstants;
import com.moviedb.upgrad.upgrad_movie_db.models.Movie;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private Context mContext;
    private OnItemClickListener mItemClickListener;
    private List<Movie> mMovieList;

    public MoviesAdapter(Context context) {
        this.mContext = context;
        this.mMovieList = new ArrayList<>();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        final Movie movie = mMovieList.get(position);
        holder.txtMovieName.setText(movie.getTitle());
        Uri uri = Uri.parse(KeyConstants.BASE_URL_IMAGE + movie.getPosterPath());
        Picasso.with(mContext).load(uri).into(holder.imgMovie);
/*
        InputStream inputStream = new BufferedInputStream();
        Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), uri.getPath());

        Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                int bgColor = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
                holder.lnrPlaceNameHolder.setBackgroundColor(bgColor);
            }
        });*/
    }

    public void setDataSource(List<Movie> movies) {
        this.mMovieList.addAll(movies);
        notifyDataSetChanged();
    }

    public List<Movie> getDataSource() {
        return this.mMovieList;
    }

    public Movie getItemAt(int position) {
        return mMovieList.get(position);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout lnrPlaceHolder;
        LinearLayout lnrPlaceNameHolder;
        ImageView imgMovie;
        TextView txtMovieName;

        public MovieViewHolder(View itemView) {
            super(itemView);

            lnrPlaceHolder = (LinearLayout) itemView.findViewById(R.id.lnr_mainHolder);
            lnrPlaceNameHolder = (LinearLayout) itemView.findViewById(R.id.lnr_placeNameHolder);
            imgMovie = (ImageView) itemView.findViewById(R.id.img_movie_poster);
            txtMovieName = (TextView) itemView.findViewById(R.id.txt_movie_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}