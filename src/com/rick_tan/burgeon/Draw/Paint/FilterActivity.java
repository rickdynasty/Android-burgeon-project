package com.rick_tan.burgeon.Draw.Paint;

import android.app.TwsActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tencent.tws.burgeon.R;

/**
 * Created by yongchen on 2016/1/13.
 */
public class FilterActivity extends TwsActivity implements SeekBar.OnSeekBarChangeListener {
    private ColorFilterView filterView;
    private SeekBar mRedSeekBar;
    private SeekBar mGreenSeekBar;
    private SeekBar mBlueSeekBar;
    private SeekBar mAlphaSeekBar;
    private float mRedSeek;
    private float mGreenSeek;
    private float mBlueSeek;
    private float mAlphaSeek;
    private TextView mVlaue_filter_red;
    private TextView mVlaue_filter_green;
    private TextView mVlaue_filter_blue;
    private TextView mVlaue_filter_alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_filter_activity);
        filterView = (ColorFilterView) findViewById(R.id.filter_bitmap_view);

        //TextView
        mVlaue_filter_red = (TextView) findViewById(R.id.vlaue_filter_red);
        mVlaue_filter_green = (TextView) findViewById(R.id.vlaue_filter_green);
        mVlaue_filter_blue = (TextView) findViewById(R.id.vlaue_filter_blue);
        mVlaue_filter_alpha = (TextView) findViewById(R.id.vlaue_filter_alpha);

        mRedSeekBar = (SeekBar) findViewById(R.id.red_seekBar);
        mRedSeekBar.setOnSeekBarChangeListener(this);
        mRedSeekBar.setMax(200);
        mRedSeekBar.setProgress(100);

        mGreenSeekBar = (SeekBar) findViewById(R.id.green_seekBar);
        mGreenSeekBar.setOnSeekBarChangeListener(this);
        mGreenSeekBar.setMax(200);
        mGreenSeekBar.setProgress(100);

        mBlueSeekBar = (SeekBar) findViewById(R.id.blue_seekBar);
        mBlueSeekBar.setOnSeekBarChangeListener(this);
        mBlueSeekBar.setMax(200);
        mBlueSeekBar.setProgress(100);

        mAlphaSeekBar = (SeekBar) findViewById(R.id.alpha_seekBar);
        mAlphaSeekBar.setOnSeekBarChangeListener(this);
        mAlphaSeekBar.setMax(200);
        mAlphaSeekBar.setProgress(100);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float filter = 1.0f;
        if (progress <= 100) {
            filter = (float) progress / 100;
        } else {
            filter = 1 + (float) (progress - 100) / 10;
        }
        Log.d("rick_Print:", "onProgressChanged filter=" + filter);
        if (seekBar == mRedSeekBar) {
            mRedSeek = filter;
            mVlaue_filter_red.setText("R:" + filter);
        } else if (seekBar == mGreenSeekBar) {
            mGreenSeek = filter;
            mVlaue_filter_green.setText("G:" + filter);
        } else if (seekBar == mBlueSeekBar) {
            mBlueSeek = filter;
            mVlaue_filter_blue.setText("B:" + filter);
        } else if (seekBar == mAlphaSeekBar) {
            mAlphaSeek = filter;
            mVlaue_filter_alpha.setText("A:" + filter);
        }
        filterView.setArgb(mAlphaSeek, mRedSeek, mGreenSeek, mBlueSeek);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }
}
