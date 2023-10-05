package com.mtrilogic.abstracts;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class ModelCreator<M extends Model> implements Parcelable.ClassLoaderCreator<M>{

    protected abstract M createFromData(Bundle data);

    @Override
    public M createFromParcel(Parcel source, ClassLoader loader) {
        if (source != null && loader != null){
            return createFromData(source.readBundle(loader));
        }
        return createFromData(null);
    }

    @Override
    public M createFromParcel(Parcel source) {
        return createFromData(null);
    }
}
