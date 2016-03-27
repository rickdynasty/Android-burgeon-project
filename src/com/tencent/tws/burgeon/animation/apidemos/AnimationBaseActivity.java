package com.tencent.tws.burgeon.animation.apidemos;

import android.app.TwsActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.tencent.tws.devicemanager.R;

public class AnimationBaseActivity extends TwsActivity implements OnClickListener {
	private ImageView image = null;
	private int width;
	private int height;
	private final long fOneMinute = 1000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_base);

		init();
		image = (ImageView) findViewById(R.id.animationImageView);

		((Button) findViewById(R.id.alphaButton)).setOnClickListener(this);
		((Button) findViewById(R.id.rotateButton)).setOnClickListener(this);
		((Button) findViewById(R.id.scaleButton)).setOnClickListener(this);
		((Button) findViewById(R.id.translateButton)).setOnClickListener(this);
	}

	private void init() {
		if (width <= 0 || height <= 0) {
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			width = dm.widthPixels;
			height = dm.heightPixels;
		}
	}

	@Override
	public void onClick(View view) {
		// 创建一个AnimationSet对象，参数为Boolean型,true表示使用Animation的interpolator，false则是使用自己的
		AnimationSet animationSet = new AnimationSet(true);
		Animation animation = null;
		switch (view.getId()) {
		case R.id.alphaButton:
			animation = new AlphaAnimation(1, 0);
			animation.setDuration(fOneMinute * 2);
			break;
		case R.id.rotateButton:
			animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setDuration(fOneMinute * 2);
			break;
		case R.id.scaleButton:
			animation = new ScaleAnimation(0, 0.1f, 0, 0.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setDuration(fOneMinute);
			break;
		case R.id.translateButton:
			animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setDuration(fOneMinute);
			break;

		default:
			break;
		}

		if (animation != null) {
			animationSet.addAnimation(animation);
			image.startAnimation(animationSet);
		}
	}
}
