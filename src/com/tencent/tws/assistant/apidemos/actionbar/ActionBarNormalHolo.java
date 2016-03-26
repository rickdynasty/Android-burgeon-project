package com.tencent.tws.assistant.apidemos.actionbar;

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

import com.tencent.tws.assistant.widget.ToggleButton;
import com.tencent.tws.devicemanager.R;

public class ActionBarNormalHolo extends TwsActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((Button)getTwsActionBar().getCloseView(false)).setText("上一步");
		ToggleButton b = (ToggleButton)getTwsActionBar().getMultiChoiceView(false);
		b.setText("下一步");
		b.setFixedText(true);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.action_bar_menu_holo, menu);
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
