package android.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.rickdynasty.tws.burgeon.R;

public class TwsMaxHeightLinearLayout extends TwsLinearLayout {
	private int mMaxHeight=0;

	public TwsMaxHeightLinearLayout(Context context) {
        super(context);
    }

    public TwsMaxHeightLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public TwsMaxHeightLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.TwsLinearLayout);		
		mMaxHeight =a.getDimensionPixelSize(R.styleable.TwsLinearLayout_maxHeight,0);	
		a.recycle();
    }
	
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//Log.d("TwsMaxHeightLinearLayout","onMeasure-------getMeasuredHeight()"+getMeasuredHeight());
		/*if(mMaxHeight > 0){
			int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
			int height = getMeasuredHeight();
			setMeasuredDimension(specWidthSize,Math.min(mMaxHeight, height));
		}*/
		//Log.d("TwsMaxHeightLinearLayout","onMeasure---11----getMeasuredHeight()"+getMeasuredHeight());
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if(mMaxHeight > 0){
			final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
			int height = getMeasuredHeight();
			int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
			if (height > mMaxHeight) {
				setMeasuredDimension(specWidthSize, mMaxHeight);
			} else {
				setMeasuredDimension(specWidthSize, height);
			}
		}
    }
	
	public void twsMeasureChildBeforeLayout(View child, int childIndex,
            int widthMeasureSpec, int totalWidth, int heightMeasureSpec,
            int totalHeight)
	{
		super.measureChildBeforeLayout(child, childIndex,widthMeasureSpec, totalWidth,heightMeasureSpec, totalHeight);
	}
	
	public void twsMeasureHorizontal(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.measureHorizontal(widthMeasureSpec,heightMeasureSpec);
	}
	
	public void twsSetMaxHeight(int height)
	{
		mMaxHeight = height;
	}
}
