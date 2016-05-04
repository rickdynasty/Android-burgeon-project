package com.tencent.tws.core.apidemos.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tencent.tws.burgeon.R;
import com.tencent.tws.core.widget.TwsButton;

public class TwsButtonActivity extends Activity implements OnClickListener {

	TwsButton circle_light_green;
	TwsButton circle_dark_green;
	TwsButton rectangle_light_green;
	TwsButton rectangle_dark_green;
	private boolean bEnable = true;
	Drawable drawable;

	private boolean bShowThreeButton = true;
	LinearLayout mLinearLayout;
	TwsButton button1;
	TwsButton button2;
	TwsButton button3;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_tws_button);
		circle_light_green = (TwsButton) findViewById(R.id.circle_light_green);
		circle_dark_green = (TwsButton) findViewById(R.id.circle_dark_green);
		rectangle_light_green = (TwsButton) findViewById(R.id.rectangle_light_green);
		rectangle_dark_green = (TwsButton) findViewById(R.id.rectangle_dark_green);
		rectangle_dark_green.setOnClickListener(this);
		Button actionButton = (Button) findViewById(R.id.action);
		actionButton.setOnClickListener(this);
		mLinearLayout = (LinearLayout) findViewById(R.id.buttonPanel);
		mLinearLayout.setOrientation(LinearLayout.VERTICAL);

		((TwsButton) findViewById(R.id.user_defined)).setOnClickListener(this);
		button1 = (TwsButton) findViewById(R.id.button1);
		button1.setButtonMode(TwsButton.RECTANGLE_LIGHT_GREEN_MODE);
		button1.setOnClickListener(this);

		button2 = (TwsButton) findViewById(R.id.button2);
		button2.setButtonMode(TwsButton.RECTANGLE_LIGHT_GREEN_MODE);
		button2.setOnClickListener(this);

		button3 = (TwsButton) findViewById(R.id.button3);
		// button3.setButtonMode(TwsButton.RECTANGLE_LIGHT_GREEN_MODE);
		button3.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.action:
			bEnable = !bEnable;
			circle_light_green.setEnabled(bEnable);
			circle_dark_green.setEnabled(bEnable);
			rectangle_light_green.setEnabled(bEnable);
			rectangle_dark_green.setEnabled(bEnable);
			break;
		case R.id.button1:
			drawable.setBounds(null);
			break;
		case R.id.button2:
		case R.id.button3:
			if (bShowThreeButton) {
				bShowThreeButton = false;
				button2.setVisibility(View.GONE);
				button1.setButtonMode(TwsButton.CIRCLE_LIGHT_GREEN_MODE);
				button3.setButtonMode(TwsButton.CIRCLE_LIGHT_GREEN_MODE);

				mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
			} else {

				bShowThreeButton = true;
				button2.setVisibility(View.VISIBLE);

				mLinearLayout.setOrientation(LinearLayout.VERTICAL);
				button1.setButtonMode(TwsButton.RECTANGLE_LIGHT_GREEN_MODE);
				button2.setButtonMode(TwsButton.RECTANGLE_LIGHT_GREEN_MODE);
				button3.setButtonMode(TwsButton.RECTANGLE_LIGHT_GREEN_MODE);
			}
			break;
		case R.id.user_defined:
			rectangle_light_green.setBorderColors(Color.BLUE, Color.WHITE);
			rectangle_light_green.setFillColor(Color.GREEN);
			break;
		default:
			break;
		}
	}
}
