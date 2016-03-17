package com.appenjoyer.lightsout.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.appenjoyer.lightsout.Interfaces.OnToggledListener;

public class ItemView extends View{

    private boolean mTouchOn;
    private boolean mDownTouch = false;
    private OnToggledListener mListener;
    private int mRowIndex = 0;
    private int mColumnIndex = 0;

    public ItemView(Context context, int Rows, int Columns) {
        super(context);
        this.mRowIndex = Rows;
        this.mColumnIndex = Columns;
        init();
    }

    public ItemView(Context context) {
        super(context);
        init();
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchOn = !mTouchOn;
                invalidate();
                if(mListener != null){
                    mListener.OnToggled(this, mTouchOn);
                }
                mDownTouch = true;
                return true;

            case MotionEvent.ACTION_UP:
                if (mDownTouch) {
                    mDownTouch = false;
                    performClick();
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    public void setOnToggledListener(OnToggledListener listener){
        mListener = listener;
    }

    public int getRowIndex() {
        return mRowIndex;
    }

    public int getColumnIndex() {
        return mColumnIndex;
    }

}
