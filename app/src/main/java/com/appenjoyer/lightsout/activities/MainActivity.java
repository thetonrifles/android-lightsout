package com.appenjoyer.lightsout.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.Toast;

import com.appenjoyer.lightsout.Interfaces.OnToggledListener;
import com.appenjoyer.lightsout.R;
import com.appenjoyer.lightsout.views.ItemView;
import com.appenjoyer.lightsout.views.LightsOutBoard;

public class MainActivity extends AppCompatActivity implements OnToggledListener {

    private LightsOutBoard mLightsOutBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLightsOutBoard = (LightsOutBoard) findViewById(R.id.grid);

        for (int xPos = 0; xPos < mLightsOutBoard.getRowsCount(); xPos++) {
            for (int yPos = 0; yPos < mLightsOutBoard.getColumnsCount(); yPos++) {
                ItemView tView = new ItemView(this, xPos, yPos);
                tView.setOnToggledListener(this);
                mLightsOutBoard.addItemView(tView);
            }
        }
        mLightsOutBoard.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final int MARGIN = 5;
                int pWidth = mLightsOutBoard.getWidth();
                int pHeight = mLightsOutBoard.getHeight();
                int numOfCol = mLightsOutBoard.getColumnsCount();
                int numOfRow = mLightsOutBoard.getRowsCount();
                int w = pWidth / numOfCol;
                int h = pHeight / numOfRow;

                for (int yPos = 0; yPos < numOfRow; yPos++) {
                    for (int xPos = 0; xPos < numOfCol; xPos++) {
                        GridLayout.LayoutParams params =
                                (GridLayout.LayoutParams) mLightsOutBoard.getChildAt(xPos, yPos).getLayoutParams();
                        params.width = w - 2 * MARGIN;
                        params.height = h - 2 * MARGIN;
                        params.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);
                        mLightsOutBoard.getChildAt(xPos, yPos).setLayoutParams(params);
                    }
                }
            }
        });
    }

    @Override
    public void OnToggled(ItemView v, boolean touchOn) {
        String text = v.getRowIndex() + " - " + v.getColumnIndex();
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}