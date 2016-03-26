package com.tencent.tws.burgeon.Draw.PorterDuff;

import android.app.TwsActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import com.tencent.tws.devicemanager.R;

/**
 * Created by yongchen on 2015/12/21.
 */
public class ClipActivity extends TwsActivity {

	ImageView mClipDstView1;
	ImageView mClipDstView2;
	ImageView mClipDstView3;
	ImageView mClipSrcView1;
	ImageView mClipSrcView2;
	ImageView mClipSrcView3;
	ImageView mClipOkView1;
	ImageView mClipOkView2;
	ImageView mClipOkView3;

	static int mSize;
	static final int DEFAULT_SIZE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.proterduff_clip);
		setupViews();
	}

	private void setupViews() {
		WindowManager mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metric = new DisplayMetrics();
		mWindowManager.getDefaultDisplay().getMetrics(metric);
		float density = metric.density;
		mSize = (int) (DEFAULT_SIZE * density);

		Bitmap bitmapDst1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.xiaoye);
		Bitmap bitmapDst2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.tian);
		Bitmap bitmapDst3 = BitmapFactory.decodeResource(getResources(),
				R.drawable.cang);

		Bitmap bitmapSrc1 = makeSrc(1);
		Bitmap bitmapSrc2 = makeSrc(2);
		Bitmap bitmapSrc3 = makeSrc(3);

		mClipDstView1 = (ImageView) findViewById(R.id.clip_dst1);
		mClipDstView2 = (ImageView) findViewById(R.id.clip_dst2);
		mClipDstView3 = (ImageView) findViewById(R.id.clip_dst3);
		mClipSrcView1 = (ImageView) findViewById(R.id.clip_src1);
		mClipSrcView2 = (ImageView) findViewById(R.id.clip_src2);
		mClipSrcView3 = (ImageView) findViewById(R.id.clip_src3);
		mClipOkView1 = (ImageView) findViewById(R.id.clip_ok1);
		mClipOkView2 = (ImageView) findViewById(R.id.clip_ok2);
		mClipOkView3 = (ImageView) findViewById(R.id.clip_ok3);

		mClipDstView1.setImageBitmap(bitmapDst1);
		mClipDstView2.setImageBitmap(bitmapDst2);
		mClipDstView3.setImageBitmap(bitmapDst3);

		mClipSrcView1.setImageBitmap(bitmapSrc1);
		mClipSrcView2.setImageBitmap(bitmapSrc2);
		mClipSrcView3.setImageBitmap(bitmapSrc3);

		mClipOkView1.setImageBitmap(makeBitmap(bitmapDst1, bitmapSrc1));
		mClipOkView2.setImageBitmap(makeBitmap(bitmapDst2, bitmapSrc2));
		mClipOkView3.setImageBitmap(makeBitmap(bitmapDst3, bitmapSrc3));
	}

	// mask
	static Bitmap makeSrc(int style) {
		Bitmap bm = Bitmap.createBitmap(mSize, mSize, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(bm);
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

		p.setColor(0xFF000000);
		switch (style) {
		case 1:
			c.drawOval(new RectF(0, 0, mSize, mSize), p);
			break;
		case 2:
			c.drawRoundRect(new RectF(0, 0, mSize, mSize), 25, 25, p);
			break;
		case 3:
			float centerX = mSize / 2;
			float centerY = mSize / 2;
			float radius = mSize / 2;
			double radian30 = 30 * Math.PI / 180;
			float a = (float) (radius * Math.sin(radian30));
			float b = (float) (radius * Math.cos(radian30));

			Path localPath = new Path();
			localPath.moveTo(centerX, 0);
			localPath.lineTo(centerX + b, centerY - a);
			localPath.lineTo(centerX + b, centerY + a);
			localPath.lineTo(centerX, mSize);
			localPath.lineTo(centerX - b, centerY + a);
			localPath.lineTo(centerX - b, centerY - a);
			localPath.close();
			c.drawPath(localPath, p);
			break;

		default:
			c.drawOval(new RectF(0, 0, mSize, mSize), p);
			break;
		}
		return bm;
	}

	static Bitmap makeBitmap(Bitmap dst, Bitmap src) {
		Bitmap bm = Bitmap.createBitmap(mSize, mSize, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(bm);
		c.drawBitmap(dst, 0, 0, null);
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		c.drawBitmap(src, 0, 0, p);
		return bm;
	}
}
