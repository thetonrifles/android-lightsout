package com.appenjoyer.lightsout.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;

import com.appenjoyer.lightsout.R;

public class LightsOutBoard extends FrameLayout implements View.OnClickListener {

    private GridLayout mGridView;
    private int mRowsCount;
    private int mColsCount;
    private OnItemClickListener mOnItemClickListener;

    public LightsOutBoard(Context context) {
        super(context);
        init(context, null);
    }

    public LightsOutBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LightsOutBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LightsOutBoard);
            mRowsCount = typedArray.getInteger(R.styleable.LightsOutBoard_rows_num, 0);
            mColsCount = typedArray.getInteger(R.styleable.LightsOutBoard_cols_num, 0);
            typedArray.recycle();
        }
        View layout = inflate(getContext(), R.layout.view_lights_board, null);
        mGridView = (GridLayout) layout.findViewById(R.id.view_grid);
        mGridView.setRowCount(mRowsCount);
        mGridView.setColumnCount(mColsCount);
        addView(layout);
    }

    public void buildChildren() {
        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < getColumnsCount(); j++) {
                ItemView view = new ItemView(getContext(), i, j);
                view.setOnClickListener(this);
                mGridView.addView(view);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public View getChildAt(int rowIndex, int columnIndex) {
        int index = (getColumnsCount() * rowIndex) + columnIndex;
        return mGridView.getChildAt(index);
    }

    public int getColumnsCount() {
        return mGridView.getColumnCount();
    }

    public int getRowsCount() {
        return mGridView.getRowCount();
    }

    @Override
    public void onClick(View v) {
        if (v instanceof ItemView) {
            ItemView view = (ItemView) v;
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view);
            }
        }
    }

    public interface OnItemClickListener {

        void onItemClick(ItemView view);

    }

}
