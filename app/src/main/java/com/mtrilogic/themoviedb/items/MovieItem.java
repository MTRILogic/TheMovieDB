package com.mtrilogic.themoviedb.items;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.RecyclableListener;
import com.mtrilogic.themoviedb.R;
import com.mtrilogic.themoviedb.models.MovieModel;

public class MovieItem extends Recyclable<MovieModel> {

    private AppCompatImageView ivwImage;
    private TextView lblTitle;

    public MovieItem(@NonNull LayoutInflater inflater, ViewGroup parent, @NonNull RecyclableListener listener) {
        super(MovieModel.class, inflater.inflate(R.layout.item_movie, parent, false), listener);
    }

    @Override
    protected void onBindItemView() {
        lblTitle = itemView.findViewById(R.id.lblTitle);
        lblTitle.setSelected(true); // necesario para que marquee ellipsize trabaje
        ivwImage = itemView.findViewById(R.id.ivwImage);
    }

    @Override
    protected void onBindModel() {
        lblTitle.setText(model.getMovie().title);
        Glide
                .with(itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185/" + model.getMovie().poster_path)
                .centerCrop()
                .placeholder(R.drawable.noimage)
                .into(ivwImage);
    }
}
