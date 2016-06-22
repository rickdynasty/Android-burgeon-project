package com.rickdynasty.tws.core.apidemos.listview;

import android.app.TwsListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;

import com.rickdynasty.tws.burgeon.R;
import com.rickdynasty.tws.core.widget.ListView;

public class MultipleChoiceList extends TwsListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(this, R.layout.select_dialog_multichoice, GENRES));

		final ListView listView = getListView();

		listView.setItemsCanFocus(false);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		listView.setClickDelay(false);
	}

	private static final String[] GENRES = new String[] { "Action", "Adventure", "Animation", "Children", "Comedy", "Documentary", "Drama", "Foreign", "History", "Independent", "Romance", "Sci-Fi",
			"Television", "Thriller" };
}
