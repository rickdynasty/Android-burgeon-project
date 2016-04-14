package com.tencent.tws.core.apidemos.widget;

import android.app.TwsActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TwsEditText;

import com.tencent.tws.burgeon.R;

public class TwsEditTextActivity extends TwsActivity implements Button.OnClickListener {
	private boolean bNeedClearAction = false; // 系统默认是显示
	private boolean isWhiteBackground = true; // 系统默认是白色背景
	TwsEditText editText;
	Button btnAction;

	// 是白色背景 还是 有色背景，最好在xml里面配置，这样可以避免加载两套图标到app
	Button btnAction_isWB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twsedit_text);
		editText = (TwsEditText) findViewById(R.id.tws_edit);
		btnAction = (Button) findViewById(R.id.btn_action);
		btnAction.setOnClickListener(this);
		btnAction_isWB = (Button) findViewById(R.id.btn_iswb);
		btnAction_isWB.setOnClickListener(this);

		btnAction.setText("显示清理Action");
		btnAction_isWB.setText("显示有色背景icon");
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_action:
			bNeedClearAction = !bNeedClearAction;
			if (bNeedClearAction) {
				btnAction.setText("显示清理Action");
			} else {
				btnAction.setText("隐藏清理Action");
			}
			editText.setNeedShowClearAction(bNeedClearAction);
			break;
		case R.id.btn_iswb:
			isWhiteBackground = !isWhiteBackground;
			if (isWhiteBackground) {
				btnAction_isWB.setText("显示有色背景icon");
			} else {
				btnAction_isWB.setText("显示白色背景icon");
			}
			editText.setIsWhiteBackground(isWhiteBackground);
			break;
		default:
			break;
		}
	}

}
