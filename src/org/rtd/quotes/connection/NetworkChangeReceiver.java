package org.rtd.quotes.connection;

import org.rtd.quotes.variables.Variables;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class NetworkChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, final Intent intent) {

		int status = NetworkUtil.getConnectivityStatus(context);

		Variables.getInstance().Initialize(context);

		if (status==0) {
			Variables.getInstance().setVariable("OFFLINE_MODE", "");

		} else {
			Variables.getInstance().setVariable("OFFLINE_MODE", "1");

		}
	}

}
