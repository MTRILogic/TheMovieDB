package com.mtrilogic.interfaces;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.classes.Listable;

public interface RecyclableAdapterListener extends OnMakeToastListener {

    @NonNull Recyclable<? extends Model> getRecyclable(int viewType, LayoutInflater inflater, ViewGroup parent);

    @NonNull Listable<Model> getModelListable();
}
