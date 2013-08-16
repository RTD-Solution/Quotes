package org.rtd.quotes.gui;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.rtd.quotes.database.DAOObjectCFG;
import org.rtd.quotes.database.DataBaseAdapter;
import org.rtd.quotes.json.ChekVersion;
import org.rtd.quotes.json.LoadingTask;
import org.rtd.quotes.json.LoadingTask.LoadingTaskFinishedListener;
import org.rtd.quotes.variables.Variables;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class SplashActivity extends Activity implements
		LoadingTaskFinishedListener {

	ImageView img, img2;
	DataBaseAdapter DBA;
	SharedPreferences sPref;
	final String DB_VER = "DB_VERSION";
	int dbVersion;

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

		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());

		if (sp.getString("theme_style", "Темная").contains("Темная"))
			getApplicationContext().setTheme(R.style.Theme_Sherlock);
		else
			getApplicationContext().setTheme(R.style.Theme_Sherlock_Light);

		if (sp.getBoolean("preference_autoload", false)) {

			ChekVersion chekVer = new ChekVersion(this,
					"http://doronda.ruskyhosting.ru/config.json");
			String version = "1";
			try {
				version = chekVer.chekUrl();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			DBA = new DataBaseAdapter(this);
			DBA.open();
			Cursor curCfg = DBA.getCursorCfg();

			DAOObjectCFG dao = new DAOObjectCFG();
			final List<String> objects = new ArrayList<String>();
			for (int i = 0; i < curCfg.getCount(); i++) {
				dao = dao.retDAOcfg(curCfg, i);
				dbVersion = sp.getInt(DB_VER, 0);
				String str = dao.getVers();
				if (Integer.valueOf(dao.getVers()) > dbVersion)
					objects.add(dao.getLink());
			}

			curCfg.close();

			final String[] srt = new String[objects.size()];
			objects.toArray(srt);
			new CountDownTimer(3000, 1000) {
				@Override
				public void onFinish() {
					new LoadingTask(getBaseContext(), SplashActivity.this)
							.execute(srt);
				}

				@Override
				public void onTick(long millisUntilFinished) {

				}
			}.start();

		} else

			new CountDownTimer(3000, 1000) {
				@Override
				public void onFinish() {
					completeSplash();
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
		Intent intent = new Intent(SplashActivity.this, QuotesActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}