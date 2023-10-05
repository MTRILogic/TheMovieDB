package com.mtrilogic.themoviedb.callbacks;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.mtrilogic.abstracts.Model;

public class TaskDiffCallback extends DiffUtil.ItemCallback<Model>{

    @Override
    public boolean areContentsTheSame(@NonNull Model oldItem, @NonNull Model newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areItemsTheSame(@NonNull Model oldItem, @NonNull Model newItem) {
        return oldItem.getItemId() == oldItem.getItemId();
    }
}
