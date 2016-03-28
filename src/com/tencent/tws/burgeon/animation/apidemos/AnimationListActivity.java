package com.tencent.tws.burgeon.animation.apidemos;

import android.app.TwsActivity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.tencent.tws.devicemanager.R;

public class AnimationListActivity extends TwsActivity implements OnClickListener {
	private ImageView mImageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_animation_list);

		mImageView = (ImageView) findViewById(R.id.animationImageView);
		((Button) findViewById(R.id.btn_action)).setOnClickListener(this);
		((Button) findViewById(R.id.btn_stop)).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		AnimationDrawable animationDrawable;
		switch (view.getId()) {
		case R.id.btn_action:
			mImageView.setImageResource(R.drawable.animationlist_loading);
			animationDrawable = (AnimationDrawable) mImageView.getDrawable();
			animationDrawable.start();
			break;
		case R.id.btn_stop:
			animationDrawable = (AnimationDrawable) mImageView.getDrawable();
			animationDrawable.stop();
			break;

		default:
			break;
		}
	}
}
