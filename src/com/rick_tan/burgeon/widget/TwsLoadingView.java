package com.rick_tan.burgeon.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.LevelListDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.rickdynasty.tws.burgeon.R;

public class TwsLoadingView extends View {
	public static int TOTAL_FRAME_COUNT = 35;
	public static int ONE_CIRCLE_DEGREE = 360;

	private LevelListDrawable mDrawableList;
	private ValueAnimator mAnimator;
	private int mLevel = -1;
	private int mCallBackCount = 0;
	private int mRotateDegree = 0;
	// for prevent continue to the next animation when mIsDetachedFromWindow
	private boolean mIsDetachedFromWindow = false;

	private int loading_frames_in_min = 33;
	private int loading_rotate_degree_in_min = 140;
	private int per_frame_rotate_degree = 0;

	public TwsLoadingView(Context context) {
		this(context, null);
	}

	public TwsLoadingView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TwsLoadingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mDrawableList = (LevelListDrawable) getResources().getDrawable(R.drawable.loading_frame_levellist);

		loading_frames_in_min = getResources().getInteger(R.integer.loading_frames_in_min);
		loading_rotate_degree_in_min = getResources().getInteger(R.integer.loading_rotate_degree_in_min);
		per_frame_rotate_degree = Math.max(loading_rotate_degree_in_min / loading_frames_in_min, 4);
		startAnimation();
	}

	public void setColorFilter(int color) {
		if (mDrawableList != null) {
			mDrawableList.setColorFilter(color, PorterDuff.Mode.SRC_IN);
		}
	}

	private void startAnimation() {
		if (mIsDetachedFromWindow)
			return;

		if (mAnimator != null) {
			mAnimator.cancel();
			mAnimator = null;
		}

		mLevel = -1;
		mCallBackCount = 0;
		mAnimator = ValueAnimator.ofInt(0, Integer.MAX_VALUE);
		long duratime = TOTAL_FRAME_COUNT * (360 * 5 * 7 * 9 / per_frame_rotate_degree) * 2;
		mAnimator.setDuration(duratime);
		mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animator) {
				mCallBackCount += 1;

				if (mCallBackCount < 0 || 0 != mCallBackCount % 2) {
					return;
				}

				int iValue = mCallBackCount / 2;

				int modulusBinary = iValue % TOTAL_FRAME_COUNT;
				if (mLevel == modulusBinary) {
					return;
				}

				mLevel = modulusBinary;

				mDrawableList.setLevel(mLevel);
				mRotateDegree = iValue % ONE_CIRCLE_DEGREE;
				invalidate();
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
				if (!mIsDetachedFromWindow) {
					startAnimation();
				}
			}

			@Override
			public void onAnimationCancel(Animator arg0) {
			}
		});
		mAnimator.start();
	}

	public void stopAnimation() {
		if (mAnimator != null && mAnimator.isRunning()) {
			mAnimator.cancel();
			mAnimator = null;
		}
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		final int width = getWidth();
		final int height = getHeight();
		Rect bounds = new Rect(1, 1, width - 1, height - 1);
		mDrawableList.setBounds(bounds);
		if (0 < mRotateDegree) {
			canvas.rotate(mRotateDegree, width / 2, height / 2);
		}
		mDrawableList.draw(canvas);
	}

	@Override
	protected void onDetachedFromWindow() {
		mIsDetachedFromWindow = true;
		stopAnimation();
		super.onDetachedFromWindow();
	}
}
