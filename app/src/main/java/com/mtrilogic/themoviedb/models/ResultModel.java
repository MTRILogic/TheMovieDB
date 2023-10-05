package com.mtrilogic.themoviedb.models;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.ModelCreator;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.themoviedb.pojos.Movie;
import com.mtrilogic.themoviedb.pojos.Result;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ResultModel extends Model {
   public static final Creator<ResultModel> CREATOR = new ModelCreator<ResultModel>() {
        @Override
        protected ResultModel createFromData(Bundle data) {
            return new ResultModel(data);
        }

        @Override
        public ResultModel[] newArray(int size) {
            return new ResultModel[size];
        }
    };

    private static final String
            PAGE = "page",
            RESULTS = "results",
            TOTAL_PAGES = "totalPages",
            TOTAL_RESULTS = "totalResults";

    private Listable<Model> modelListable;
    private Result result;

    public ResultModel(Result result){
        modelListable = getModelListable(result.results);
        this.result = result;
    }

    private ResultModel(Bundle data){
        super(data);
    }

    public Result getResult() {
        return result;
    }

    public Listable<Model> getModelListable() {
        return modelListable;
    }

    @Override
    protected void restoreFromData(@NonNull Bundle data) {
        super.restoreFromData(data);
        result.page = data.getInt(PAGE);
        modelListable = new Listable<>(data, RESULTS);
        result.results = getMovieList(modelListable.getList());
        result.total_pages = data.getInt(TOTAL_PAGES);
        result.total_results = data.getInt(TOTAL_RESULTS);
    }

    @Override
    protected void saveToData(@NonNull Bundle data) {
        super.saveToData(data);
        data.putInt(PAGE, result.page);
        modelListable.saveToData(data, RESULTS);
        data.putInt(TOTAL_PAGES, result.total_pages);
        data.putInt(TOTAL_RESULTS, result.total_results);
    }

    private List<Movie> getMovieList(ArrayList<Model> resultMovieList){
        List<Movie> movieList = new ArrayList<>();
        if (resultMovieList != null){
            for (Model model : resultMovieList){
                MovieModel m = (MovieModel) model;
                movieList.add(m.getMovie());
            }
        }
        return movieList;
    }

    private Listable<Model> getModelListable(List<Movie> movieList){
        Listable<Model> modelListable = new Listable<>();
        long idx = 0;
        for (Movie movie : movieList){
            MovieModel model = new MovieModel(movie, idx);
            if (modelListable.getList().add(model)){
                idx++;
            }
        }
        modelListable.setIdx(idx);
        return modelListable;
    }
}
