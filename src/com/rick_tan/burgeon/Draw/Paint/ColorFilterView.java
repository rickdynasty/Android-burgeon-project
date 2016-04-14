package com.rick_tan.burgeon.Draw.Paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.tencent.tws.burgeon.R;

public class ColorFilterView extends View {
	private Bitmap mBitmap;
	private Paint mPaint;
	private float mRedFilter;
	private float mGreenFilter;
	private float mBlueFilter;
	private float mAlphaFilter;
	private ColorMatrix mColorMatrix;
	private int burgeon_image_view_w;
	private int burgeon_image_view_h;

	public ColorFilterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ColorFilterView(Context context) {
		super(context);
		init();
	}

	public ColorFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mPaint = new Paint();
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meinv);
		burgeon_image_view_w = getResources().getDimensionPixelSize(R.dimen.burgeon_image_view_w);
		burgeon_image_view_h = getResources().getDimensionPixelSize(R.dimen.burgeon_image_view_h);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (mBitmap == null || mBitmap.isRecycled())
			return;

		int bW = mBitmap.getWidth();
		int bH = mBitmap.getHeight();
		float scal = Math.max(1.0f, Math.min(burgeon_image_view_w / (float) bW, burgeon_image_view_h / (float) bH));
		bW = (int) (bW * scal);
		bH = (int) (bH * scal);
		int left = (burgeon_image_view_w - bW) / 2;
		// canvas.drawBitmap(mBitmap, left, 0, mPaint);
		canvas.drawBitmap(mBitmap, null, new Rect(left, 0, left + bW, bH), mPaint);
	}

	public void setArgb(float alpha, float red, float green, float blue) {
		mRedFilter = red;
		mGreenFilter = green;
		mBlueFilter = blue;
		mAlphaFilter = alpha;
		mColorMatrix = new ColorMatrix(new float[] { mRedFilter, 0, 0, 0, 0, 0, mGreenFilter, 0, 0, 0, 0, 0, mBlueFilter, 0, 0, 0, 0, 0, mAlphaFilter, 0, });
		mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));

		postInvalidate();
	}
}
