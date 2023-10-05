package com.mtrilogic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.mtrilogic.themoviedb.R;

@SuppressWarnings("unused")
public class SquareImageView extends AppCompatImageView {
    private final boolean portrait;

    public SquareImageView(Context context, boolean portrait) {
        super(context);
        this.portrait = portrait;
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SquareImageView);
        portrait = typedArray.getBoolean(R.styleable.SquareImageView_portrait, false);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int side = portrait ? getMeasuredHeight() : getMeasuredWidth();
        setMeasuredDimension(side, side);
    }
}
