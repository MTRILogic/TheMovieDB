package com.mtrilogic.themoviedb.services;

import com.mtrilogic.themoviedb.pojos.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {

    @GET("discover/movie?api_key=d30e1f350220f9aad6c4110df385d380")
    Call<Result> getMovies();
}
