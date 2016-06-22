package com.rickdynasty.tws.core.apidemos.blur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.TwsActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.rickdynasty.tws.burgeon.R;
import com.rickdynasty.tws.core.widget.ListView;

public class ListViewFullScreenNoBlur extends TwsActivity {
	
	private Button header1, header2;
	private Button footer1, footer2;
	
	private ActionMode mActionMode;
	
	private ListView mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		Window window = getWindow();
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        
		header1 = new Button(ListViewFullScreenNoBlur.this);
        header1.setText("Header1");
        header2 = new Button(ListViewFullScreenNoBlur.this);
        header2.setText("Header2");
        footer1 = new Button(ListViewFullScreenNoBlur.this);
        footer1.setText("Footer1");
        footer2 = new Button(ListViewFullScreenNoBlur.this);
        footer2.setText("Footer2");
		
		setContentView(R.layout.activity_blur);

		mList = (ListView) findViewById(R.id.list);

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

		mList.setFirstItemHigher(true);
//		mList.setLastItemHigher(true);
		mList.setHeaderBlankWithStatusbar(false);
		mList.setFooterBlank(false);
		mList.setAdapter(adapter);
		mList.addHeaderView(header1);
		mList.addHeaderView(header2);
		mList.addFooterView(footer1);
		mList.addFooterView(footer2);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
}
