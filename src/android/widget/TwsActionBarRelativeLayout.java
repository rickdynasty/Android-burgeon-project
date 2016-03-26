package android.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

public class TwsActionBarRelativeLayout extends RelativeLayout {
	public TwsActionBarRelativeLayout(Context context) {
		super(context);
	}
	
	public TwsActionBarRelativeLayout(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
	}
	
	protected boolean fitSystemWindows(Rect insets) {
		insets.top = 0;
		super.fitSystemWindows(insets);
    	return true;
    }

}