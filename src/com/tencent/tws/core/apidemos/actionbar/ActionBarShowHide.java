package com.tencent.tws.core.apidemos.actionbar;

import android.app.TwsActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.tencent.tws.core.app.ActionBar;
import com.tencent.tws.core.app.ActionBar.ActionBarHideListener;
import com.tencent.tws.core.app.ActionBar.ActionBarShowListener;
import com.tencent.tws.core.widget.Toast;
import com.tencent.tws.burgeon.R;

public class ActionBarShowHide extends TwsActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.action_bar_split);
        
        TextView tv = (TextView) findViewById(R.id.actiontext);
        final ActionBar actionBar = getTwsActionBar();
        actionBar.setShowHideAnimationEnabled(true);
        tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                if (actionBar != null) {
                    if (actionBar.isShowing()) {
                    	getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    	// hide top and split actionBar
                    	actionBar.hide();
                    	// hide split actionBar
//                    	actionBar.splitActionbar_hide();
                    	// hide top actionBar
//                    	actionBar.topActionbar_hide();
                    } else {
                    	getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    	// show top and split actionBar
                    	actionBar.show();
                    	// show split actionBar
//                    	actionBar.splitActionbar_show();
                    	// show top actionBar
//                    	actionBar.topActionbar_show();
                    }
                }

			}
		});
        
        actionBar.setActionBarShowListener(new ActionBarShowListener() {
			
			@Override
			public void doShowBar() {
				Toast.makeText(ActionBarShowHide.this, "ActionBarShow", Toast.LENGTH_SHORT).show();
			}
		});
        
        actionBar.setActionBarHideListener(new ActionBarHideListener() {
			
			@Override
			public void doHideBar() {
				Toast.makeText(ActionBarShowHide.this, "ActionBarHide", Toast.LENGTH_SHORT).show();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Normal item");
        MenuItem actionItem = menu.add("Action Button");
        actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        actionItem.setIcon(android.R.drawable.ic_menu_share);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }
}
