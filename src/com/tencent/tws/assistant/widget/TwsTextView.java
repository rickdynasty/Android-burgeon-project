package com.tencent.tws.assistant.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class TwsTextView extends TextView {
	
	private static final String TAG = "TwsTextView";

	public TwsTextView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	
    public TwsTextView(Context context, AttributeSet attrs) {
        this(context, attrs, com.android.internal.R.attr.textViewStyle);
    }

    @SuppressWarnings("deprecation")
    public TwsTextView(Context context, AttributeSet attrs, int defStyle) {
    	super(context, attrs, defStyle);
    }
    
    private boolean mTwsIsHandleView = true;

	public void twsSetHandleViewFlag(boolean isHandleView) {
		mTwsIsHandleView = isHandleView;
		
		Log.i(TAG, "twsSetHandleViewFlag|isHandleView="+isHandleView);
	}
	
	public boolean twsGetHandleViewFlag() {
		
		Log.i(TAG, "twsGetHandleViewFlag|mTwsIsHandleView="+mTwsIsHandleView);
		
		return mTwsIsHandleView;
	}
}
