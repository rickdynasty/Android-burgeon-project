package com.rickdynasty.tws.core.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.rickdynasty.tws.burgeon.R;

public class TwsButton extends Button {

	private static final String TAG = "TwsButton";

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

	private ValueAnimator mAnimator;
	private float mScal = 1.0f;
	private boolean mEventActionEnd = false;
	private int mStartColor = 0;
	private int mEndColor = 0;
	private int mFillColor = 0;
	private int mDisabledStartColor = 0;
	private int mDisabledEndColor = 0;
	private int mDisabledFillColor = 0;
	private TwsSimpleGradientDrawable mBackground = null;
	private final float sSCAL_END = 0.8f;
	private final float sSCAL_START = 1.0f;
	private final int sANIMATION_DURATION_IN = 150;
	private final int sANIMATION_DURATION_OUT = 300;
	// for animation
	private int mStartAlpha = 0;
	private int mStartRed = 0;
	private int mStartGreen = 0;
	private int mStartBlue = 0;
	private int mEndAlpha = 0;
	private int mEndRed = 0;
	private int mEndGreen = 0;
	private int mEndBlue = 0;

	private boolean mEnabled = true;
	private boolean mClickable = true;

	public TwsButton(Context context) {
		super(context);
	}

	public TwsButton(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.twsButtonStyle);
	}

	public TwsButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TwsButton, defStyleAttr, 0);
		mButtomMode = a.getInt(R.styleable.TwsButton_buttonMode, -1);
		int layout_width = a.getLayoutDimension(R.styleable.TwsButton_android_layout_width, "layout_width");
		int layout_height = a.getLayoutDimension(R.styleable.TwsButton_android_layout_height, "layout_height");
		mSrc = a.getDrawable(R.styleable.TwsButton_src);
		mEnabled = a.getBoolean(R.styleable.TwsButton_android_enabled, true);
		mClickable = a.getBoolean(R.styleable.TwsButton_android_clickable, true);
		int textColor = a.getColor(R.styleable.TwsButton_android_textColor, Color.WHITE);
		a.recycle();
		setTextColor(textColor);

		twsbutton_circle_size = getResources().getDimensionPixelSize(R.dimen.twsbutton_circle_size);
		twsbutton_rectangle_w = getResources().getDimensionPixelSize(R.dimen.twsbutton_rectangle_w);
		twsbutton_rectangle_h = getResources().getDimensionPixelSize(R.dimen.twsbutton_rectangle_h);

		initButtonSize(layout_width, layout_height);
		init();
		initButtonContent();
	}

	private boolean isLegalMode() {
		return mButtomMode == CIRCLE_LIGHT_GREEN_MODE || mButtomMode == CIRCLE_DARK_GREEN_MODE
				|| mButtomMode == RECTANGLE_LIGHT_GREEN_MODE || mButtomMode == RECTANGLE_DARK_GREEN_MODE
				|| mButtomMode == CIRCLE_LIGHT_RED_MODE || mButtomMode == CIRCLE_DARK_RED_MODE;
	}

	// Don't blame me, egg ache animation needs, can only delay the click
	@Override
	public boolean performClick() {
		Handler handler = getHandler();
		if (handler != null) {
			handler.postDelayed(mSuperPerformClick, sANIMATION_DURATION_OUT);
		} else {
			return super.performClick();
		}

		return true;
	}

	private final Runnable mSuperPerformClick = new Runnable() {
		@Override
		public void run() {
			superPerformClick();
		}
	};

	private boolean superPerformClick() {
		return super.performClick();
	}

	private void init() {
		switch (mButtomMode) {
		case CIRCLE_LIGHT_GREEN_MODE:
			mStartColor = getResources().getColor(R.color.twsbutton_light_green_startColor);
			mEndColor = getResources().getColor(R.color.twsbutton_light_green_endColor);
			mFillColor = getResources().getColor(R.color.twsbutton_light_green_fillColor);
			break;
		case CIRCLE_DARK_GREEN_MODE:
			mStartColor = getResources().getColor(R.color.twsbutton_dark_green_startColor);
			mEndColor = getResources().getColor(R.color.twsbutton_dark_green_endColor);
			mFillColor = getResources().getColor(R.color.twsbutton_dark_green_fillColor);
			break;
		case RECTANGLE_LIGHT_GREEN_MODE:
			mStartColor = getResources().getColor(R.color.twsbutton_light_green_startColor);
			mEndColor = getResources().getColor(R.color.twsbutton_light_green_endColor);
			mFillColor = getResources().getColor(R.color.twsbutton_light_green_fillColor);
			break;
		case RECTANGLE_DARK_GREEN_MODE:
			mStartColor = getResources().getColor(R.color.twsbutton_dark_green_startColor);
			mEndColor = getResources().getColor(R.color.twsbutton_dark_green_endColor);
			mFillColor = getResources().getColor(R.color.twsbutton_dark_green_fillColor);
			break;
		case CIRCLE_LIGHT_RED_MODE:
			mStartColor = getResources().getColor(R.color.twsbutton_light_red_startColor);
			mEndColor = getResources().getColor(R.color.twsbutton_light_red_endColor);
			mFillColor = getResources().getColor(R.color.twsbutton_light_red_fillColor);
			break;
		case CIRCLE_DARK_RED_MODE:
			mStartColor = getResources().getColor(R.color.twsbutton_dark_red_startColor);
			mEndColor = getResources().getColor(R.color.twsbutton_dark_red_endColor);
			mFillColor = getResources().getColor(R.color.twsbutton_dark_red_fillColor);
			break;
		default:
			return;
		}

		mDisabledStartColor = getResources().getColor(R.color.twsbutton_disabled_startColor);
		mDisabledEndColor = getResources().getColor(R.color.twsbutton_disabled_endColor);
		mDisabledFillColor = getResources().getColor(R.color.twsbutton_disabled_fillColor);
	}

	private void initButtonSize(int layout_width, int layout_height) {
		switch (mButtomMode) {
		case CIRCLE_LIGHT_GREEN_MODE:
		case CIRCLE_DARK_RED_MODE:
		case CIRCLE_LIGHT_RED_MODE:
		case CIRCLE_DARK_GREEN_MODE:
			initCircleButtonSize(layout_width, layout_height);
			break;
		case RECTANGLE_LIGHT_GREEN_MODE:
		case RECTANGLE_DARK_GREEN_MODE:
			initRectangleButtonSize(layout_width, layout_height);
			break;
		default:
			break;
		}
	}

	private void initButtonContent() {
		if (!isLegalMode())
			return;

		if (mBackground == null) {
			int roundRadius = getResources().getDimensionPixelSize(R.dimen.tws_button_radius);
			int fileDrawableInset = getResources().getDimensionPixelSize(R.dimen.tws_button_border_width);
			if (fileDrawableInset < 2)
				fileDrawableInset = 3;

			mBackground = new TwsSimpleGradientDrawable();
			mBackground.setCornerRadius(roundRadius);
			mBackground.setStroke(fileDrawableInset, Color.TRANSPARENT);
			mBackground.setColor(mFillColor);
			mBackground.setStrokeColors(new int[] { mStartColor, mEndColor });
		}

		if (mEnabled) {
			mStartAlpha = Color.alpha(mStartColor);
			mStartRed = Color.red(mStartColor);
			mStartGreen = Color.green(mStartColor);
			mStartBlue = Color.blue(mStartColor);
			mEndAlpha = Color.alpha(mEndColor);
			mEndRed = Color.red(mEndColor);
			mEndGreen = Color.green(mEndColor);
			mEndBlue = Color.blue(mEndColor);

			// call this api to determine the direction of alignment
			mBackground.setStrokeColors(new int[] { Color.argb(mStartAlpha, mStartRed, mStartGreen, mStartBlue),
					Color.argb(mEndAlpha, mEndRed, mEndGreen, mEndBlue) });
			mBackground.setColor(mFillColor);
		} else {
			mBackground.setStrokeColors(new int[] { mDisabledStartColor, mDisabledEndColor });
			mBackground.setColor(mDisabledFillColor);
		}

		setBackground(mBackground);
	}

	public void setBorderColors(int startColor, int endColor) {
		if (mBackground != null) {
			mStartColor = startColor;
			mStartAlpha = Color.alpha(mStartColor);
			mStartRed = Color.red(mStartColor);
			mStartGreen = Color.green(mStartColor);
			mStartBlue = Color.blue(mStartColor);

			mEndColor = endColor;
			mEndAlpha = Color.alpha(mEndColor);
			mEndRed = Color.red(mEndColor);
			mEndGreen = Color.green(mEndColor);
			mEndBlue = Color.blue(mEndColor);

			mBackground.setStrokeColors(new int[] { mStartColor, mEndColor });
		}
	}

	public void setFillColor(int fillColor) {
		if (mBackground != null) {
			mFillColor = fillColor;
			mBackground.setColor(fillColor);
		}
	}

	private void initCircleButtonSize(int layout_width, int layout_height) {
		if (layout_width <= 0 || layout_height <= 0) {
			if (0 == twsbutton_circle_size) {
				twsbutton_circle_size = getResources().getDimensionPixelSize(R.dimen.twsbutton_circle_size);
			}
			setWidth(twsbutton_circle_size);
			setHeight(twsbutton_circle_size);
		}
	}

	private void initRectangleButtonSize(int layout_width, int layout_height) {
		if (layout_width <= 0 || layout_height <= 0) {
			if (0 == twsbutton_rectangle_w || 0 == twsbutton_rectangle_h) {
				twsbutton_rectangle_w = getResources().getDimensionPixelSize(R.dimen.twsbutton_rectangle_w);
				twsbutton_rectangle_h = getResources().getDimensionPixelSize(R.dimen.twsbutton_rectangle_h);
			}
			setWidth(twsbutton_rectangle_w);
			setHeight(twsbutton_rectangle_h);
		}
	}

	public void setButtonMode(int mode) {
		if (mButtomMode == mode)
			return;

		mButtomMode = mode;

		initButtonSize(getWidth(), getHeight());
		init();
		initButtonContent();

		invalidate();
	}

	public int getButtonMode() {
		return mButtomMode;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!mClickable)
			return super.onTouchEvent(event);

		final int action = event.getActionMasked();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mEventActionEnd = false;
			startInAnimation();
			break;
		case MotionEvent.ACTION_UP:
			mEventActionEnd = true;
			startOutAnimation();
			break;
		case MotionEvent.ACTION_CANCEL:
			mEventActionEnd = true;
			startOutAnimation();
			break;

		default:
			break;
		}

		boolean rlt = super.onTouchEvent(event);
		if (rlt == false) {
			mEventActionEnd = true;
		}

		return rlt;
	}

	private void startInAnimation() {
		if (mAnimator != null) {
			mAnimator.cancel();
			mAnimator = null;
		}

		final int offsetAlpha = mStartAlpha - mEndAlpha;
		final int offsetRed = mStartRed - mEndRed;
		final int offsetGreen = mStartGreen - mEndGreen;
		final int offsetBlue = mStartBlue - mEndBlue;

		mAnimator = ValueAnimator.ofInt(1, 100);
		mAnimator.setDuration(sANIMATION_DURATION_IN);
		mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animator) {
				final int value = (Integer) animator.getAnimatedValue();
				if (value < 1 || 100 < value) {
					return;
				}

				if (value % 2 != 0)
					return;

				final float percent = (float) value / 100;
				mScal = sSCAL_START - (sSCAL_START - sSCAL_END) * percent;

				if (mBackground != null && mEnabled) {
					int endAlpha = mEndAlpha + (int) (offsetAlpha * percent);
					int endRed = mEndRed + (int) (offsetRed * percent);
					int endGreen = mEndGreen + (int) (offsetGreen * percent);
					int engBlue = mEndBlue + (int) (offsetBlue * percent);
					mBackground.setStrokeColors(new int[] {
							Color.argb(mStartAlpha, mStartRed, mStartGreen, mStartBlue),
							Color.argb(endAlpha, endRed, endGreen, engBlue) });
				}

				setScaleX(mScal);
				setScaleY(mScal);
				invalidate();
			}
		});
		mAnimator.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animator) {
			}

			@Override
			public void onAnimationRepeat(Animator animator) {
			}

			@Override
			public void onAnimationEnd(Animator animator) {
				if (mEventActionEnd) {
					mAnimator.cancel();
					mAnimator = null;
					startOutAnimation();
				}
			}

			@Override
			public void onAnimationCancel(Animator arg0) {
			}
		});
		mAnimator.start();
	}

	private void startOutAnimation() {
		if (mAnimator != null && mAnimator.isRunning()) {
			mEventActionEnd = true;
			return;
		}

		if (mAnimator != null) {
			mAnimator.cancel();
			mAnimator = null;
		}

		final int offsetAlpha = mStartAlpha - mEndAlpha;
		final int offsetRed = mStartRed - mEndRed;
		final int offsetGreen = mStartGreen - mEndGreen;
		final int offsetBlue = mStartBlue - mEndBlue;

		mAnimator = ValueAnimator.ofInt(99, 0);
		mAnimator.setDuration(sANIMATION_DURATION_OUT);
		mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animator) {
				final int value = (Integer) animator.getAnimatedValue();
				if (value < 0 || 99 < value) {
					return;
				}

				final float percent = (float) value / 100;
				mScal = sSCAL_START - (sSCAL_START - sSCAL_END) * percent;

				if (mBackground != null && mEnabled) {
					int endAlpha = mEndAlpha + (int) (offsetAlpha * percent);
					int endRed = mEndRed + (int) (offsetRed * percent);
					int endGreen = mEndGreen + (int) (offsetGreen * percent);
					int engBlue = mEndBlue + (int) (offsetBlue * percent);
					mBackground.setStrokeColors(new int[] {
							Color.argb(mStartAlpha, mStartRed, mStartGreen, mStartBlue),
							Color.argb(endAlpha, endRed, endGreen, engBlue) });
				}

				setScaleX(mScal);
				setScaleY(mScal);
				invalidate();
			}
		});
		mAnimator.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animator) {
			}

			@Override
			public void onAnimationRepeat(Animator animator) {
			}

			@Override
			public void onAnimationEnd(Animator animator) {
				setScaleX(1.0f);
				setScaleY(1.0f);
				if (mAnimator != null) {
					mAnimator.cancel();
					mAnimator = null;
				}
			}

			@Override
			public void onAnimationCancel(Animator arg0) {
			}
		});
		mAnimator.start();
	}

	@Override
	protected void onDetachedFromWindow() {
		if (mAnimator != null) {
			mAnimator.cancel();
			mAnimator = null;
		}
		super.onDetachedFromWindow();
	}

	@Override
	public void setEnabled(boolean enable) {
		super.setEnabled(enable);
		if (mEnabled != enable) {
			mEnabled = enable;
			initButtonContent();
		}
	}

	@Override
	public void setClickable(boolean clickable) {
		super.setClickable(clickable);
		mClickable = clickable;
	}

}
