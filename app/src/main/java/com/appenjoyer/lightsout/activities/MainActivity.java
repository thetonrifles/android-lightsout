package com.appenjoyer.lightsout.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.appenjoyer.lightsout.R;
import com.appenjoyer.lightsout.views.ItemView;
import com.appenjoyer.lightsout.views.LightsOutBoard;

public class MainActivity extends AppCompatActivity implements LightsOutBoard.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LightsOutBoard board  = (LightsOutBoard) findViewById(R.id.grid);
        board.setOnItemClickListener(this);

        board.buildChildren(3, 3);
    }

    @Override
    public void onItemClick(ItemView view) {
        String text = view.getRowIndex() + " - " + view.getColumnIndex();
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}