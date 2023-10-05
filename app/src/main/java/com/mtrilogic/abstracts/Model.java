package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class Model implements Parcelable {

    private static final String ITEM_ID = "itemId", VIEW_TYPE = "viewType";

    private long itemId;
    private int viewType;

    public Model(long itemId, int viewType){
        this.itemId = itemId;
        this.viewType = viewType;
    }

    public Model(){}

    protected Model(Bundle data){
        if (data != null){
            restoreFromData(data);
        }
    }

    public final long getItemId() {
        return itemId;
    }

    public final int getViewType() {
        return viewType;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    protected void restoreFromData(@NonNull Bundle data){
        itemId = data.getLong(ITEM_ID);
        viewType = data.getInt(VIEW_TYPE);
    }

    protected void saveToData(@NonNull Bundle data){
        data.putLong(ITEM_ID, itemId);
        data.putInt(VIEW_TYPE, viewType);
    }

    @Override
    public final int describeContents() {
        return 0;
    }

    @Override
    public final void writeToParcel(@NonNull Parcel dest, int flags) {
        Bundle data = new Bundle();
        saveToData(data);
        dest.writeBundle(data);
    }
}
