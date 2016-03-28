package com.tencent.tws.burgeon.widget;

import com.tencent.tws.devicemanager.R;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class TwsLoadingView extends ImageView {

	public TwsLoadingView(Context context) {
		this(context, null);
	}

	public TwsLoadingView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TwsLoadingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		setImageDrawable(getResources().getDrawable(R.drawable.animationlist_loading));

		AnimationDrawable animationDrawable = (AnimationDrawable) getDrawable();
		animationDrawable.start();

		AnimationSet animationSet = new AnimationSet(true);

		Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_loading);
		LinearInterpolator lin = new LinearInterpolator();
		animation.setInterpolator(lin);

		if (animation != null) {
			animationSet.addAnimation(animation);
			startAnimation(animationSet);
		}
	}

}
