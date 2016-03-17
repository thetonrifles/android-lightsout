package com.appenjoyer.lightsout.views;

/**
 * Created by Joan on 17/03/2016.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.appenjoyer.lightsout.Interfaces.OnToggledListener;

public class ItemView extends View{

    boolean touchOn;
    boolean mDownTouch = false;
    private OnToggledListener toggledListener;
    int _IdRow = 0;
    int _IdColumn = 0;

    public ItemView(Context context, int Rows, int Columns) {
        super(context);
        this._IdRow = Rows;
        this._IdColumn = Columns;
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
        touchOn = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (touchOn) {
            canvas.drawColor(Color.RED);
        } else {
            canvas.drawColor(Color.GRAY);
        }
    }
    //onClick not possible to use on custom View so, onTouchEvent is the solution
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            //if Click
            case MotionEvent.ACTION_DOWN:
                touchOn = !touchOn;
                invalidate();
                if(toggledListener != null){
                    toggledListener.OnToggled(this, touchOn);
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
        toggledListener = listener;
    }

    public int get_IdRow() {
        return _IdRow;
    }

    public int get_IdColumn() {
        return _IdColumn;
    }

}
