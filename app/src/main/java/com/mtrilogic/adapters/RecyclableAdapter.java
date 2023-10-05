package com.mtrilogic.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtrilogic.abstracts.Model;
import com.mtrilogic.abstracts.Recyclable;
import com.mtrilogic.interfaces.RecyclableAdapterListener;

import java.util.List;

public class RecyclableAdapter extends RecyclerView.Adapter<Recyclable<? extends Model>> {

    private final RecyclableAdapterListener listener;
    private final LayoutInflater inflater;

    public RecyclableAdapter(LayoutInflater inflater, RecyclableAdapterListener listener){
        this.inflater = inflater;
        this.listener = listener;
        setHasStableIds(true);
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
        holder.bindModel(getModel(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        return getModel(position).getViewType();
    }

    @Override
    public long getItemId(int position) {
        return getModel(position).getItemId();
    }

    @Override
    public int getItemCount() {
        return getModelList().size();
    }

    private List<Model> getModelList(){
        return listener.getModelListable().getList();
    }

    private Model getModel(int position){
        return getModelList().get(position);
    }
}
