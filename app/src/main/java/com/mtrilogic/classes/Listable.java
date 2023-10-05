package com.mtrilogic.classes;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.mtrilogic.abstracts.Model;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Listable<M extends Model> {

    private static final String LIST = "list", IDX = "idx";

    private ArrayList<M> list;
    private long idx;

    public Listable(){
        list = new ArrayList<>();
    }

    public Listable(@NonNull ArrayList<M> list, long idx){
        this.list = list;
        this.idx = idx;
    }

    public Listable(@NonNull Bundle data){
        list = data.getParcelableArrayList(LIST);
        if (list != null){
            idx = data.getLong(IDX);
        }else {
            list = new ArrayList<>();
        }
    }

    public Listable(@NonNull Bundle data, @NonNull String key){
        list = data.getParcelableArrayList(LIST + key);
        if (list != null){
            idx = data.getLong(IDX + key);
        }else {
            list = new ArrayList<>();
        }
    }

    @NonNull
    public ArrayList<M> getList() {
        return list;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public long getIdx() {
        return idx;
    }

    public void saveToData(@NonNull Bundle data){
        data.putParcelableArrayList(LIST, list);
        data.putLong(IDX, idx);
    }

    public void saveToData(@NonNull Bundle data, @NonNull String key){
        data.putParcelableArrayList(LIST + key, list);
        data.putLong(IDX + key, idx);
    }
}
