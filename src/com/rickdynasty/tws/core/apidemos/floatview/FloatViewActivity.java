package com.rickdynasty.tws.core.apidemos.floatview;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.TwsActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.rickdynasty.tws.burgeon.R;
import com.rickdynasty.tws.core.apidemos.widget.TwsBaseWidget;
import com.rickdynasty.tws.core.widget.FloatInterface;
import com.rickdynasty.tws.core.widget.FloatInterface.OnContentClickListener;
import com.rickdynasty.tws.core.widget.FloatView;

public class FloatViewActivity extends TwsActivity {

	private FloatView view;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				if (msg.what == 0) {
					Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher_settings);
					view.update(drawable, "新的通知哦", "知道啦，谢谢");

					view.setOnContentClickListener(new OnContentClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Log.d("hlx", "onClick onClick onClick 11111");
							Intent intent = new Intent(getApplicationContext(), TwsBaseWidget.class);
							startActivity(intent);
						}
					});

					view.setOnDismissListener(new FloatInterface.OnDismissListener() {

						@Override
						public void onDismiss(FloatInterface arg0) {
							// TODO Auto-generated method stub
							Log.d("hlx", "setOnDismissListener");
						}
					});
					// view.setSlideInAnimator();
				}

				break;
			case 1:
				Log.d("hlx", "onClick onClick onClick 22222");
				updateCustomLayout();
				break;

			default:

				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		defalutLayoutFloatView();
		// customLayoutFloatView();
	}

	public void defalutLayoutFloatView() {
		view = new FloatView(FloatViewActivity.this);
		view.setIcon(R.drawable.ic_burgeon);
		view.setTitle("搜索试试");
		view.setSubTitle("点哪里哦");
		view.show(false);
		setTimer(500, 0);
	}

	public void customLayoutFloatView() {
		view = new FloatView(FloatViewActivity.this);
		view.setContentView(R.layout.float_content);
		Button btn = (Button) view.findViewById(R.id.floatdial);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				view.dismissDelayed(1000);
			}
		});
		view.show(true);
		// Intent intent = new Intent(getApplicationContext(),
		// TwsBaseWidget.class);
		// startActivity(intent);
		setTimer(2000, 1);

	}

	public void updateCustomLayout() {

		View view1 = LayoutInflater.from(getBaseContext()).inflate(R.layout.float_content_two, null);
		view.update(view1);
	}

	public void setTimer(long delay, final int type) {
		TimerTask dismissTask = new TimerTask() {
			public void run() {
				// 发通知
				Log.d("hlx", "setTimer");
				if (view.isShowing()) {
					Log.d("hlx", "setTimer isShowing = " + "true");
					Message msg = new Message();
					msg.what = type;
					mHandler.sendMessage(msg);
				}
			}
		};
		Timer timer = new Timer();
		timer.schedule(dismissTask, delay);
	}

}
