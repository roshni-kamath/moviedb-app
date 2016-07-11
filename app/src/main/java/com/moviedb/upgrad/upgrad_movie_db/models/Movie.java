package com.moviedb.upgrad.upgrad_movie_db.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hp on 10-07-2016.
 */
@JsonIgnoreProperties(ignoreUnknown =  true)
public class Movie implements Serializable{

    private long id ;
    @JsonProperty("vote_average")
    private float voteAverage ;
    @JsonProperty("popularity")
    private double popularity ;
    @JsonProperty("original_title")
    private String title ;
    @JsonProperty("overview")
    private String overview ;
    @JsonProperty("poster_path")
    private String posterPath ;
    @JsonProperty("backdrop_path")
    private String backdropPath ;
    @JsonProperty("release_date")
    private String releaseDate ;

    public Movie() {
    }

    public Movie(long id, float voteAverage, String title, String overview, String posterPath, String backdropPath, String releaseDate) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
