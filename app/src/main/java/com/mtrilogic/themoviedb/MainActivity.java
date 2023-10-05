package com.mtrilogic.themoviedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.adapters.RecyclableDiffAdapter;
import com.mtrilogic.classes.Listable;
import com.mtrilogic.interfaces.RecyclableAdapterListener;
import com.mtrilogic.interfaces.RecyclableListener;
import com.mtrilogic.themoviedb.callbacks.TaskDiffCallback;
import com.mtrilogic.themoviedb.items.MovieItem;
import com.mtrilogic.themoviedb.models.MovieModel;
import com.mtrilogic.themoviedb.pojos.Movie;
import com.mtrilogic.themoviedb.pojos.Result;
import com.mtrilogic.themoviedb.services.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclableAdapterListener, RecyclableListener {

    private Listable<Model> modelListable;
    private RecyclableDiffAdapter diffAdapter;
    //private RecyclableAdapter adapter;
    private RecyclerView lvwItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("Movies");
        }

        if (savedInstanceState != null){
            modelListable = new Listable<>(savedInstanceState);
        }else {
            modelListable = new Listable<>();
            requestItems();
        }

        diffAdapter = new RecyclableDiffAdapter(new TaskDiffCallback(), getLayoutInflater(), this);
        //adapter = new RecyclableAdapter(getLayoutInflater(), this);

        lvwItems = findViewById(R.id.lvwItems);
        lvwItems.setLayoutManager(new GridLayoutManager(this, 3));
        lvwItems.setAdapter(diffAdapter);
        //lvwItems.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        modelListable.saveToData(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onMakeToast(@NonNull String line) {
        makeToast(line);
    }

    /** @noinspection DataFlowIssue*/
    @NonNull
    @Override
    public Recyclable<? extends Model> getRecyclable(int viewType, LayoutInflater inflater, ViewGroup parent) {
        if (viewType == 1){
            return new MovieItem(inflater, parent, this);
        }
        return null;
    }

    @NonNull
    @Override
    public Listable<Model> getModelListable() {
        return modelListable;
    }

    @NonNull
    @Override
    public RecyclableDiffAdapter getRecyclableDiffAdapter() {
        return diffAdapter;
    }

    /** @noinspection DataFlowIssue*/
    @NonNull
    @Override
    public RecyclableAdapter getRecyclableAdapter() {
        //return adapter;
        return null;
    }

    @NonNull
    @Override
    public RecyclerView getRecyclerView() {
        return lvwItems;
    }

    private void requestItems(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService service = retrofit.create(MovieService.class);

        service.getMovies().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        loadItems(response.body());
                    }
                }else {
                    makeToast(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {
                makeToast(t.getMessage());
            }
        });
    }

    private void loadItems(Result result){
        long idx = 0;
        for (Movie movie : result.results){
            MovieModel model = new MovieModel(movie, idx);
            if (modelListable.getList().add(model)){
                idx++;
            }
        }
        if (idx > 0){
            modelListable.setIdx(idx);
            diffAdapter.submitList(modelListable.getList());
            //adapter.notifyDataSetChanged();
        }
    }

    private void makeToast(String line){
        Toast.makeText(this, line, Toast.LENGTH_LONG).show();
    }
}