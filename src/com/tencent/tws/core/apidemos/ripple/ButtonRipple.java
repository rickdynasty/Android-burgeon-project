package com.tencent.tws.core.apidemos.ripple;

import android.app.TwsActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.tws.core.utils.TwsRippleUtils;
import com.tencent.tws.burgeon.R;

public class ButtonRipple extends TwsActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.act_btn_ripple);
		Button button1 = (Button) findViewById(R.id.btn1);
		Button button2 = (Button) findViewById(R.id.btn2);
		Button button3 = (Button) findViewById(R.id.btn3);
		Button button4 = (Button) findViewById(R.id.btn4);
		button1.setBackground(TwsRippleUtils.getHasContentDrawable(this, R.drawable.btn_noframe_blue_normal_holo_light));
		button2.setBackground(TwsRippleUtils.getDefaultDrawable(this));
		button3.setBackground(TwsRippleUtils.getCustomDrawable(this, R.color.tws_dark_gray, R.drawable.btn_noframe_red_normal_holo_light, 0));
		button4.setBackground(TwsRippleUtils.getHasContentDrawable(this, R.drawable.btn_default_holo_light));

		View view1 = findViewById(R.id.ripple_child1);
		view1.setBackground(TwsRippleUtils.getDefaultDrawable(this));
		View view2 = findViewById(R.id.ripple_child2);
		view2.setBackground(TwsRippleUtils.getCustomDrawable(this, R.color.tws_blue, 0, 0));
		View view3 = findViewById(R.id.ripple_child3);
		view3.setBackground(TwsRippleUtils.getCustomDrawable(this, R.color.tws_red, 0, 0));
		View view4 = findViewById(R.id.ripple_child4);
		view4.setBackground(TwsRippleUtils.getCustomDrawable(this, R.color.tws_red, 0, 0, TwsRippleUtils.RIPPLE_STYLE_CLEAR));
		setupViews();
	}

	void setupViews() {
		TextView defaultLight = (TextView) findViewById(R.id.default_light);
		TextView defaultDark = (TextView) findViewById(R.id.default_dark);
		TextView listLight = (TextView) findViewById(R.id.list_light);
		TextView listDark = (TextView) findViewById(R.id.list_dark);
		defaultLight.setBackground(TwsRippleUtils.getDefaultDrawable(this));
		defaultDark.setBackground(TwsRippleUtils.getDefaultDarkDrawable(this));
		listLight.setBackground(TwsRippleUtils.getDefaultListSelector(this));
		listDark.setBackground(TwsRippleUtils.getDefaultListDarkSelector(this));
	}
}
