package com.tencent.tws.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.tencent.tws.burgeon.R;

public class TwsButton extends Button {
	public static final int CIRCLE_LIGHT_GREEN_MODE = 0; // default
	public static final int CIRCLE_DARK_GREEN_MODE = 1;
	public static final int RECTANGLE_LIGHT_GREEN_MODE = 2;
	public static final int RECTANGLE_DARK_GREEN_MODE = 3;
	public static final int CIRCLE_LIGHT_RED_MODE = 4;
	public static final int CIRCLE_DARK_RED_MODE = 5;

	private int mButtomMode = -1;
	private int twsbutton_circle_size = 0;
	private int twsbutton_rectangle_w = 0;
	private int twsbutton_rectangle_h = 0;
	Drawable mSrc = null;

	public TwsButton(Context context) {
		super(context);
	}

	public TwsButton(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.twsButtonStyle);
	}

	public TwsButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TwsButton, defStyleAttr, 0);
		mButtomMode = a.getInt(R.styleable.TwsButton_twsButtonMode, -1);
		int layout_width = a.getLayoutDimension(R.styleable.TwsButton_android_layout_width, "layout_width");
		int layout_height = a.getLayoutDimension(R.styleable.TwsButton_android_layout_height, "layout_height");
		mSrc = a.getDrawable(R.styleable.TwsButton_src);
		a.recycle();

		twsbutton_circle_size = getResources().getDimensionPixelSize(R.dimen.twsbutton_circle_size);
		twsbutton_rectangle_w = getResources().getDimensionPixelSize(R.dimen.twsbutton_rectangle_w);
		twsbutton_rectangle_h = getResources().getDimensionPixelSize(R.dimen.twsbutton_rectangle_h);

		setupButton(layout_width, layout_height);
	}

	private void setupButton(int layout_width, int layout_height) {

		switch (mButtomMode) {
		case CIRCLE_LIGHT_GREEN_MODE:
			if (layout_width <= 0 || layout_height <= 0) {
				if (0 == twsbutton_circle_size) {
					twsbutton_circle_size = getResources().getDimensionPixelSize(R.dimen.twsbutton_circle_size);
				}
				setWidth(twsbutton_circle_size);
				setHeight(twsbutton_circle_size);
			}
			setBackground(getResources().getDrawable(R.drawable.circle_button_light_green_bg));
			break;
		case CIRCLE_DARK_GREEN_MODE:
			if (layout_width <= 0 || layout_height <= 0) {
				if (0 == twsbutton_circle_size) {
					twsbutton_circle_size = getResources().getDimensionPixelSize(R.dimen.twsbutton_circle_size);
				}
				setWidth(twsbutton_circle_size);
				setHeight(twsbutton_circle_size);
			}
			setBackground(getResources().getDrawable(R.drawable.circle_button_dark_green_bg));
			break;
		case CIRCLE_DARK_RED_MODE:
			if (layout_width <= 0 || layout_height <= 0) {
				if (0 == twsbutton_circle_size) {
					twsbutton_circle_size = getResources().getDimensionPixelSize(R.dimen.twsbutton_circle_size);
				}
				setWidth(twsbutton_circle_size);
				setHeight(twsbutton_circle_size);
			}
			setBackground(getResources().getDrawable(R.drawable.circle_button_dark_red_bg));

			break;
		case RECTANGLE_LIGHT_GREEN_MODE:
			if (layout_width <= 0 || layout_height <= 0) {
				if (0 == twsbutton_rectangle_w || 0 == twsbutton_rectangle_h) {
					twsbutton_rectangle_w = getResources().getDimensionPixelSize(R.dimen.twsbutton_rectangle_w);
					twsbutton_rectangle_h = getResources().getDimensionPixelSize(R.dimen.twsbutton_rectangle_h);
				}
				setWidth(twsbutton_rectangle_w);
				setHeight(twsbutton_rectangle_h);
			}
			setBackground(getResources().getDrawable(R.drawable.round_rectangle_button_light_green_bg));

			break;
		case RECTANGLE_DARK_GREEN_MODE:
			if (layout_width <= 0 || layout_height <= 0) {
				if (0 == twsbutton_rectangle_w || 0 == twsbutton_rectangle_h) {
					twsbutton_rectangle_w = getResources().getDimensionPixelSize(R.dimen.twsbutton_rectangle_w);
					twsbutton_rectangle_h = getResources().getDimensionPixelSize(R.dimen.twsbutton_rectangle_h);
				}
				setWidth(twsbutton_rectangle_w);
				setHeight(twsbutton_rectangle_h);
			}
			setBackground(getResources().getDrawable(R.drawable.round_rectangle_button_dark_green_bg));

			break;

		default:
			break;
		}
	}

	private static final String TAG = "TwsButton";

	public void setButtonMode(int mode) {
		if (mButtomMode == mode)
			return;

		mButtomMode = mode;
		setupButton(0, 0);
		invalidate();
	}

	public int getButtonMode() {
		return mButtomMode;
	}
}
