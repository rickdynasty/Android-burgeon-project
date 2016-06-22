package com.rickdynasty.tws.core.apidemos.actionbar;

import android.app.TwsActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.rickdynasty.tws.burgeon.R;
import com.rickdynasty.tws.core.widget.ToggleButton;

public class ActionBarNormalEdit extends TwsActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getTwsActionBar().setTitle(R.string.action_bar_title);
		getTwsActionBar().setSubtitle(R.string.action_bar_subtitle);
		((Button)getTwsActionBar().getCloseView(false)).setText("上一步");
		ToggleButton b = (ToggleButton)getTwsActionBar().getMultiChoiceView(false);
		b.setText("下一步");
		b.setFixedText(true);
		getTwsActionBar().getEditView(false).setText("编辑文字");
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.actionbar_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "action_bar_settings_action_provider_no_handling", Toast.LENGTH_SHORT).show();
        return false;
    }

    public static class SettingsActionProvider extends ActionProvider {
        private static final Intent sSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
        private final Context mContext;

        public SettingsActionProvider(Context context) {
            super(context);
            mContext = context;
        }

        @Override
        public View onCreateActionView() {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            View view = layoutInflater.inflate(R.layout.action_bar_settings_action_provider, null);
            ImageButton button = (ImageButton) view.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(sSettingsIntent);
                }
            });
            return view;
        }

        @Override
        public boolean onPerformDefaultAction() {
            mContext.startActivity(sSettingsIntent);
            return true;
        }
    }
}
