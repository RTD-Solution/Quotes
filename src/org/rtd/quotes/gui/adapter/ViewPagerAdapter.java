package org.rtd.quotes.gui.adapter;

import org.rtd.quotes.gui.AllQuotesFragment;
import org.rtd.quotes.gui.FavoritesFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    // Declare the number of ViewPager pages
    final int PAGE_COUNT = 2;

    public ViewPagerAdapter(FragmentManager fm) {
	super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
	switch (arg0) {

	case 0:
	    AllQuotesFragment allQuotesFragment = new AllQuotesFragment();
	    return allQuotesFragment;

	case 1:
	    FavoritesFragment settingsFragment = new FavoritesFragment();
	    return settingsFragment;

	}
	return null;
    }

    @Override
    public int getCount() {
	// TODO Auto-generated method stub
	return PAGE_COUNT;
    }

}
