package org.rtd.quotes.json;

import org.json.JSONException;
import org.json.JSONObject;
import org.rtd.quotes.json.DownloadJSON;
import org.rtd.quotes.json.JSONParser;



import android.content.Context;
import android.os.AsyncTask;

public class LoadingTask extends AsyncTask<String, String, Integer> {

	public interface LoadingTaskFinishedListener {
		void onTaskFinished(); // If you want to pass something back to the listener add a param to this method
	}

	// This is the progress bar you want to update while the task is in progress
	private final Context cnt;
	// This is the listener that will be told when this task is finished
	private final LoadingTaskFinishedListener finishedListener;

	/**
	 * A Loading task that will load some resources that are necessary for the app to start
	 * @param progressBar - the progress bar you want to update while the task is in progress
	 * @param finishedListener - the listener that will be told when this task is finished
	 */
	public LoadingTask(Context cnt, LoadingTaskFinishedListener finishedListener) {
		this.cnt = cnt;
		this.finishedListener = finishedListener;
	}

	@Override
	protected Integer doInBackground(String... params) {
	//	Log.i("Tutorial", "Starting task with url: "+params[0]);
		if(resourcesDontAlreadyExist()){
			downloadResources(params[0]);
		}
		// Perhaps you want to return something to your post execute
		return 1234;
	}

	private boolean resourcesDontAlreadyExist() {
		// Here you would query your app's internal state to see if this download had been performed before
		// Perhaps once checked save this in a shared preference for speed of access next time
		return true; // returning true so we show the splash every time
	}


	private void downloadResources(String str) {
		// We are just imitating some process thats takes a bit of time (loading of resources / downloading)

		DownloadJSON dJson = new DownloadJSON();
		
		JSONObject jObj = null;
		try {
			jObj = new JSONObject(dJson.downloadUrl(str));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
		JSONParser jp = new JSONParser(cnt, jObj);
		jp.Parser();

	}


	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		finishedListener.onTaskFinished(); // Tell whoever was listening we have finished
	}
}