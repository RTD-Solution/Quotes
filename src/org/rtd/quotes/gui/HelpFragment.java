package org.rtd.quotes.gui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import org.rtd.quotes.gui.R;

public class HelpFragment extends SherlockFragment implements TabListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	return inflater.inflate(R.layout.help_fragment, container, false);
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
	// TODO Auto-generated method stub
	// ft.add(android.R.id.content, this, "Help");
	// ft.attach(this);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	// TODO Auto-generated method stub
	// ft.detach(this);

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
	// TODO Auto-generated method stub

    }
}
