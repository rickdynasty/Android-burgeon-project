package com.tencent.tws.burgeon.widget.progressbar;

import android.animation.ValueAnimator;
import android.app.TwsActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.SeekBar;
import android.widget.Toast;

import com.tencent.tws.burgeon.widget.CirculaPogressBar;
import com.tencent.tws.burgeon.widget.MasterLayout;
import com.tencent.tws.devicemanager.R;

public class PogressBarActivity extends TwsActivity implements SeekBar.OnSeekBarChangeListener, OnClickListener {

	private CirculaPogressBar mProgressView;
	private SeekBar mSeekBar;

	MasterLayout masterLayout;
	CircularProgressButton mCustomSelector01;
	CircularProgressButton mCustomSelector02;
	CircularProgressButton mProgressPadding01;
	CircularProgressButton mProgressPadding02;
	CircularProgressButton mIntegerProgress01;
	CircularProgressButton mIntegerProgress02;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progressbar);
		mProgressView = (CirculaPogressBar) findViewById(R.id.my_progress);
		mSeekBar = (SeekBar) findViewById(R.id.progress_seekBar);
		mSeekBar.setOnSeekBarChangeListener(this);
		mSeekBar.setMax(100);
		mSeekBar.setProgress(80);

		mProgressView.setMaxCount(100.0f);
		mProgressView.setCurrentCount(mSeekBar.getProgress());
		mProgressView.setScore(mSeekBar.getProgress());

		masterLayout = (MasterLayout) findViewById(R.id.MasterLayout01);
		masterLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				masterLayout.animation();
				if (masterLayout.flg_frmwrk_mode == 1) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(PogressBarActivity.this, "Starting download", Toast.LENGTH_SHORT).show();
						}
					});
					new DownLoadSigTask().execute();
				}
				if (masterLayout.flg_frmwrk_mode == 2) {
					new DownLoadSigTask().cancel(true);
					masterLayout.reset();
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(PogressBarActivity.this, "Download stopped", Toast.LENGTH_SHORT).show();
						}
					});
				}
				if (masterLayout.flg_frmwrk_mode == 3) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(PogressBarActivity.this, "Download complete", Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		});

		mCustomSelector01 = (CircularProgressButton) findViewById(R.id.circularButton_custom_selector01);
		mCustomSelector01.setIndeterminateProgressMode(true);
		mCustomSelector01.setOnClickListener(this);

		mCustomSelector02 = (CircularProgressButton) findViewById(R.id.circularButton_custom_selector02);
		mCustomSelector02.setIndeterminateProgressMode(true);
		mCustomSelector02.setOnClickListener(this);

		mProgressPadding01 = (CircularProgressButton) findViewById(R.id.circularButton_progress_padding01);
		mProgressPadding01.setIndeterminateProgressMode(true);
		mProgressPadding01.setOnClickListener(this);

		mProgressPadding02 = (CircularProgressButton) findViewById(R.id.circularButton_progress_padding02);
		mProgressPadding02.setIndeterminateProgressMode(true);
		mProgressPadding02.setOnClickListener(this);

		mIntegerProgress01 = (CircularProgressButton) findViewById(R.id.circularButton_integer_progress01);
		mIntegerProgress01.setIndeterminateProgressMode(true);
		mIntegerProgress01.setOnClickListener(this);
		mIntegerProgress02 = (CircularProgressButton) findViewById(R.id.circularButton_integer_progress02);
		mIntegerProgress02.setIndeterminateProgressMode(true);
		mIntegerProgress02.setOnClickListener(this);
	}

	class DownLoadSigTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
		}

		@Override
		protected String doInBackground(final String... args) {
			for (int i = 0; i <= 100; i++) {
				try {
					Thread.sleep(50);

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				publishProgress(i);

			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			masterLayout.cusview.setupprogress(progress[0]);
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean arg2) {
		if (seekBar == mSeekBar) {
			mProgressView.setCurrentCount(progress);
			mProgressView.setScore(progress);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.circularButton_custom_selector01:
			if (mCustomSelector01.getProgress() == 0) {
				mCustomSelector01.setProgress(50);
			} else if (mCustomSelector01.getProgress() == 100) {
				mCustomSelector01.setProgress(0);
			} else {
				mCustomSelector01.setProgress(100);
			}
			break;
		case R.id.circularButton_custom_selector02:
			if (mCustomSelector02.getProgress() == 0) {
				mCustomSelector02.setProgress(50);
			} else if (mCustomSelector02.getProgress() == -1) {
				mCustomSelector02.setProgress(0);
			} else {
				mCustomSelector02.setProgress(-1);
			}
			break;
		case R.id.circularButton_progress_padding01:
			if (mProgressPadding01.getProgress() == 0) {
				mProgressPadding01.setProgress(50);
			} else if (mProgressPadding01.getProgress() == 100) {
				mProgressPadding01.setProgress(0);
			} else {
				mProgressPadding01.setProgress(100);
			}
			break;
		case R.id.circularButton_progress_padding02:
			if (mProgressPadding02.getProgress() == 0) {
				mProgressPadding02.setProgress(50);
			} else if (mProgressPadding02.getProgress() == -1) {
				mProgressPadding02.setProgress(0);
			} else {
				mProgressPadding02.setProgress(-1);
			}
			break;

		case R.id.circularButton_integer_progress01:
			if (mIntegerProgress01.getProgress() == 0) {
				ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
				widthAnimation.setDuration(1500);
				widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
				widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						Integer value = (Integer) animation.getAnimatedValue();
						mIntegerProgress01.setProgress(value);
					}
				});
				widthAnimation.start();
			} else {
				mIntegerProgress01.setProgress(0);
			}
			break;

		case R.id.circularButton_integer_progress02:
			if (mIntegerProgress02.getProgress() == 0) {
				ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
				widthAnimation.setDuration(1500);
				widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
				widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						Integer value = (Integer) animation.getAnimatedValue();
						mIntegerProgress02.setProgress(value);
						if (value == 99) {
							mIntegerProgress02.setProgress(-1);
						}
					}
				});
				widthAnimation.start();
			} else {
				mIntegerProgress02.setProgress(0);
			}
			break;

		default:
			break;
		}

	}
}
