package com.tencent.tws.core.apidemos.blur;

import android.app.TwsActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.Button;

import com.tencent.tws.core.app.ActionBar;
import com.tencent.tws.burgeon.R;

public class ActionModeBlur extends TwsActivity {
    private ActionBar mActionBar;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
        setContentView(R.layout.act_actionmode_blur);
        setActionModeOverLayBgBlur(true);
        mButton = (Button) findViewById(R.id.start_actionmode);
        mButton.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActionMode(mCallback);
                return false;
            }
        });
        mActionBar = getTwsActionBar();
    }

    ActionMode.Callback mCallback = new ActionMode.Callback() {

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            menu.add("title");
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

    };

}
