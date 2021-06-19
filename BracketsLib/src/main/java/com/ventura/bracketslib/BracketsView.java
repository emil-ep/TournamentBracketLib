package com.ventura.bracketslib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BracketsView extends FrameLayout {

    public BracketsView(Context context) {
        super(context);
        init(context);
    }

    public BracketsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public BracketsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

    }
}
