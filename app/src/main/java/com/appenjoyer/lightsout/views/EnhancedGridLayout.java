package com.appenjoyer.lightsout.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;

public class EnhancedGridLayout extends GridLayout {

    public EnhancedGridLayout(Context context) {
        super(context);
    }

    public EnhancedGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EnhancedGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public View getChildAt(int row, int column) {
        int index = (getColumnCount() * row) + column;
        return getChildAt(index);
    }

}
