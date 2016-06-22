package com.rickdynasty.tws.core.apidemos;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.TwsListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;

import com.rickdynasty.tws.burgeon.R;
import com.rickdynasty.tws.core.widget.ListView;
import com.rickdynasty.tws.core.widget.Toast;

public class ApiDemos extends TwsListActivity {
	private static final String CATEGORY_SAMPLE = "rickdynasty.tws.intent.category.SAMPLE_CODE";
	private static final String EXTRA_PROGRAM = "com.rickdynasty.tws.burgeon.Path";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String path = intent.getStringExtra(EXTRA_PROGRAM);

		if (path == null) {
			path = "";
		}

		setListAdapter(new SimpleAdapter(this, getData(path), R.layout.simple_list_item_1, new String[] { "title" }, new int[] { android.R.id.text1 }));
		getListView().setTextFilterEnabled(true);
		Toast.makeText(this, "ApiDemos", Toast.LENGTH_SHORT).show();
	}

	protected List<Map<String, Object>> getData(String prefix) {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(CATEGORY_SAMPLE);

		PackageManager pm = getPackageManager();
		List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

		if (null == list)
			return myData;

		String[] prefixPath;
		String prefixWithSlash = prefix;

		if (prefix.equals("")) {
			prefixPath = null;
		} else {
			prefixPath = prefix.split("/");
			prefixWithSlash = prefix + "/";
		}

		int len = list.size();

		Map<String, Boolean> entries = new HashMap<String, Boolean>();

		for (int i = 0; i < len; i++) {
			ResolveInfo info = list.get(i);
			CharSequence labelSeq = info.loadLabel(pm);
			String label = labelSeq != null ? labelSeq.toString() : info.activityInfo.name;

			if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {

				String[] labelPath = label.split("/");

				String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

				if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
					addItem(myData, nextLabel, activityIntent(info.activityInfo.applicationInfo.packageName, info.activityInfo.name));
				} else {
					if (entries.get(nextLabel) == null) {
						addItem(myData, nextLabel, browseIntent(prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
						entries.put(nextLabel, true);
					}
				}
			}
		}

		Collections.sort(myData, sDisplayNameComparator);

		return myData;
	}

	private final static Comparator<Map<String, Object>> sDisplayNameComparator = new Comparator<Map<String, Object>>() {
		private final Collator collator = Collator.getInstance();

		public int compare(Map<String, Object> map1, Map<String, Object> map2) {
			return collator.compare(map1.get("title"), map2.get("title"));
		}
	};

	protected Intent activityIntent(String pkg, String componentName) {
		Intent result = new Intent();
		result.setClassName(pkg, componentName);
		return result;
	}

	protected Intent browseIntent(String path) {
		Intent result = new Intent();
		result.setClass(this, ApiDemos.class);
		result.putExtra(EXTRA_PROGRAM, path);
		return result;
	}

	protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("intent", intent);
		data.add(temp);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

		Intent intent = (Intent) map.get("intent");
		startActivity(intent);
	}
}
