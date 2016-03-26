package com.tencent.tws.assistant.apidemos.actionbar;

import android.app.TwsActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.tencent.tws.assistant.widget.Toast;
import com.tencent.tws.devicemanager.R;

public class ActionBarMiniMode extends TwsActivity {

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getTwsActionBar().setTitle(R.string.action_bar_title);
        getTwsActionBar().setSubtitle(R.string.action_bar_subtitle);

        getTwsActionBar().getTitleView(false).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getTwsActionBar().setDisplayShowCustomEnabled(true);
                getTwsActionBar().setDisplayShowHomeEnabled(false);
                flag = getTwsActionBar().startMiniMode();
//                flag = getTwsActionBar().exitMiniMode();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (flag && keyCode == KeyEvent.KEYCODE_BACK) {
            getTwsActionBar().setDisplayShowCustomEnabled(false);
            getTwsActionBar().setDisplayShowHomeEnabled(true);
            flag = getTwsActionBar().exitMiniMode();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "press", Toast.LENGTH_SHORT).show();
        return false;
    }
}
