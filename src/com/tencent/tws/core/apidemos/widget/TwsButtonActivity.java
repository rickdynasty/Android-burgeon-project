package com.tencent.tws.core.apidemos.widget;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Switch;

import com.tencent.tws.burgeon.R;
import com.tencent.tws.core.widget.TwsButton;

public class TwsButtonActivity extends Activity implements OnClickListener {

	TwsButton circle_light_green;
	TwsButton circle_dark_green;
	TwsButton rectangle_light_green;
	TwsButton rectangle_dark_green;
	TwsButton circle_dark_red;
	private boolean mIsEnable = true;
	TwsButton mActionButton;

	private int mFlag = 0;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_tws_button);
		circle_light_green = (TwsButton) findViewById(R.id.circle_light_green);
		circle_dark_green = (TwsButton) findViewById(R.id.circle_dark_green);
		rectangle_light_green = (TwsButton) findViewById(R.id.rectangle_light_green);
		rectangle_dark_green = (TwsButton) findViewById(R.id.rectangle_dark_green);
		circle_dark_red = (TwsButton) findViewById(R.id.circle_dark_red);
		mActionButton = (TwsButton) findViewById(R.id.action);
		mActionButton.setOnClickListener(this);
		((TwsButton) findViewById(R.id.action2)).setOnClickListener(this);
		setTwsButtonEnabled(mIsEnable);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.action:
			mIsEnable = !mIsEnable;
			setTwsButtonEnabled(mIsEnable);
			break;
		case R.id.action2:
			dillAction2();
			break;
		default:
			break;
		}
	}

	private void dillAction2() {
		switch (mFlag) {
		case 0:
			circle_light_green.setBorderColors(Color.LTGRAY, Color.GREEN);
			circle_light_green.setFillColor(Color.BLUE);
			break;
		case 1:
			circle_dark_green.setBorderColors(Color.GRAY, Color.GREEN);
			circle_dark_green.setFillColor(Color.GREEN);
			break;
		case 2:
			rectangle_light_green.setBorderColors(Color.MAGENTA, Color.DKGRAY);
			rectangle_light_green.setFillColor(Color.CYAN);
			break;
		case 3:
			rectangle_dark_green.setBorderColors(Color.BLUE, Color.WHITE);
			rectangle_dark_green.setFillColor(Color.LTGRAY);
			break;
		case 4:
			circle_dark_red.setBorderColors(Color.RED, Color.WHITE);
			circle_dark_red.setFillColor(Color.YELLOW);
			break;

		}
		mFlag++;
	}

	private void setTwsButtonEnabled(boolean enable) {
		if (enable) {
			mActionButton.setText("Set to UnEnabled");
		} else {
			mActionButton.setText("Set to Enabled");
		}
		circle_light_green.setEnabled(enable);
		circle_dark_green.setEnabled(enable);
		rectangle_light_green.setEnabled(enable);
		rectangle_dark_green.setEnabled(enable);
	}
}
