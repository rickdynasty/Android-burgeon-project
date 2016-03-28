package com.tencent.tws.burgeon.animation.apidemos;

import android.app.TwsActivity;
import android.os.Bundle;

import com.tencent.tws.burgeon.widget.TwsLoadingView;
import com.tencent.tws.devicemanager.R;

public class TwsLoadingViewActivity extends TwsActivity {
	private TwsLoadingView mTwsLoadingView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loadingview);
		mTwsLoadingView = (TwsLoadingView) findViewById(R.id.animation_LoadingView);
	}
}
