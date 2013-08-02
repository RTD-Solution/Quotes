package org.rtd.quotes.gui;

import org.rtd.quotes.gui.R;
import org.rtd.quotes.json.LoadingTask;
import org.rtd.quotes.json.LoadingTask.LoadingTaskFinishedListener;
import org.rtd.quotes.variables.Variables;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity implements
		LoadingTaskFinishedListener {
	
	ImageView img, img2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		Variables.getInstance().Initialize(getApplicationContext());

		img = (ImageView) findViewById(R.id.SplashImageView);
		RotateAnimation rotateAnimation = new RotateAnimation(30, 90,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotateAnimation.setInterpolator(new LinearInterpolator());
		rotateAnimation.setDuration(10000);
		rotateAnimation.setFillAfter(true);
		rotateAnimation.setRepeatCount(-1);
		img.startAnimation(rotateAnimation);
		img2 = (ImageView) findViewById(R.id.SplashImageView2);
		RotateAnimation rotateAnimation2 = new RotateAnimation(-30,
				-10.0f * 360.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation2.setInterpolator(new LinearInterpolator());
		rotateAnimation2.setDuration(10000);
		rotateAnimation2.setFillAfter(true);
		rotateAnimation2.setRepeatCount(-1);
		img2.startAnimation(rotateAnimation2);

		// ��������� ���� � ��������� ��
		new CountDownTimer(3000, 1000) {
			@Override
			public void onFinish() {
				new LoadingTask(getBaseContext(), MainActivity.this)
						.execute("http://doronda.ruskyhosting.ru/cit.json");
			}

			@Override
			public void onTick(long millisUntilFinished) {

			}
		}.start();

	}

	@Override
	public void onTaskFinished() {
		completeSplash();
	}

	private void completeSplash() {
		startApp();
		finish();
	}

	private void startApp() {
		Intent intent = new Intent(MainActivity.this, QuotesActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}