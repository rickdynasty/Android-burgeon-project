package com.rick_tan.burgeon.Draw.PorterDuff;

import android.app.TwsActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by yongchen on 2015/12/21.
 */
public class XfermodesActivity extends TwsActivity {

    private static final int OPAQUE_SRC_COLOR = 0xFFFF0000;
    private static final int OPAQUE_DST_COLOR = 0xFF0000FF;
    private static final int PARTIALLY_TRANSPARENT_SRC_COLOR = 0xCCFF0000;
    private static final int PARTIALLY_TRANSPARENT_DST_COLOR = 0xCC0000FF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView contentView = new ScrollView(this);
        contentView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        SampleView opaqueView = new SampleView(this, OPAQUE_SRC_COLOR, OPAQUE_DST_COLOR);
        SampleView partiallyTransparentView = new SampleView(this, PARTIALLY_TRANSPARENT_SRC_COLOR,
                PARTIALLY_TRANSPARENT_DST_COLOR);

        ll.addView(opaqueView, new LayoutParams(LayoutParams.MATCH_PARENT, 1300));
        ll.addView(partiallyTransparentView, new LayoutParams(LayoutParams.MATCH_PARENT, 1300));
        contentView.addView(ll);
        setContentView(contentView);
    }

    private static class SampleView extends View {
        private static final int DEFAULT_W = 64;
        private static final int DEFAULT_H = 64;
        private static int W = 64;
        private static int H = 64;
        private static final int ROW_MAX = 4; // number of samples per row
        private boolean mIsOpaque;

        private Bitmap mSrcB;
        private Bitmap mDstB;
        private Shader mBG; // background checker-board pattern

        private static final Xfermode[] sModes = {
                new PorterDuffXfermode(PorterDuff.Mode.CLEAR),
                new PorterDuffXfermode(PorterDuff.Mode.SRC),
                new PorterDuffXfermode(PorterDuff.Mode.DST),
                new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER),
                new PorterDuffXfermode(PorterDuff.Mode.DST_OVER),
                new PorterDuffXfermode(PorterDuff.Mode.SRC_IN),
                new PorterDuffXfermode(PorterDuff.Mode.DST_IN),
                new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),
                new PorterDuffXfermode(PorterDuff.Mode.DST_OUT),
                new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP),
                new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP),
                new PorterDuffXfermode(PorterDuff.Mode.XOR),
                new PorterDuffXfermode(PorterDuff.Mode.DARKEN),
                new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),
                new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY),
                new PorterDuffXfermode(PorterDuff.Mode.SCREEN)};

        private static final String[] sLabels = {"Clear", "Src", "Dst", "SrcOver", "DstOver", "SrcIn",
                "DstIn", "SrcOut", "DstOut", "SrcATop", "DstATop", "Xor", "Darken", "Lighten", "Multiply",
                "Screen"};

        public SampleView(Context context, int srcColor, int dstColor) {
            super(context);
            WindowManager mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metric = new DisplayMetrics();
            mWindowManager.getDefaultDisplay().getMetrics(metric);
            float density = metric.density;
            W = (int) (DEFAULT_W * density);
            H = (int) (DEFAULT_H * density);
            mSrcB = makeSrc(W, H, srcColor);
            mDstB = makeDst(W, H, dstColor);

            // make a ckeckerboard pattern
            Bitmap bm = Bitmap.createBitmap(new int[]{0xFFFFFFFF, 0xFFCCCCCC, 0xFFCCCCCC, 0xFFFFFFFF}, 2, 2,
                    Bitmap.Config.RGB_565);
            mBG = new BitmapShader(bm, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            Matrix m = new Matrix();
            m.setScale(6, 6);
            mBG.setLocalMatrix(m);

            mIsOpaque = (Color.alpha(srcColor) == 255);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);

            Paint labelP = new Paint(Paint.ANTI_ALIAS_FLAG);
            labelP.setTextAlign(Paint.Align.CENTER);
            labelP.setTextSize(35);

            Paint paint = new Paint();
            paint.setFilterBitmap(false);

            Paint tPaint = new Paint();
            tPaint.setTextAlign(Paint.Align.CENTER);
            tPaint.setTextSize(50);
            tPaint.setColor(Color.RED);
            canvas.drawText(mIsOpaque ? "Opaque" : "Partially Transparent", getWidth() / 2, 75, tPaint);

            canvas.translate(88, 200);

            int x = 0;
            int y = 0;
            for (int i = 0; i < sModes.length; i++) {
                // draw the border
                paint.setStyle(Paint.Style.STROKE);
                paint.setShader(null);
                canvas.drawRect(x - 0.5f, y - 0.5f, x + W + 0.5f, y + H + 0.5f, paint);

                // draw the checker-board pattern
                paint.setStyle(Paint.Style.FILL);
                paint.setShader(mBG);
                canvas.drawRect(x, y, x + W, y + H, paint);

                // draw the src/dst example into our offscreen bitmap
                int sc = canvas.saveLayer(x, y, x + W, y + H, null, Canvas.MATRIX_SAVE_FLAG
                        | Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
                        | Canvas.FULL_COLOR_LAYER_SAVE_FLAG | Canvas.CLIP_TO_LAYER_SAVE_FLAG);
                canvas.translate(x, y);
                canvas.drawBitmap(mDstB, 0, 0, paint);
                paint.setXfermode(sModes[i]);
                canvas.drawBitmap(mSrcB, 0, 0, paint);
                paint.setXfermode(null);
                canvas.restoreToCount(sc);

                // draw the label
                canvas.drawText(sLabels[i], x + W / 2, y - labelP.getTextSize() / 2, labelP);

                x += W + 50;

                // wrap around when we've drawn enough for one row
                if ((i % ROW_MAX) == ROW_MAX - 1) {
                    x = 0;
                    y += H + 70;
                }
            }
        }
    }

    // create a bitmap with a circle, used for the "dst" image
    static Bitmap makeDst(int w, int h, int color) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(color);
        c.drawOval(new RectF(0, 0, w * 3 / 4, h * 3 / 4), p);
        return bm;
    }

    // create a bitmap with a rect, used for the "src" image
    static Bitmap makeSrc(int w, int h, int color) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(color);
        c.drawRect(w / 3, h / 3, w * 19 / 20, h * 19 / 20, p);
        return bm;
    }
}
