package com.appenjoyer.lightsout.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class ItemView extends View {

    private boolean mTouchOn;
    private int mRowIndex = 0;
    private int mColumnIndex = 0;

    public ItemView(Context context) {
        super(context);
        init();
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemView(Context context, int Rows, int Columns) {
        super(context);
        mRowIndex = Rows;
        mColumnIndex = Columns;
        init();
    }

    private void init() {
        mTouchOn = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mTouchOn) {
            canvas.drawColor(Color.RED);
        } else {
            canvas.drawColor(Color.GRAY);
        }
    }

    public boolean isTouchOn() {
        return mTouchOn;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        mTouchOn = !mTouchOn;
        invalidate();
        return true;
    }

    public int getRowIndex() {
        return mRowIndex;
    }

    public int getColumnIndex() {
        return mColumnIndex;
    }

}
