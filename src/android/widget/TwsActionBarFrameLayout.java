package android.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.rickdynasty.tws.burgeon.R;

public class TwsActionBarFrameLayout extends FrameLayout {
	public TwsActionBarFrameLayout(Context context) {
		super(context);
	}
	
	public TwsActionBarFrameLayout(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
	}
	
	protected boolean fitSystemWindows(Rect insets) {
		if (android.os.Build.VERSION.SDK_INT > 18 && getResources().getBoolean(R.bool.config_statusbar_state)) {
			insets.top = 0;
		}
		super.fitSystemWindows(insets);
    	return true;
    }

}