package org.rtd.quotes.gui;

import java.util.ArrayList;
import java.util.List;

import org.rtd.quotes.database.DAOObject;
import org.rtd.quotes.database.DataBaseAdapter;
import org.rtd.quotes.gui.R;
import org.rtd.quotes.gui.adapter.ListArrayAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;

public class AllQuotesFragment extends SherlockFragment implements
	OnClickListener, TabListener {

    ToggleButton tbFav;
    ListView list;
    DataBaseAdapter DBA;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	// TODO Auto-generated method stub

	View allQuotesView = inflater.inflate(R.layout.all_quotes_fragment,
		container, false);

	DBA = new DataBaseAdapter(allQuotesView.getContext());
	DBA.open();

	Cursor c = DBA.getCursor();

	DAOObject dao = new DAOObject();
	final List<DAOObject> objects = new ArrayList<DAOObject>();
	for (int i = 0; i < c.getCount(); i++) {
	    dao = dao.retDAO(c, i);
	    objects.add(dao);
	}

	objects.add(new DAOObject());

	c.close();

	list = (ListView) allQuotesView.findViewById(R.id.list_view);

	list.setDividerHeight(1);
	list.setVerticalScrollBarEnabled(false);

	list.setAdapter(new ListArrayAdapter(getActivity(), allQuotesView
		.getContext(), R.layout.list_item, objects));
	list.setOnItemClickListener(new OnItemClickListener() {

	    @Override
	    public void onItemClick(AdapterView<?> adapterView, View arg1,
		    int position, long arg3) {

		// tbFav = (ToggleButton) arg1.findViewById(R.id.tbFav);

		/*
		 * if(objects.get(position).get_Id()==-1){
		 * arg1.setClickable(false); } else {
		 * objects.get(position).get_Id(); int qwe =
		 * objects.get(position).get_Id() - 1;
		 * 
		 * Intent intent = new Intent(); intent.putExtra("ID", qwe);
		 * intent.putExtra("DISTANCE", qwe);
		 * intent.setClass(QuotesActivity.this, InfoActivity.class);
		 * startActivity(intent);
		 */
	    }

	});

	return allQuotesView;
    }

    @Override
    public void onClick(View v) {

	if ((tbFav.isChecked())) {
	    tbFav.setChecked(true);
	} else {
	    tbFav.setChecked(false);
	}

    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
	// TODO Auto-generated method stub
	// ft.add(SettingsFragment.this, "Settings");
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
