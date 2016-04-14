package com.tencent.tws.core.apidemos.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.app.TwsActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.tws.core.widget.TwsButton;
import com.tencent.tws.burgeon.R;

public class TwsButtonActivity extends TwsActivity implements OnClickListener {

	TwsButton tws_btn_Normal;
	TwsButton tws_btn_Recommended;
	TwsButton tws_btn_Progress;
	TwsButton tws_btn_Custom;
	Button btn_action_enable;
	private ValueAnimator mAnimator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_tws_button);
		tws_btn_Normal = (TwsButton) findViewById(R.id.tws_btn_Normal);
		tws_btn_Normal.setOnClickListener(this);
		tws_btn_Recommended = (TwsButton) findViewById(R.id.tws_btn_Recommended);
		tws_btn_Recommended.setOnClickListener(this);
		tws_btn_Progress = (TwsButton) findViewById(R.id.tws_btn_Progress);
		tws_btn_Progress.setOnClickListener(this);
		tws_btn_Custom = (TwsButton) findViewById(R.id.tws_btn_Custom);
		tws_btn_Custom.setOnClickListener(this);
		btn_action_enable = (Button) findViewById(R.id.btn_test);
		btn_action_enable.setOnClickListener(this);
		btn_action_enable.setText("to unEnable");
		Button btn_action = (Button) findViewById(R.id.btn_test2);
		btn_action.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tws_btn_Normal:
			Toast.makeText(getApplication(), "Normal Button", Toast.LENGTH_SHORT).show();
			break;
		case R.id.tws_btn_Recommended:
			Toast.makeText(getApplication(), "Recommended Button", Toast.LENGTH_SHORT).show();
			break;
		case R.id.tws_btn_Progress:
			Toast.makeText(getApplication(), "Progress Button", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_test:
			if (tws_btn_Normal != null) {
				if (tws_btn_Normal.isEnabled()) {
					tws_btn_Normal.setEnabled(false);
					tws_btn_Recommended.setEnabled(false);
					tws_btn_Progress.setEnabled(false);
					tws_btn_Custom.setEnabled(false);
					btn_action_enable.setText("to Enable");
				} else {
					tws_btn_Normal.setEnabled(true);
					tws_btn_Recommended.setEnabled(true);
					tws_btn_Progress.setEnabled(true);
					tws_btn_Custom.setEnabled(true);
					btn_action_enable.setText("to unEnable");
				}
			}
			break;
		case R.id.btn_test2:
			openWithAnimate();
			break;

		default:
			break;
		}
	}

	public void openWithAnimate() {
		if (tws_btn_Progress == null)
			return;

		if (mAnimator != null) {
			mAnimator.cancel();
			mAnimator = null;
		}

		final int porgress = tws_btn_Progress.getProgressMax();
		mAnimator = ValueAnimator.ofInt(0, porgress);
		mAnimator.setDuration(5000);
		mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animator) {
				int iValue = animator.getAnimatedValue() == null ? 0 : (Integer) animator.getAnimatedValue();
				tws_btn_Progress.setProgress(iValue);
				tws_btn_Custom.setProgress(iValue);
				if (0 < porgress)
					tws_btn_Progress.setText((iValue * 100) / porgress + "%");
			}
		});

		mAnimator.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator arg0) {
			}

			@Override
			public void onAnimationRepeat(Animator arg0) {
			}

			@Override
			public void onAnimationEnd(Animator arg0) {
			}

			@Override
			public void onAnimationCancel(Animator arg0) {
			}
		});
		mAnimator.start();
	}

}
