package org.rtd.quotes.component;

import org.rtd.quotes.gui.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class AlertDialogExt extends Dialog {

    public AlertDialogExt(Context context) {
	super(context);

	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.alert_dialog);
	getWindow().setBackgroundDrawable(
		new ColorDrawable(android.graphics.Color.TRANSPARENT));

	setCancelable(false);

	TextView text1 = (TextView) findViewById(R.id.TextView01);
	text1.setText(R.string.str_War);

    }

    @Override
    public void setTitle(CharSequence title) {
	super.setTitle(title);
	TextView text1 = (TextView) findViewById(R.id.TextView01);
	text1.setText(title);
    }

    public void setMessage(String message) {
	TextView text2 = (TextView) findViewById(R.id.TextView02);
	text2.setText(message);
    }

    public void setButton1(String text, View.OnClickListener onClickListener) {
	Button button = (Button) findViewById(R.id.Button01);
	button.setText(text);
	button.setTextColor(Color.BLACK);
	button.setOnClickListener(onClickListener);
    }

    public void setButton2(String text, View.OnClickListener onClickListener) {

	Button button = (Button) findViewById(R.id.Button02);
	button.setVisibility(View.VISIBLE);
	button.setText(text);
	button.setTextColor(Color.BLACK);
	button.setOnClickListener(onClickListener);
    }
}
