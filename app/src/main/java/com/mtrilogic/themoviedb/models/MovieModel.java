package com.mtrilogic.themoviedb.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.ModelCreator;
import com.mtrilogic.themoviedb.pojos.Movie;

import java.util.ArrayList;

public class MovieModel extends Model {
    public static final Creator<MovieModel> CREATOR = new ModelCreator<MovieModel>() {
        @Override
        protected MovieModel createFromData(Bundle data) {
            return new MovieModel(data);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    private static final String
            ADULT = "adult",
            BACKDROP_PATH = "backdropPath",
            GENRE_IDS = "genreIds",
            ID = "id",
            ORIGINAL_LANGUAGE = "originalLanguage",
            ORIGINAL_TITLE = "originalTitle",
            OVERVIEW = "overview",
            POPULARITY = "popularity",
            POSTER_PATH = "posterPath",
            RELEASE_DATE = "releaseDate",
            TITLE = "title",
            VIDEO = "video",
            VOTE_AVERAGE = "voteAverage",
            VOTE_COUNT = "voteCount";

    private Movie movie;

    public MovieModel(Movie movie, long itemId){
        super(itemId, 1);
        this.movie = movie;
    }

    private MovieModel(Bundle data){
        super(data);
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    protected void restoreFromData(@NonNull Bundle data) {
        super.restoreFromData(data);
        movie.adult = data.getBoolean(ADULT);
        movie.backdrop_path = data.getString(BACKDROP_PATH);
        movie.genre_ids = data.getIntegerArrayList(GENRE_IDS);
        movie.id = data.getInt(ID);
        movie.original_language = data.getString(ORIGINAL_LANGUAGE);
        movie.original_title = data.getString(ORIGINAL_TITLE);
        movie.overview = data.getString(OVERVIEW);
        movie.popularity = data.getDouble(POPULARITY);
        movie.poster_path = data.getString(POSTER_PATH);
        movie.release_date = data.getString(RELEASE_DATE);
        movie.title = data.getString(TITLE);
        movie.video = data.getBoolean(VIDEO);
        movie.vote_average = data.getDouble(VOTE_AVERAGE);
        movie.vote_count = data.getInt(VOTE_COUNT);
    }

    @Override
    protected void saveToData(@NonNull Bundle data) {
        super.saveToData(data);
        data.putBoolean(ADULT, movie.adult);
        data.putString(BACKDROP_PATH, movie.backdrop_path);
        data.putIntegerArrayList(GENRE_IDS, new ArrayList<>(movie.genre_ids));
        data.putInt(ID, movie.id);
        data.putString(ORIGINAL_LANGUAGE, movie.original_language);
        data.putString(TITLE, movie.title);
        data.putString(OVERVIEW, movie.overview);
        data.putDouble(POPULARITY, movie.popularity);
        data.putString(POSTER_PATH, movie.poster_path);
        data.putString(RELEASE_DATE, movie.release_date);
        data.putString(TITLE, movie.title);
        data.putBoolean(VIDEO, movie.video);
        data.putDouble(VOTE_AVERAGE, movie.vote_average);
        data.putInt(VOTE_COUNT, movie.vote_count);
    }
}
