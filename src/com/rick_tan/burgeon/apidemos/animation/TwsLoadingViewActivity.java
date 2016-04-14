package com.rick_tan.burgeon.apidemos.animation;

import android.app.TwsActivity;
import android.graphics.Color;
import android.os.Bundle;

import com.rick_tan.burgeon.widget.TwsLoadingView;
import com.tencent.tws.burgeon.R;

public class TwsLoadingViewActivity extends TwsActivity {
	private TwsLoadingView mTwsLoadingView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loadingview);
		mTwsLoadingView = (TwsLoadingView) findViewById(R.id.animation_LoadingView);
		//use setColorFilter to set show BLUE style 
		mTwsLoadingView.setColorFilter(Color.BLUE);
	}
}
