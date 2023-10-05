package com.mtrilogic.themoviedb.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("page")
    public int page;
    public List<Movie> results;
    public int total_pages;
    public int total_results;
}
