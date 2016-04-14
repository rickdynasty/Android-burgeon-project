package com.tencent.tws.core.apidemos.blur;

import android.app.TwsActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

import com.tencent.tws.core.widget.TwsScrollWrapper;
import com.tencent.tws.burgeon.R;

public class ScrollViewFullScreenBlur extends TwsActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Window window = getWindow();
        window.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		
		TwsScrollWrapper wrapper = new TwsScrollWrapper().contentLayoutWithStatusbar(this, R.layout.activity_scrollview);
		wrapper.setHeaderBlank(true);
		wrapper.setFooterBlank(false);
        setContentView(wrapper.createView(this));
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.action_bar_menu, menu);
    	return super.onCreateOptionsMenu(menu);
    }
}