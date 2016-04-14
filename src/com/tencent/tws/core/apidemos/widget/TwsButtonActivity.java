package com.tencent.tws.core.apidemos.widget;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tencent.tws.burgeon.R;
import com.tencent.tws.core.widget.TwsButton;

public class TwsButtonActivity extends Activity implements OnClickListener {

	TwsButton circle_light_green;
	TwsButton circle_dark_green;
	TwsButton rectangle_light_green;
	TwsButton rectangle_dark_green;
	private boolean bEnable = true;
	TwsButton action;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_tws_button);
		circle_light_green = (TwsButton) findViewById(R.id.circle_light_green);
		circle_dark_green = (TwsButton) findViewById(R.id.circle_dark_green);
		rectangle_light_green = (TwsButton) findViewById(R.id.rectangle_light_green);
		rectangle_dark_green = (TwsButton) findViewById(R.id.rectangle_dark_green);
		action = (TwsButton) findViewById(R.id.action);
		action.setOnClickListener(this);
		setTwsButtonEnabled(bEnable);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.action:
			bEnable = !bEnable;
			setTwsButtonEnabled(bEnable);
			break;
		default:
			break;
		}
	}

	private void setTwsButtonEnabled(boolean enable) {
		if (enable) {
			action.setText("Set to UnEnabled");
		} else {
			action.setText("Set to Enabled");
		}
		circle_light_green.setEnabled(enable);
		circle_dark_green.setEnabled(enable);
		rectangle_light_green.setEnabled(enable);
		rectangle_dark_green.setEnabled(enable);
	}
}
