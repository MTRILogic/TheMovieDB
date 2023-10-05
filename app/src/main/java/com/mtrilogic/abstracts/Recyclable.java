package com.mtrilogic.abstracts;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.interfaces.RecyclableListener;

public abstract class Recyclable<M extends Model> extends RecyclerView.ViewHolder {

    protected RecyclableListener listener;
    protected int position;
    protected M model;

    protected abstract void onBindItemView();
    protected abstract void onBindModel();

    private final Class<M> clazz;

    public Recyclable(@NonNull Class<M> clazz, @NonNull View itemView, @NonNull RecyclableListener listener){
        super(itemView);
        this.listener = listener;
        this.clazz = clazz;
    }

    public void bindItemView(){
        onBindItemView();
    }

    public void bindModel(@NonNull Model model, int position){
        this.model = clazz.cast(model);
        this.position = position;
        onBindModel();
    }
}
