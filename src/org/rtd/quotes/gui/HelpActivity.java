package org.rtd.quotes.gui;

import org.rtd.quotes.component.AlertDialogExt;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class HelpActivity extends SherlockFragmentActivity {

    Context context;
    final String LOG_TAG = "myLogs";
    LinearLayout lay1, lay2;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	switch (item.getItemId()) {
	case android.R.id.home:
	    this.finish();
	    // more code here for other cases
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
	setContentView(R.layout.help);

	lay1 = (LinearLayout) findViewById(R.id.lay1);
	TextView tv = (TextView) findViewById(R.id.help_tv_developer);
	tv.setMovementMethod(LinkMovementMethod.getInstance());
	context = getSupportActionBar().getThemedContext();

	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	getSupportActionBar().setTitle(R.string.about);

	getSupportActionBar().setDisplayShowTitleEnabled(true);

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
