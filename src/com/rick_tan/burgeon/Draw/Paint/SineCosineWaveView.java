package com.rick_tan.burgeon.Draw.Paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

import com.rickdynasty.tws.core.utils.UiUtils;

/**
 * Created by yongchen on 2016/1/13.
 */
public class SineCosineWaveView extends View {
    // Wave paint color
    private static final int WAVE_PAINT_COLOR = 0x880000aa;
    // y = Asin(wx+b)+h
    private static final float STRETCH_FACTOR_A = 20;
    private static final int OFFSET_Y = 0;
    // first water wave speed
    private static final int TRANSLATE_X_SPEED_ONE = 7;
    // second water wave speed
    private static final int TRANSLATE_X_SPEED_TWO = 5;
    private float mCycleFactorW;

    private int mTotalWidth, mTotalHeight;
    private float[] mYPositions;
    private float[] mResetOneYPositions;
    private float[] mResetTwoYPositions;
    private int mXOffsetSpeedOne;
    private int mXOffsetSpeedTwo;
    private int mXOneOffset;
    private int mXTwoOffset;

    private Paint mWavePaint;
    private DrawFilter mDrawFilter;

    public SineCosineWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // chang dpto px, Used to control the movement speed on different resolutions are basically identical
        mXOffsetSpeedOne = UiUtils.dipToPx(context, TRANSLATE_X_SPEED_ONE);
        mXOffsetSpeedTwo = UiUtils.dipToPx(context, TRANSLATE_X_SPEED_TWO);

        mWavePaint = new Paint();
        // Remove the Paint sawtooth
        mWavePaint.setAntiAlias(true);
        mWavePaint.setStyle(Paint.Style.FILL);
        mWavePaint.setColor(WAVE_PAINT_COLOR);
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //remove drawing sawtooth from the aspect of canvas
        canvas.setDrawFilter(mDrawFilter);
        resetPositonY();
        for (int i = 0; i < mTotalWidth; i++) {

            // Control drawings of y ripple in the position of the screen
            // draw first wave
            canvas.drawLine(i, mTotalHeight - mResetOneYPositions[i] - 400, i,
                    mTotalHeight,
                    mWavePaint);

            // draw second wave
            canvas.drawLine(i, mTotalHeight - mResetTwoYPositions[i] - 400, i,
                    mTotalHeight,
                    mWavePaint);
        }

        // change two wave offset
        mXOneOffset += mXOffsetSpeedOne;
        mXTwoOffset += mXOffsetSpeedTwo;

        // if have moved to the end, start recording
        if (mXOneOffset >= mTotalWidth) {
            mXOneOffset = 0;
        }
        if (mXTwoOffset > mTotalWidth) {
            mXTwoOffset = 0;
        }

        // postInvalidate, Can consider to late 20 s
        postInvalidate();
    }

    private void resetPositonY() {
        // mXOneOffset is the first water ripple the distance to move
        int yOneInterval = mYPositions.length - mXOneOffset;
        // use System.arraycopy refill the first wave data
        System.arraycopy(mYPositions, mXOneOffset, mResetOneYPositions, 0, yOneInterval);
        System.arraycopy(mYPositions, 0, mResetOneYPositions, yOneInterval, mXOneOffset);

        int yTwoInterval = mYPositions.length - mXTwoOffset;
        System.arraycopy(mYPositions, mXTwoOffset, mResetTwoYPositions, 0, yTwoInterval);
        System.arraycopy(mYPositions, 0, mResetTwoYPositions, yTwoInterval, mXTwoOffset);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // view with
        mTotalWidth = w;
        mTotalHeight = h;
        // Used to hold the original wave y value
        mYPositions = new float[mTotalWidth];
        // save the first wave y value
        mResetOneYPositions = new float[mTotalWidth];
        // save second first wave y value
        mResetTwoYPositions = new float[mTotalWidth];

        // The cycle as the total width of the view
        mCycleFactorW = (float) (2 * Math.PI / mTotalWidth);

        // get all corresponding y value by the total width of the view
        for (int i = 0; i < mTotalWidth; i++) {
            mYPositions[i] = (float) (STRETCH_FACTOR_A * Math.sin(mCycleFactorW * i) + OFFSET_Y);
        }
    }
}
