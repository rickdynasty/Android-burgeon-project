package com.rick_tan.burgeon.Draw.PorterDuff;

import android.app.TwsActivity;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.rickdynasty.tws.burgeon.R;

/**
 * Created by yongchen on 2015/12/21.
 */
public class TintActivity extends TwsActivity {

    ImageView mTintDstView1;
    ImageView mTintDstView2;
    ImageView mTintDstView3;
    ImageView mTintDstView4;
    ImageView mTintSrcView1;
    ImageView mTintSrcView2;
    ImageView mTintSrcView3;
    ImageView mTintSrcView4;
    ImageView mTintOkView1;
    ImageView mTintOkView2;
    ImageView mTintOkView3;
    ImageView mTintOkView4;

    int mTintSrcColor1 = 0xFF00FFFF;
    int mTintSrcColor2 = 0xFFFF0000;
    int mTintSrcColor3 = 0xFF0000FF;
    int mTintSrcColor4 = 0xFFFFFF00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proterduff_tint);
        setupViews();
    }

    void setupViews() {
        mTintDstView1 = (ImageView) findViewById(R.id.tint_dst1);
        mTintDstView2 = (ImageView) findViewById(R.id.tint_dst2);
        mTintDstView3 = (ImageView) findViewById(R.id.tint_dst3);
        mTintDstView4 = (ImageView) findViewById(R.id.tint_dst4);
        mTintSrcView1 = (ImageView) findViewById(R.id.tint_src1);
        mTintSrcView2 = (ImageView) findViewById(R.id.tint_src2);
        mTintSrcView3 = (ImageView) findViewById(R.id.tint_src3);
        mTintSrcView4 = (ImageView) findViewById(R.id.tint_src4);
        mTintOkView1 = (ImageView) findViewById(R.id.tint_ok1);
        mTintOkView2 = (ImageView) findViewById(R.id.tint_ok2);
        mTintOkView3 = (ImageView) findViewById(R.id.tint_ok3);
        mTintOkView4 = (ImageView) findViewById(R.id.tint_ok4);

        mTintDstView1.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_check_off));
        mTintDstView2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_check_on));
        mTintDstView3.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_radio_off));
        mTintDstView4.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_radio_on));

        mTintSrcView1.setBackgroundColor(mTintSrcColor1);
        mTintSrcView2.setBackgroundColor(mTintSrcColor2);
        mTintSrcView3.setBackgroundColor(mTintSrcColor3);
        mTintSrcView4.setBackgroundColor(mTintSrcColor4);

        Drawable dstDrawable1 = getResources().getDrawable(R.drawable.btn_check_off);
        dstDrawable1.setColorFilter(mTintSrcColor1, PorterDuff.Mode.SRC_IN);
        Drawable dstDrawable2 = getResources().getDrawable(R.drawable.btn_check_on);
        dstDrawable2.setColorFilter(mTintSrcColor2, PorterDuff.Mode.SRC_IN);
        Drawable dstDrawable3 = getResources().getDrawable(R.drawable.btn_radio_off);
        dstDrawable3.setColorFilter(mTintSrcColor3, PorterDuff.Mode.SRC_IN);
        Drawable dstDrawable4 = getResources().getDrawable(R.drawable.btn_radio_on);
        dstDrawable4.setColorFilter(mTintSrcColor4, PorterDuff.Mode.SRC_IN);

        mTintOkView1.setImageDrawable(dstDrawable1);
        mTintOkView2.setImageDrawable(dstDrawable2);
        mTintOkView3.setImageDrawable(dstDrawable3);
        mTintOkView4.setImageDrawable(dstDrawable4);
    }

}
