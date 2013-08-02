package org.rtd.quotes.gui;

import org.rtd.quotes.component.AlertDialogExt;
import org.rtd.quotes.gui.R;
import org.rtd.quotes.gui.adapter.ViewPagerAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class QuotesActivity extends SherlockFragmentActivity implements
	OnNavigationListener {

    Context context;
    ArrayAdapter adapter;
    final String LOG_TAG = "myLogs";
    LinearLayout lay1, lay2;
    ViewPager pager;
    Tab tab;

    // @Override
    // public boolean onOptionsItemSelected(MenuItem item) {
    // item.setTitle("item");
    // return super.onOptionsItemSelected(item);
    //
    // };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	menu.add("Настройки")
		.setIcon(R.drawable.spinner_ring)
		.setShowAsAction(
			MenuItem.SHOW_AS_ACTION_IF_ROOM
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	menu.add("О программе")
		.setIcon(R.drawable.spinner_ring)
		.setShowAsAction(
			MenuItem.SHOW_AS_ACTION_IF_ROOM
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
	return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);

	pager = (ViewPager) findViewById(R.id.pager);

	FragmentManager fm = getSupportFragmentManager();

	lay1 = (LinearLayout) findViewById(R.id.lay1);

	context = getSupportActionBar().getThemedContext();

	getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	final ActionBar actionBar = getSupportActionBar();

	actionBar.setDisplayShowTitleEnabled(true);

	ViewPager.SimpleOnPageChangeListener ViewPagerListener = new ViewPager.SimpleOnPageChangeListener() {
	    @Override
	    public void onPageSelected(int position) {
		super.onPageSelected(position);
		actionBar.setSelectedNavigationItem(position);
	    }
	};

	pager.setOnPageChangeListener(ViewPagerListener);
	ViewPagerAdapter viewpageradapter = new ViewPagerAdapter(fm);
	pager.setAdapter(viewpageradapter);

	ActionBar.TabListener tabListener = new ActionBar.TabListener() {

	    @Override
	    public void onTabSelected(Tab tab, FragmentTransaction ft) {
		pager.setCurrentItem(tab.getPosition());
	    }

	    @Override
	    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	    }

	    @Override
	    public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	    }
	};

	tab = actionBar.newTab().setText("Все цитаты")
		.setTabListener(new FavoritesFragment())
		.setIcon(R.drawable.spinner_ring);
	tab.setTabListener(tabListener);
	actionBar.addTab(tab);

	tab = actionBar.newTab().setText("Избранное")
		.setTabListener(new FavoritesFragment())
		.setIcon(R.drawable.spinner_ring);
	tab.setTabListener(tabListener);
	actionBar.addTab(tab);

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
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
	// TODO Auto-generated method stub
	Toast.makeText(context, adapter.getItem(itemPosition).toString(),
		Toast.LENGTH_LONG).show();
	return false;
    }
}
