package com.tencent.tws.core.apidemos.sortlistview;

import android.app.TwsActivity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;

public class SortListviewActivity extends TwsActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SortListView sortListView=new SortListView(getBaseContext());
        addContentView(sortListView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }


}
