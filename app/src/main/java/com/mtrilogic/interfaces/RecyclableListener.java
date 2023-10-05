package com.mtrilogic.interfaces;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.adapters.RecyclableAdapter;
import com.mtrilogic.adapters.RecyclableDiffAdapter;
import com.mtrilogic.classes.Listable;

@SuppressWarnings("unused")
public interface RecyclableListener extends OnMakeToastListener {

    @NonNull RecyclableDiffAdapter getRecyclableDiffAdapter();

    @NonNull RecyclableAdapter getRecyclableAdapter();

    @NonNull Listable<Model> getModelListable();

    @NonNull RecyclerView getRecyclerView();
}
