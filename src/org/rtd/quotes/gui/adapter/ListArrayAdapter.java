package org.rtd.quotes.gui.adapter;

import java.util.List;

import org.rtd.quotes.database.DAOObject;
import org.rtd.quotes.gui.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ListArrayAdapter extends ArrayAdapter<DAOObject> {
    private LayoutInflater mInflater;
    Context context;

    public ListArrayAdapter(Activity a, Context context,
	    int textViewResourceId, List<DAOObject> objects) {
	super(context, textViewResourceId, objects);
	this.context = context;
	mInflater = (LayoutInflater) this.getContext().getSystemService(
		Context.LAYOUT_INFLATER_SERVICE);
	// evInf = new EventInfo();

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

	View row = convertView;
	final ViewHolder holder = new ViewHolder();
	final DAOObject daoObj = getItem(position);
	row = mInflater.inflate(R.layout.list_item, null);

	holder.txtTypeName = (TextView) row.findViewById(R.id.txtTypeName);
	holder.txtId = (TextView) row.findViewById(R.id.txtId);
	holder.txtDate = (TextView) row.findViewById(R.id.txtDate);
	holder.tbFavorite = (ToggleButton) row.findViewById(R.id.tbFav);

	holder.tbFavorite
		.setOnCheckedChangeListener(new OnCheckedChangeListener() {

		    @Override
		    public void onCheckedChanged(CompoundButton buttonView,
			    boolean isChecked) {

			if (isChecked) {
			    holder.tbFavorite.setChecked(true);
			    Toast.makeText(getContext(), "true",
				    Toast.LENGTH_LONG).show();
			    holder.tbFavorite
				    .setBackgroundResource(android.R.drawable.btn_star_big_on);
			} else {
			    holder.tbFavorite.setChecked(false);
			    Toast.makeText(getContext(), "false",
				    Toast.LENGTH_LONG).show();
			    holder.tbFavorite
				    .setBackgroundResource(android.R.drawable.btn_star_big_off);
			}
		    }
		});
	// holder.itemLay = (LinearLayout) row.findViewById(R.id.imgLay);
	holder.mainLay = (RelativeLayout) row.findViewById(R.id.mainLay);

	holder.txtTypeName.setText(daoObj.getBody());
	holder.txtId.setText(String.valueOf(daoObj.getId()));
	holder.txtDate.setText("1111.11.11");

	if (daoObj.getFavorite() == 1)
	    holder.tbFavorite
		    .setBackgroundResource(android.R.drawable.btn_star_big_on);
	else
	    holder.tbFavorite
		    .setBackgroundResource(android.R.drawable.btn_star_big_off);

	row.setTag(holder);

	return row;
    }

    class ViewHolder {
	// ImageView img;
	TextView txtTypeName;
	LinearLayout itemLay;
	TextView txtId;
	TextView txtDate;
	RelativeLayout mainLay;
	ToggleButton tbFavorite;
    }
}