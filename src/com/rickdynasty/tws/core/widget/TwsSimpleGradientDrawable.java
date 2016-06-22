package com.rickdynasty.tws.core.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;

/** @hide */
public class TwsSimpleGradientDrawable extends Drawable {
	private static final String TAG = "TwsSimpleGradientDrawable";

	public static final int RECTANGLE = 0;
	public static final int OVAL = 1;
	public static final int LINE = 2;
	public static final int RING = 3;

	public static final int LINEAR_GRADIENT = 0;
	public static final int RADIAL_GRADIENT = 1;
	public static final int SWEEP_GRADIENT = 2;

	private static final int RADIUS_TYPE_PIXELS = 0;
	private static final int RADIUS_TYPE_FRACTION = 1;
	private static final int RADIUS_TYPE_FRACTION_PARENT = 2;

	private static final float DEFAULT_INNER_RADIUS_RATIO = 3.0f;
	private static final float DEFAULT_THICKNESS_RATIO = 9.0f;

	private GradientState mGradientState;

	private final Paint mFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Rect mPadding;
	private final Paint mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private ColorFilter mColorFilter; // optional, set by the caller
	private int mAlpha = 0xFF; // modified by the caller

	private final Path mPath = new Path();
	private final RectF mRect = new RectF();

	private Paint mLayerPaint; // internal, used if we use saveLayer()
	private boolean mGradientIsDirty; // internal state
	private boolean mMutated;
	private Path mRingPath;
	private boolean mPathIsDirty = true;

	/** Current gradient radius, valid when {@link #mGradientIsDirty} is false. */
	private float mGradientRadius;

	/**
	 * Controls how the gradient is oriented relative to the drawable's bounds
	 */
	public enum Orientation {
		/** draw the gradient from the top to the bottom */
		TOP_BOTTOM,
		/** draw the gradient from the top-right to the bottom-left */
		TR_BL,
		/** draw the gradient from the right to the left */
		RIGHT_LEFT,
		/** draw the gradient from the bottom-right to the top-left */
		BR_TL,
		/** draw the gradient from the bottom to the top */
		BOTTOM_TOP,
		/** draw the gradient from the bottom-left to the top-right */
		BL_TR,
		/** draw the gradient from the left to the right */
		LEFT_RIGHT,
		/** draw the gradient from the top-left to the bottom-right */
		TL_BR,
	}

	public TwsSimpleGradientDrawable() {
		this(new GradientState(Orientation.BR_TL, null));
	}

	public TwsSimpleGradientDrawable(Orientation orientation, int[] colors) {
		this(new GradientState(orientation, colors));
	}

	@Override
	public boolean getPadding(Rect padding) {
		if (mPadding != null) {
			padding.set(mPadding);
			return true;
		} else {
			return super.getPadding(padding);
		}
	}

	public void setCornerRadii(float[] radii) {
		mGradientState.setCornerRadii(radii);
		mPathIsDirty = true;
		invalidateSelf();
	}

	public void setCornerRadius(float radius) {
		mGradientState.setCornerRadius(radius);
		mPathIsDirty = true;
		invalidateSelf();
	}

	public void setStroke(int width, int color) {
		setStroke(width, color, 0, 0);
	}

	public void setStroke(int width, ColorStateList colorStateList) {
		setStroke(width, colorStateList, 0, 0);
	}

	public void setStroke(int width, int color, float dashWidth, float dashGap) {
		mGradientState.setStroke(width, ColorStateList.valueOf(color), dashWidth, dashGap);
		setStrokeInternal(width, color, dashWidth, dashGap);
	}

	public void setStroke(int width, ColorStateList colorStateList, float dashWidth, float dashGap) {
		mGradientState.setStroke(width, colorStateList, dashWidth, dashGap);
		final int color;
		if (colorStateList == null) {
			color = Color.TRANSPARENT;
		} else {
			final int[] stateSet = getState();
			color = colorStateList.getColorForState(stateSet, 0);
		}
		setStrokeInternal(width, color, dashWidth, dashGap);
	}

	private void setStrokeInternal(int width, int color, float dashWidth, float dashGap) {
		mStrokePaint.setStyle(Paint.Style.STROKE);
		mStrokePaint.setStrokeWidth(width);
		mStrokePaint.setColor(color);

		DashPathEffect e = null;
		if (dashWidth > 0) {
			e = new DashPathEffect(new float[] { dashWidth, dashGap }, 0);
		}
		mStrokePaint.setPathEffect(e);
		invalidateSelf();
	}

	public void setSize(int width, int height) {
		mGradientState.setSize(width, height);
		mPathIsDirty = true;
		invalidateSelf();
	}

	public void setShape(int shape) {
		mRingPath = null;
		mPathIsDirty = true;
		mGradientState.setShape(shape);
		invalidateSelf();
	}

	public void setGradientType(int gradient) {
		mGradientState.setGradientType(gradient);
		mGradientIsDirty = true;
		invalidateSelf();
	}

	public void setGradientCenter(float x, float y) {
		mGradientState.setGradientCenter(x, y);
		mGradientIsDirty = true;
		invalidateSelf();
	}

	public void setGradientRadius(float gradientRadius) {
		mGradientState.setGradientRadius(gradientRadius, TypedValue.COMPLEX_UNIT_PX);
		mGradientIsDirty = true;
		invalidateSelf();
	}

	public float getGradientRadius() {
		if (mGradientState.mGradient != RADIAL_GRADIENT) {
			return 0;
		}

		ensureValidRect();
		return mGradientRadius;
	}

	public void setUseLevel(boolean useLevel) {
		mGradientState.mUseLevel = useLevel;
		mGradientIsDirty = true;
		invalidateSelf();
	}

	private int modulateAlpha(int alpha) {
		int scale = mAlpha + (mAlpha >> 7);
		return alpha * scale >> 8;
	}

	public Orientation getOrientation() {
		return mGradientState.mOrientation;
	}

	public void setOrientation(Orientation orientation) {
		mGradientState.mOrientation = orientation;
		mGradientIsDirty = true;
		invalidateSelf();
	}

	public void setStrokeColors(int[] colors) {
		mGradientState.setStrokeColors(colors);
		mGradientIsDirty = true;
		ensureValidRect();
		invalidateSelf();
	}

	@Override
	public void draw(Canvas canvas) {
		if (!ensureValidRect()) {
			// nothing to draw
			return;
		}

		// remember the alpha values, in case we temporarily overwrite them
		// when we modulate them with mAlpha
		final int prevFillAlpha = mFillPaint.getAlpha();
		final int prevStrokeAlpha = mStrokePaint != null ? mStrokePaint.getAlpha() : 0;
		// compute the modulate alpha values
		final int currFillAlpha = modulateAlpha(prevFillAlpha);
		final int currStrokeAlpha = modulateAlpha(prevStrokeAlpha);

		final boolean haveStroke = currStrokeAlpha > 0 && mStrokePaint != null && mStrokePaint.getStrokeWidth() > 0;
		final boolean haveFill = currFillAlpha > 0;
		final GradientState st = mGradientState;
		final ColorFilter colorFilter = mColorFilter;

		final boolean useLayer = haveStroke && haveFill && st.mShape != LINE && currStrokeAlpha < 255
				&& (mAlpha < 255 || colorFilter != null);

		if (useLayer) {
			if (mLayerPaint == null) {
				mLayerPaint = new Paint();
			}
			mLayerPaint.setDither(st.mDither);
			mLayerPaint.setAlpha(mAlpha);
			mLayerPaint.setColorFilter(colorFilter);

			float rad = mStrokePaint.getStrokeWidth();
			canvas.saveLayer(mRect.left - rad, mRect.top - rad, mRect.right + rad, mRect.bottom + rad, mLayerPaint,
					Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);

			// don't perform the filter in our individual paints
			// since the layer will do it for us
			mFillPaint.setColorFilter(null);
			mStrokePaint.setColorFilter(null);
		} else {
			/*
			 * if we're not using a layer, apply the dither/filter to our
			 * individual paints
			 */
			mFillPaint.setAlpha(currFillAlpha);
			mFillPaint.setDither(st.mDither);
			mFillPaint.setColorFilter(colorFilter);
			if (colorFilter != null && st.mColorStateList == null) {
				mFillPaint.setColor(mAlpha << 24);
			}
			if (haveStroke) {
				mStrokePaint.setAlpha(currStrokeAlpha);
				mStrokePaint.setDither(st.mDither);
				mStrokePaint.setColorFilter(colorFilter);
			}
		}

		switch (st.mShape) {
		case RECTANGLE:
			if (st.mRadiusArray != null) {
				buildPathIfDirty();
				canvas.drawPath(mPath, mFillPaint);
				if (haveStroke) {
					canvas.drawPath(mPath, mStrokePaint);
				}
			} else if (st.mRadius > 0.0f) {
				float rad = Math.min(st.mRadius, Math.min(mRect.width(), mRect.height()) * 0.5f);
				canvas.drawRoundRect(mRect, rad, rad, mFillPaint);
				if (haveStroke) {
					canvas.drawRoundRect(mRect, rad, rad, mStrokePaint);
				}
			} else {
				if (mFillPaint.getColor() != 0 || colorFilter != null || mFillPaint.getShader() != null) {
					canvas.drawRect(mRect, mFillPaint);
				}
				if (haveStroke) {
					canvas.drawRect(mRect, mStrokePaint);
				}
			}
			break;
		case OVAL:
			canvas.drawOval(mRect, mFillPaint);
			if (haveStroke) {
				canvas.drawOval(mRect, mStrokePaint);
			}
			break;
		case LINE: {
			RectF r = mRect;
			float y = r.centerY();
			if (haveStroke) {
				canvas.drawLine(r.left, y, r.right, y, mStrokePaint);
			}
			break;
		}
		case RING:
			Path path = buildRing(st);
			canvas.drawPath(path, mFillPaint);
			if (haveStroke) {
				canvas.drawPath(path, mStrokePaint);
			}
			break;
		}

		if (useLayer) {
			canvas.restore();
		} else {
			mFillPaint.setAlpha(prevFillAlpha);
			if (haveStroke) {
				mStrokePaint.setAlpha(prevStrokeAlpha);
			}
		}
	}

	private void buildPathIfDirty() {
		final GradientState st = mGradientState;
		if (mPathIsDirty) {
			ensureValidRect();
			mPath.reset();
			mPath.addRoundRect(mRect, st.mRadiusArray, Path.Direction.CW);
			mPathIsDirty = false;
		}
	}

	private Path buildRing(GradientState st) {
		if (mRingPath != null && (!st.mUseLevelForShape || !mPathIsDirty))
			return mRingPath;
		mPathIsDirty = false;

		float sweep = st.mUseLevelForShape ? (360.0f * getLevel() / 10000.0f) : 360f;

		RectF bounds = new RectF(mRect);

		float x = bounds.width() / 2.0f;
		float y = bounds.height() / 2.0f;

		float thickness = st.mThickness != -1 ? st.mThickness : bounds.width() / st.mThicknessRatio;
		// inner radius
		float radius = st.mInnerRadius != -1 ? st.mInnerRadius : bounds.width() / st.mInnerRadiusRatio;

		RectF innerBounds = new RectF(bounds);
		innerBounds.inset(x - radius, y - radius);

		bounds = new RectF(innerBounds);
		bounds.inset(-thickness, -thickness);

		if (mRingPath == null) {
			mRingPath = new Path();
		} else {
			mRingPath.reset();
		}

		final Path ringPath = mRingPath;
		// arcTo treats the sweep angle mod 360, so check for that, since we
		// think 360 means draw the entire oval
		if (sweep < 360 && sweep > -360) {
			ringPath.setFillType(Path.FillType.EVEN_ODD);
			// inner top
			ringPath.moveTo(x + radius, y);
			// outer top
			ringPath.lineTo(x + radius + thickness, y);
			// outer arc
			ringPath.arcTo(bounds, 0.0f, sweep, false);
			// inner arc
			ringPath.arcTo(innerBounds, sweep, -sweep, false);
			ringPath.close();
		} else {
			// add the entire ovals
			ringPath.addOval(bounds, Path.Direction.CW);
			ringPath.addOval(innerBounds, Path.Direction.CCW);
		}

		return ringPath;
	}

	public void setColor(int argb) {
		mGradientState.setColorStateList(ColorStateList.valueOf(argb));
		mFillPaint.setColor(argb);
		invalidateSelf();
	}

	public void setColor(ColorStateList colorStateList) {
		mGradientState.setColorStateList(colorStateList);
		final int color;
		if (colorStateList == null) {
			color = Color.TRANSPARENT;
		} else {
			final int[] stateSet = getState();
			color = colorStateList.getColorForState(stateSet, 0);
		}
		mFillPaint.setColor(color);
		invalidateSelf();
	}

	@Override
	protected boolean onStateChange(int[] stateSet) {
		boolean invalidateSelf = false;

		final GradientState s = mGradientState;
		final ColorStateList stateList = s.mColorStateList;
		if (stateList != null) {
			final int newColor = stateList.getColorForState(stateSet, 0);
			final int oldColor = mFillPaint.getColor();
			if (oldColor != newColor) {
				mFillPaint.setColor(newColor);
				invalidateSelf = true;
			}
		}

		final Paint strokePaint = mStrokePaint;
		if (strokePaint != null) {
			final ColorStateList strokeStateList = s.mStrokeColorStateList;
			if (strokeStateList != null) {
				final int newStrokeColor = strokeStateList.getColorForState(stateSet, 0);
				final int oldStrokeColor = strokePaint.getColor();
				if (oldStrokeColor != newStrokeColor) {
					strokePaint.setColor(newStrokeColor);
					invalidateSelf = true;
				}
			}
		}

		if (s.mTint != null && s.mTintMode != null) {
			invalidateSelf = true;
		}

		if (invalidateSelf) {
			invalidateSelf();
			return true;
		}

		return false;
	}

	@Override
	public boolean isStateful() {
		final GradientState s = mGradientState;
		return super.isStateful() || (s.mColorStateList != null && s.mColorStateList.isStateful())
				|| (s.mStrokeColorStateList != null && s.mStrokeColorStateList.isStateful())
				|| (s.mTint != null && s.mTint.isStateful());
	}

	@Override
	public int getChangingConfigurations() {
		return super.getChangingConfigurations() | mGradientState.mChangingConfigurations;
	}

	@Override
	public void setAlpha(int alpha) {
		if (alpha != mAlpha) {
			mAlpha = alpha;
			invalidateSelf();
		}
	}

	@Override
	public int getAlpha() {
		return mAlpha;
	}

	@Override
	public void setDither(boolean dither) {
		if (dither != mGradientState.mDither) {
			mGradientState.mDither = dither;
			invalidateSelf();
		}
	}

	public ColorFilter getColorFilter() {
		return mColorFilter;
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		if (cf != mColorFilter) {
			mColorFilter = cf;
			invalidateSelf();
		}
	}

	@Override
	public int getOpacity() {
		return (mAlpha == 255 && mGradientState.mOpaqueOverBounds && isOpaqueForState()) ? PixelFormat.OPAQUE
				: PixelFormat.TRANSLUCENT;
	}

	@Override
	protected void onBoundsChange(Rect r) {
		super.onBoundsChange(r);
		mRingPath = null;
		mPathIsDirty = true;
		mGradientIsDirty = true;
	}

	@Override
	protected boolean onLevelChange(int level) {
		super.onLevelChange(level);
		mGradientIsDirty = true;
		mPathIsDirty = true;
		invalidateSelf();
		return true;
	}

	/**
	 * This checks mGradientIsDirty, and if it is true, recomputes both our
	 * drawing rectangle (mRect) and the gradient itself, since it depends on
	 * our rectangle too.
	 * 
	 * @return true if the resulting rectangle is not empty, false otherwise
	 */
	private boolean ensureValidRect() {
		if (mGradientIsDirty) {
			mGradientIsDirty = false;

			Rect bounds = getBounds();
			float inset = 0;

			if (mStrokePaint != null) {
				inset = mStrokePaint.getStrokeWidth() * 0.5f;
			}

			Log.d(TAG, "ensureValidRect() bounds=" + bounds + " inset=" + inset + " mGradientState.mStrokeColors="
					+ mGradientState.mStrokeColors);
			mRect.set(bounds.left + inset, bounds.top + inset, bounds.right - inset, bounds.bottom - inset);
			setStrokePaintShader();
		}
		return !mRect.isEmpty();
	}

	private void setStrokePaintShader() {
		final GradientState st = mGradientState;
		final int[] colors = st.mStrokeColors;
		if (colors != null) {
			RectF r = mRect;
			float x0, x1, y0, y1;

			if (st.mGradient == LINEAR_GRADIENT) {
				final float level = st.mUseLevel ? getLevel() / 10000.0f : 1.0f;
				switch (st.mOrientation) {
				case TOP_BOTTOM:
					x0 = r.left;
					y0 = r.top;
					x1 = x0;
					y1 = level * r.bottom;
					break;
				case TR_BL:
					x0 = r.right;
					y0 = r.top;
					x1 = level * r.left;
					y1 = level * r.bottom;
					break;
				case RIGHT_LEFT:
					x0 = r.right;
					y0 = r.top;
					x1 = level * r.left;
					y1 = y0;
					break;
				case BR_TL:
					x0 = r.right;
					y0 = r.bottom;
					x1 = level * r.left;
					y1 = level * r.top;
					break;
				case BOTTOM_TOP:
					x0 = r.left;
					y0 = r.bottom;
					x1 = x0;
					y1 = level * r.top;
					break;
				case BL_TR:
					x0 = r.left;
					y0 = r.bottom;
					x1 = level * r.right;
					y1 = level * r.top;
					break;
				case LEFT_RIGHT:
					x0 = r.left;
					y0 = r.top;
					x1 = level * r.right;
					y1 = y0;
					break;
				default:/* TL_BR */
					x0 = r.left;
					y0 = r.top;
					x1 = level * r.right;
					y1 = level * r.bottom;
					break;
				}

				mStrokePaint
						.setShader(new LinearGradient(x0, y0, x1, y1, colors, st.mPositions, Shader.TileMode.CLAMP));
			} else if (st.mGradient == RADIAL_GRADIENT) {
				x0 = r.left + (r.right - r.left) * st.mCenterX;
				y0 = r.top + (r.bottom - r.top) * st.mCenterY;

				float radius = st.mGradientRadius;
				if (st.mGradientRadiusType == RADIUS_TYPE_FRACTION) {
					final float width = st.mWidth >= 0 ? st.mWidth : r.width();
					final float height = st.mHeight >= 0 ? st.mHeight : r.height();
					radius *= Math.min(width, height);
				} else if (st.mGradientRadiusType == RADIUS_TYPE_FRACTION_PARENT) {
					radius *= Math.min(r.width(), r.height());
				}

				if (st.mUseLevel) {
					radius *= getLevel() / 10000.0f;
				}

				mGradientRadius = radius;

				if (radius <= 0) {
					radius = 0.001f;
				}

				mStrokePaint.setShader(new RadialGradient(x0, y0, radius, colors, null, Shader.TileMode.CLAMP));
			} else if (st.mGradient == SWEEP_GRADIENT) {
				x0 = r.left + (r.right - r.left) * st.mCenterX;
				y0 = r.top + (r.bottom - r.top) * st.mCenterY;

				int[] tempColors = colors;
				float[] tempPositions = null;

				if (st.mUseLevel) {
					tempColors = st.mTempColors;
					final int length = colors.length;
					if (tempColors == null || tempColors.length != length + 1) {
						tempColors = st.mTempColors = new int[length + 1];
					}
					System.arraycopy(colors, 0, tempColors, 0, length);
					tempColors[length] = colors[length - 1];

					tempPositions = st.mTempPositions;
					final float fraction = 1.0f / (length - 1);
					if (tempPositions == null || tempPositions.length != length + 1) {
						tempPositions = st.mTempPositions = new float[length + 1];
					}

					final float level = getLevel() / 10000.0f;
					for (int i = 0; i < length; i++) {
						tempPositions[i] = i * fraction * level;
					}
					tempPositions[length] = 1.0f;

				}
				mStrokePaint.setShader(new SweepGradient(x0, y0, tempColors, tempPositions));
			}

			if (st.mStrokeColorStateList == null) {
				mStrokePaint.setColor(Color.BLACK);
			}
		}
	}

	@Override
	public int getIntrinsicWidth() {
		return mGradientState.mWidth;
	}

	@Override
	public int getIntrinsicHeight() {
		return mGradientState.mHeight;
	}

	@Override
	public ConstantState getConstantState() {
		mGradientState.mChangingConfigurations = getChangingConfigurations();
		return mGradientState;
	}

	private boolean isOpaqueForState() {
		if (mGradientState.mStrokeWidth >= 0 && mStrokePaint != null && !isOpaque(mStrokePaint.getColor())) {
			return false;
		}

		if (!isOpaque(mFillPaint.getColor())) {
			return false;
		}

		return true;
	}

	@Override
	public Drawable mutate() {
		if (!mMutated && super.mutate() == this) {
			mGradientState = new GradientState(mGradientState);
			initializeWithState(mGradientState);
			mMutated = true;
		}
		return this;
	}

	final static class GradientState extends ConstantState {
		public int mChangingConfigurations;
		public int mShape = RECTANGLE;
		public int mGradient = LINEAR_GRADIENT;
		public int mAngle = 0;
		public Orientation mOrientation;
		public ColorStateList mColorStateList;
		public ColorStateList mStrokeColorStateList;
		public int[] mStrokeColors;
		public int[] mTempColors; // no need to copy
		public float[] mTempPositions; // no need to copy
		public float[] mPositions;
		public int mStrokeWidth = -1; // if >= 0 use stroking.
		public float mStrokeDashWidth = 0.0f;
		public float mStrokeDashGap = 0.0f;
		public float mRadius = 0.0f; // use this if mRadiusArray is null
		public float[] mRadiusArray = null;
		public Rect mPadding = null;
		public int mWidth = -1;
		public int mHeight = -1;
		public float mInnerRadiusRatio = DEFAULT_INNER_RADIUS_RATIO;
		public float mThicknessRatio = DEFAULT_THICKNESS_RATIO;
		public int mInnerRadius = -1;
		public int mThickness = -1;
		public boolean mDither = false;

		float mCenterX = 0.5f;
		float mCenterY = 0.5f;
		float mGradientRadius = 0.5f;
		int mGradientRadiusType = RADIUS_TYPE_PIXELS;
		boolean mUseLevel = false;
		boolean mUseLevelForShape = true;

		boolean mOpaqueOverBounds;
		boolean mOpaqueOverShape;

		ColorStateList mTint = null;
		PorterDuff.Mode mTintMode = PorterDuff.Mode.SRC_IN;

		int[] mThemeAttrs;
		int[] mAttrSize;
		int[] mAttrGradient;
		int[] mAttrSolid;
		int[] mAttrStroke;
		int[] mAttrCorners;
		int[] mAttrPadding;

		GradientState(Orientation orientation, int[] colors) {
			mOrientation = orientation;
			setStrokeColors(colors);
		}

		public GradientState(GradientState state) {
			mChangingConfigurations = state.mChangingConfigurations;
			mShape = state.mShape;
			mGradient = state.mGradient;
			mAngle = state.mAngle;
			mOrientation = state.mOrientation;
			mColorStateList = state.mColorStateList;
			if (state.mStrokeColors != null) {
				mStrokeColors = state.mStrokeColors.clone();
			}
			if (state.mPositions != null) {
				mPositions = state.mPositions.clone();
			}
			mStrokeColorStateList = state.mStrokeColorStateList;
			mStrokeWidth = state.mStrokeWidth;
			mStrokeDashWidth = state.mStrokeDashWidth;
			mStrokeDashGap = state.mStrokeDashGap;
			mRadius = state.mRadius;
			if (state.mRadiusArray != null) {
				mRadiusArray = state.mRadiusArray.clone();
			}
			if (state.mPadding != null) {
				mPadding = new Rect(state.mPadding);
			}
			mWidth = state.mWidth;
			mHeight = state.mHeight;
			mInnerRadiusRatio = state.mInnerRadiusRatio;
			mThicknessRatio = state.mThicknessRatio;
			mInnerRadius = state.mInnerRadius;
			mThickness = state.mThickness;
			mDither = state.mDither;
			mCenterX = state.mCenterX;
			mCenterY = state.mCenterY;
			mGradientRadius = state.mGradientRadius;
			mGradientRadiusType = state.mGradientRadiusType;
			mUseLevel = state.mUseLevel;
			mUseLevelForShape = state.mUseLevelForShape;
			mOpaqueOverBounds = state.mOpaqueOverBounds;
			mOpaqueOverShape = state.mOpaqueOverShape;
			mTint = state.mTint;
			mTintMode = state.mTintMode;
			mThemeAttrs = state.mThemeAttrs;
			mAttrSize = state.mAttrSize;
			mAttrGradient = state.mAttrGradient;
			mAttrSolid = state.mAttrSolid;
			mAttrStroke = state.mAttrStroke;
			mAttrCorners = state.mAttrCorners;
			mAttrPadding = state.mAttrPadding;
		}

		@Override
		public Drawable newDrawable() {
			return new TwsSimpleGradientDrawable(this);
		}

		@Override
		public Drawable newDrawable(Resources res) {
			return new TwsSimpleGradientDrawable(this);
		}

		@Override
		public int getChangingConfigurations() {
			return mChangingConfigurations;
		}

		public void setShape(int shape) {
			mShape = shape;
			computeOpacity();
		}

		public void setGradientType(int gradient) {
			mGradient = gradient;
		}

		public void setGradientCenter(float x, float y) {
			mCenterX = x;
			mCenterY = y;
		}

		public void setStrokeColors(int[] colors) {
			Log.d(TAG, "setStrokeColors colors=" + colors);
			mStrokeColors = colors;
			mStrokeColorStateList = null;
			computeOpacity();
		}

		public void setColorStateList(ColorStateList colorStateList) {
			mColorStateList = colorStateList;
			computeOpacity();
		}

		private void computeOpacity() {
			mOpaqueOverBounds = false;
			mOpaqueOverShape = false;

			if (mStrokeColors != null) {
				for (int i = 0; i < mStrokeColors.length; i++) {
					if (!isOpaque(mStrokeColors[i])) {
						return;
					}
				}
			}

			// An unfilled shape is not opaque over bounds or shape
			if (mStrokeColors == null && mStrokeColorStateList == null) {
				return;
			}

			// Colors are opaque, so opaqueOverShape=true,
			mOpaqueOverShape = true;
			// and opaqueOverBounds=true if shape fills bounds
			mOpaqueOverBounds = mShape == RECTANGLE && mRadius <= 0 && mRadiusArray == null;
		}

		public void setStroke(int width, ColorStateList colorStateList, float dashWidth, float dashGap) {
			mStrokeWidth = width;
			mStrokeColorStateList = colorStateList;
			mStrokeDashWidth = dashWidth;
			mStrokeDashGap = dashGap;
			computeOpacity();
		}

		public void setCornerRadius(float radius) {
			if (radius < 0) {
				radius = 0;
			}
			mRadius = radius;
			mRadiusArray = null;
		}

		public void setCornerRadii(float[] radii) {
			mRadiusArray = radii;
			if (radii == null) {
				mRadius = 0;
			}
		}

		public void setSize(int width, int height) {
			mWidth = width;
			mHeight = height;
		}

		public void setGradientRadius(float gradientRadius, int type) {
			mGradientRadius = gradientRadius;
			mGradientRadiusType = type;
		}
	}

	static boolean isOpaque(int color) {
		return ((color >> 24) & 0xff) == 0xff;
	}

	/**
	 * Creates a new themed GradientDrawable based on the specified constant
	 * state.
	 * <p>
	 * The resulting drawable is guaranteed to have a new constant state.
	 * 
	 * @param state
	 *            Constant state from which the drawable inherits
	 */
	private TwsSimpleGradientDrawable(GradientState state) {
		mGradientState = state;

		initializeWithState(mGradientState);

		mGradientIsDirty = true;
		mMutated = false;
	}

	private void initializeWithState(GradientState state) {
		if (state.mStrokeColorStateList != null) {
			final int[] currentState = getState();
			final int stateColor = state.mStrokeColorStateList.getColorForState(currentState, 0);
			mFillPaint.setColor(stateColor);
		} else if (state.mStrokeColors == null) {
			// If we don't have a solid color and we don't have a gradient,
			// the app is stroking the shape, set the color to the default
			// value of state.mSolidColor
			mFillPaint.setColor(0);
		} else {
			// Otherwise, make sure the fill alpha is maxed out.
			mFillPaint.setColor(Color.BLACK);
		}

		mPadding = state.mPadding;

		if (state.mStrokeWidth >= 0) {
			mStrokePaint.setStyle(Paint.Style.STROKE);
			mStrokePaint.setStrokeWidth(state.mStrokeWidth);

			if (state.mStrokeColorStateList != null) {
				final int[] currentState = getState();
				final int strokeStateColor = state.mStrokeColorStateList.getColorForState(currentState, 0);
				mStrokePaint.setColor(strokeStateColor);
			}

			if (state.mStrokeDashWidth != 0.0f) {
				final DashPathEffect e = new DashPathEffect(
						new float[] { state.mStrokeDashWidth, state.mStrokeDashGap }, 0);
				mStrokePaint.setPathEffect(e);
			}
		}
	}
}
