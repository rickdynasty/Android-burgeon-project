package com.tencent.tws.assistant.apidemos.searchview;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.tencent.tws.devicemanager.R;


public class SearchResultActivity extends TwsActivity implements OnClickListener,TextWatcher{
	private static final String TAG = "SearchResultActivity";
	private View mTranslucentMask;
	private Button mCancelBtn;
	private EditText mTwsSearchView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		mTranslucentMask = findViewById(R.id.translucent_mask);
		mCancelBtn = (Button) findViewById(R.id.cancel_search_btn);
		mTwsSearchView =  (EditText) findViewById(R.id.sms_search_view);
		mTwsSearchView.addTextChangedListener(this);
		mTranslucentMask.setOnClickListener(this);
		mCancelBtn.setOnClickListener(this);
		Window theWindow = getWindow();
		
		WindowManager.LayoutParams lp = theWindow.getAttributes();
		lp.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE;
		theWindow.setAttributes(lp);
	}
	
	@Override
	public void onClick(View v) {
		
		if(v == mTranslucentMask){
			if(mTranslucentMask.isShown()){
				mTranslucentMask.setVisibility(View.INVISIBLE);
			}
		}else if(v == mCancelBtn) {
			finish();
		}
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		Log.d(TAG, "afterTextChanged");
		setTranslucentMaskVisible(mTwsSearchView.getText().length() <= 0);
	}
	
	public void setTranslucentMaskVisible(boolean isVisible) {
		if(isVisible) {
			mTranslucentMask.setVisibility(View.VISIBLE);
		}else {
			mTranslucentMask.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onTextChanged");
		
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onTextChanged");
	}

}
