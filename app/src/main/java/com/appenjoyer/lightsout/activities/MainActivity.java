package com.appenjoyer.lightsout.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.Toast;

import com.appenjoyer.lightsout.views.ItemView;
import com.appenjoyer.lightsout.views.LightsGridView;
import com.appenjoyer.lightsout.Interfaces.OnToggledListener;
import com.appenjoyer.lightsout.R;

public class MainActivity extends AppCompatActivity implements OnToggledListener {

    LightsGridView mGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridLayout = (LightsGridView) findViewById(R.id.mGrid);

        int numOfCol = mGridLayout.getColumnCount();
        int numOfRow = mGridLayout.getRowCount();
        for (int yPos = 0; yPos < numOfRow; yPos++) {
            for (int xPos = 0; xPos < numOfCol; xPos++) {
                ItemView tView = new ItemView(this, xPos, yPos);
                tView.setOnToggledListener(this);
                mGridLayout.addView(tView);
            }
        }
        mGridLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final int MARGIN = 5;
                int pWidth = mGridLayout.getWidth();
                int pHeight = mGridLayout.getHeight();
                int numOfCol = mGridLayout.getColumnCount();
                int numOfRow = mGridLayout.getRowCount();
                int w = pWidth / numOfCol;
                int h = pHeight / numOfRow;

                for (int yPos = 0; yPos < numOfRow; yPos++) {
                    for (int xPos = 0; xPos < numOfCol; xPos++) {
                        GridLayout.LayoutParams params =
                                (GridLayout.LayoutParams) mGridLayout.getChildAt(xPos, yPos).getLayoutParams();
                        params.width = w - 2 * MARGIN;
                        params.height = h - 2 * MARGIN;
                        params.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);
                        mGridLayout.getChildAt(xPos, yPos).setLayoutParams(params);
                    }
                }
            }
        });
    }

    @Override
    public void OnToggled(ItemView v, boolean touchOn) {
        Toast.makeText(MainActivity.this, String.valueOf(mGridLayout.getColumnCount()), Toast.LENGTH_SHORT).show();
    }

}