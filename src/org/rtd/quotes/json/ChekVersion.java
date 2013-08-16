package org.rtd.quotes.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.rtd.quotes.database.DAOObjectCFG;
import org.rtd.quotes.database.DataBaseAdapter;
import org.rtd.quotes.variables.Variables;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;

public class ChekVersion {

	static InputStream is = null;
	static String json = "";
	Context context;
    String str = null;
    boolean var;
    DAOObjectCFG daoObjCfg;
    DataBaseAdapter DBA;
	SharedPreferences sPref;
	final String DB_VER = "DB_VERSION";
	int dbVersion;

	public ChekVersion(Context cnt, String str){
		this.str = str;
		this.context = cnt;
		Variables.getInstance().Initialize(cnt);

	}

	public String chekUrl() throws JSONException {
		SharedPreferences sp = PreferenceManager
				  .getDefaultSharedPreferences(context);
		dbVersion = sp.getInt(DB_VER, 0);
		DBA = new DataBaseAdapter(context);		
		DBA.open();
		DBA.deleteCfg();
		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(str);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "Windows-1251"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		JSONObject jObj = null;
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
		daoObjCfg = new DAOObjectCFG();
		String newVersion = jObj.getString("version");
		JSONArray updates = jObj.getJSONArray("update");
		for(int i=0; i< updates.length(); i++){
			JSONObject updtElement = updates.getJSONObject(i);
			daoObjCfg.setVers(updtElement.getString("vers"));
			daoObjCfg.setNum(updtElement.getString("num"));
			daoObjCfg.setDate(updtElement.getString("date"));
			daoObjCfg.setLink(updtElement.getString("link"));
			if(dbVersion<Integer.valueOf(updtElement.getString("vers"))){
			DBA.insertEntryCfg(daoObjCfg);
			//Variables.getInstance().setVariable(DB_VER, updtElement.getString("vers"));
			}
		}
	
		if(newVersion==null){
			return "1";
		}
		DBA.close();
		return newVersion;

	}

	
	
}
