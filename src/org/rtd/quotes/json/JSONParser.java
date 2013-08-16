package org.rtd.quotes.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.rtd.quotes.database.DAOObject;
import org.rtd.quotes.database.DataBaseAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class JSONParser {
	private JSONObject jObject;
	DataBaseAdapter DBA;
	Context context;
	DAOObject daoObj;
	SharedPreferences sp;
	final String DB_VER = "DB_VERSION";
	int dbVersion;

	public JSONParser(Context context, JSONObject jObject) {
		this.jObject = jObject;
		this.context = context;
		sp = PreferenceManager
				.getDefaultSharedPreferences(context);
	}

	public void Parser() {
      
		DBA = new DataBaseAdapter(context);
		DBA.open();
		//DBA.delete();
		daoObj = new DAOObject();
		try {
			dbVersion = jObject.getInt("version");
			daoObj.setDate(jObject.getString("date"));
			JSONArray jsonArray = jObject.getJSONArray("quotes");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject elem = jsonArray.getJSONObject(i);
				daoObj.setGenre(elem.getString("genre"));
				JSONArray jsonArray2 = elem.getJSONArray("list");
				for (int y = 0; y < jsonArray2.length(); y++) {
					JSONObject elem2 = jsonArray2.getJSONObject(y);
					daoObj.setName(elem2.getString("name"));
					JSONArray jsonArray3 = elem2.getJSONArray("objects");
					for (int z = 0; z < jsonArray3.length(); z++) {
						JSONObject objects = jsonArray3.getJSONObject(z);
						daoObj.setId(objects.getInt("id"));
						daoObj.setBody(objects.getString("body"));
						daoObj.setFavorite(objects.getInt("favorite"));

						DBA.insertEntry(daoObj);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBA.close();
		sp.edit().putInt(DB_VER, dbVersion).commit();
	}
}
