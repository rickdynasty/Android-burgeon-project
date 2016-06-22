package com.rickdynasty.tws.core.apidemos.searchview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import com.rickdynasty.tws.burgeon.R;

public class SearchBarView extends EditText implements OnFocusChangeListener, TextWatcher {
	private Drawable mDeleteDrawable;

    public SearchBarView(Context context) {
        this(context, null);
    }

    public SearchBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mDeleteDrawable = getCompoundDrawables()[2]; 
        if (mDeleteDrawable == null) {
            mDeleteDrawable = getResources()
                    .getDrawable(R.drawable.search_result_selector);
        }
        mDeleteDrawable.setBounds(0, 0, mDeleteDrawable.getIntrinsicWidth(), mDeleteDrawable.getIntrinsicHeight());
        setDeleteIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private void setDeleteIconVisible(boolean visible) {
        Drawable icon = visible ? mDeleteDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], icon, getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                boolean touchable = event.getX() > (getWidth()
                        - getPaddingRight() - mDeleteDrawable.getIntrinsicWidth())
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        setDeleteIconVisible(getText().length() > 0);
    }

    @Override 
    public void onTextChanged(CharSequence s, int start, int count, 
            int after) { 
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setDeleteIconVisible(getText().length() > 0);
        } else {
            setDeleteIconVisible(false);
        }
    }
}
