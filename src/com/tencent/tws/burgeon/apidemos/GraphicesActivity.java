package com.tencent.tws.burgeon.apidemos;

import android.app.TwsActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;

import com.tencent.tws.devicemanager.R;

public class GraphicesActivity extends TwsActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 设置ContentView为自定义的MyVieW */
		MyView myView = new MyView(this);
		setContentView(myView);
	}

	/* 自定义继承View 的MyView */
	private class MyView extends View {
		public MyView(Context context) {
			super(context);
		}

		/* 重写onDraw（） */
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			final int with = getWidth();
			final int height = getHeight();
			if (with <= 0 || height <= 0)
				return;

			final float X_space = (float) with / 10;
			final float Y_space = X_space;
			/* 设置背景为白色 */
			canvas.drawColor(Color.WHITE);
			Paint paint = new Paint();
			/* 去锯齿 */
			paint.setAntiAlias(true);
			/* 设置paint的颜色 */
			paint.setColor(Color.RED);
			/* 设置paint的 style 为STROKE：空心 */
			paint.setStyle(Paint.Style.STROKE);
			/* 设置paint的外框宽度 */
			paint.setStrokeWidth(3);

			float radius = getWidth() / 10;
			final float f_dia = radius * 2;
			float cx = X_space + radius;
			float cy = Y_space + radius;
			float rightEx = 0.0f;
			float left = X_space;
			float top = cy + radius + Y_space;
			float right = left + f_dia;
			float bottom = top + f_dia;
			RectF re;
			Path path;

			// 绘制空心 - 图形
			{
				canvas.drawCircle(cx, cy, radius, paint);
				canvas.drawRect(left, top, right, bottom, paint);

				top = bottom + Y_space;
				bottom = top + radius;
				canvas.drawRect(left, top, right, bottom, paint);

				top = bottom + Y_space;
				bottom = top + radius;
				re = new RectF(left, top, right, bottom);
				canvas.drawOval(re, paint);

				top = bottom + Y_space;
				bottom = top + f_dia;
				rightEx = left + radius;
				path = new Path();
				path.moveTo(left, bottom);
				path.lineTo(right, bottom);
				path.lineTo(rightEx, top);
				path.close();
				canvas.drawPath(path, paint);
			}

			paint.setStyle(Paint.Style.FILL);
			paint.setColor(Color.BLUE);

			// 绘制实心 - 图形
			{
				cx += X_space + f_dia;
				canvas.drawCircle(cx, cy, radius, paint);

				left = right + X_space;
				top = cy + radius + Y_space;
				right = left + f_dia;
				bottom = top + f_dia;
				canvas.drawRect(left, top, right, bottom, paint);

				/* 画一个空心长方形 */
				top = bottom + Y_space;
				bottom = top + radius;
				canvas.drawRect(left, top, right, bottom, paint);

				/* 画一个空心椭圆形 */
				top = bottom + Y_space;
				bottom = top + radius;
				re = new RectF(left, top, right, bottom);
				canvas.drawOval(re, paint);

				/* 画一个空心多边形(eg:三边) */
				top = bottom + Y_space;
				bottom = top + f_dia;
				rightEx = left + radius;
				path = new Path();
				path.moveTo(left, bottom);
				path.lineTo(right, bottom);
				path.lineTo(rightEx, top);
				path.close();
				canvas.drawPath(path, paint);
			}

			Shader mShader = new LinearGradient(0, 0, 100, 100, new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW }, null, Shader.TileMode.REPEAT);
			paint.setShader(mShader);

			// 绘制渐变色 - 图形
			{
				cx += X_space + f_dia;
				canvas.drawCircle(cx, cy, radius, paint);

				left = right + X_space;
				top = cy + radius + Y_space;
				right = left + f_dia;
				bottom = top + f_dia;
				canvas.drawRect(left, top, right, bottom, paint);

				/* 画一个空心长方形 */
				top = bottom + Y_space;
				bottom = top + radius;
				canvas.drawRect(left, top, right, bottom, paint);

				/* 画一个空心椭圆形 */
				top = bottom + Y_space;
				bottom = top + radius;
				re = new RectF(left, top, right, bottom);
				canvas.drawOval(re, paint);

				/* 画一个空心多边形(eg:三边) */
				top = bottom + Y_space;
				bottom = top + f_dia;
				rightEx = left + radius;
				path = new Path();
				path.moveTo(left, bottom);
				path.lineTo(right, bottom);
				path.lineTo(rightEx, top);
				path.close();
				canvas.drawPath(path, paint);
			}
		}
	}
}
