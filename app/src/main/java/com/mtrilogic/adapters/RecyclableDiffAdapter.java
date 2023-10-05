package com.mtrilogic.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.RecyclableAdapterListener;

import java.util.List;

public class RecyclableDiffAdapter extends ListAdapter<Model, Recyclable<? extends Model>> {

    private final RecyclableAdapterListener listener;
    private final LayoutInflater inflater;

    public RecyclableDiffAdapter(@NonNull DiffUtil.ItemCallback<Model> diffCallback, LayoutInflater inflater, RecyclableAdapterListener listener) {
        super(diffCallback);
        this.inflater = inflater;
        this.listener = listener;
    }

    /*/public RecyclableDiffAdapter(@NonNull AsyncDifferConfig<Model> config, LayoutInflater inflater, RecyclableAdapterListener listener) {
        super(config);
        this.inflater = inflater;
        this.listener = listener;
    }*/

    @Override
    public int getItemCount() {
        return getCurrentList().size();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getItemId();
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    protected Model getItem(int position) {
        return getCurrentList().get(position);
    }

    @NonNull
    @Override
    public List<Model> getCurrentList() {
        return listener.getModelListable().getList();
    }

    @Override
    public void onCurrentListChanged(@NonNull List<Model> previousList, @NonNull List<Model> currentList) {
        super.onCurrentListChanged(previousList, currentList);
    }

    @NonNull
    @Override
    public Recyclable<? extends Model> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Recyclable<? extends Model> recyclable = listener.getRecyclable(viewType, inflater, parent);
        recyclable.bindItemView();
        return recyclable;
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclable<? extends Model> holder, int position) {
        holder.bindModel(getItem(position), position);
    }
}
