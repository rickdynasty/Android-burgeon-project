package com.rickdynasty.tws.core.apidemos.blur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.TwsActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.SimpleAdapter;

import com.rickdynasty.tws.burgeon.R;
import com.rickdynasty.tws.core.app.ActionBar.SplitBlurListener;
import com.rickdynasty.tws.core.widget.AdapterView;
import com.rickdynasty.tws.core.widget.AdapterView.OnItemLongClickListener;
import com.rickdynasty.tws.core.widget.ListView;

public class ActionModeListViewFullScreenBlur extends TwsActivity {
	
	private ActionMode mActionMode;
	
	private ListView mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		Window window = getWindow();
        window.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        
		setContentView(R.layout.activity_blur);
		
//		getTwsActionBar().setShowHideAnimationEnabled(false);

		mList = (ListView) findViewById(R.id.list);
		
		getTwsActionBar().setSplitBlurListener(new SplitBlurListener() {
			
			@Override
			public void doBlur() {
				mList.enableBottomBlur(true);
			}
		});

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Map<String, Object> item;
		for (int i = 0; i < 200; i++) {
			item = new HashMap<String, Object>();
			item.put("姓名", "AAAAAAAAAAA张三");
			item.put("性别", "男BBBBBB");
			item.put("aa", "FFFFFFFF");
			data.add(item);
		}
		final SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.listitem_blur, new String[] { "姓名", "性别", "aa" },
				new int[] { R.id.mview1, R.id.mview2, R.id.mview3 });

		mList.setHeaderBlankWithStatusbar(false);
		mList.setFirstItemHigher(true);
		mList.setFooterBlank(false);
		mList.setAdapter(adapter);
		
		mList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				mActionMode = startActionMode(mActionModeCallback);
                mActionMode.setTitle("action Mode");
				return false;
			}
		});
	}
	
	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        	
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            getTwsActionBar().twsSetActionModeBackOnClickListener(null);
            mActionMode = null;
            mList.enableBottomBlur(false);
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            menu.add("title2").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            menu.add("title1").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.add("title3").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            mode.finish();
            return true;
        }
    };
}
