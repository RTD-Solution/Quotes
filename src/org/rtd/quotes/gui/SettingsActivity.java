package org.rtd.quotes.gui;

import org.rtd.quotes.component.AlertDialogExt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.MenuItem;

public class SettingsActivity extends SherlockPreferenceActivity implements
	com.actionbarsherlock.ActionBarSherlock.OnOptionsItemSelectedListener,
	OnSharedPreferenceChangeListener {

    Context context;
    final String LOG_TAG = "myLogs";
    LinearLayout lay1, lay2;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case android.R.id.home:
	    this.finish();
	}
	return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

	SharedPreferences sp = PreferenceManager
		.getDefaultSharedPreferences(getApplicationContext());

	if (sp.getString("theme_style", "Темная").contains("Темная"))
	    this.setTheme(R.style.Theme_Sherlock);
	else
	    this.setTheme(R.style.Theme_Sherlock_Light);

	super.onCreate(savedInstanceState);
	// setContentView(R.layout.main);

	addPreferencesFromResource(R.xml.preferences);
	getPreferenceScreen().getSharedPreferences()
		.registerOnSharedPreferenceChangeListener(this);

	lay1 = (LinearLayout) findViewById(R.id.lay1);

	context = getSupportActionBar().getThemedContext();

	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	getSupportActionBar().setTitle(R.string.settings);

	final ActionBar actionBar = getSupportActionBar();

	actionBar.setDisplayShowTitleEnabled(true);

    }

    protected void siteDialog(String message) {
	final AlertDialogExt dialog = new AlertDialogExt(this);
	dialog.setMessage(message);
	dialog.setButton1(getResources().getString(R.string.str_Ok),
		new View.OnClickListener() {

		    @Override
		    public void onClick(View v) {

			dialog.dismiss();
		    }
		});
	dialog.show();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
	// TODO Auto-generated method stub
	if (arg0.getString("theme_style", "Темная").contains("Темная"))
	    this.setTheme(R.style.Theme_Sherlock);
	else
	    this.setTheme(R.style.Theme_Sherlock_Light);
	this.getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	this.startActivity(this.getIntent());
    }

    @Override
    public void onResume() {
	SharedPreferences sp = PreferenceManager
		.getDefaultSharedPreferences(getApplicationContext());

	if (sp.getString("theme_style", "Темная").contains("Темная"))
	    this.setTheme(R.style.Theme_Sherlock);
	else
	    this.setTheme(R.style.Theme_Sherlock_Light);

	super.onResume();

    }
}
