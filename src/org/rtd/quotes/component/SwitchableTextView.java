package org.rtd.quotes.component;

import org.rtd.quotes.gui.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class SwitchableTextView extends TextView {

    public SwitchableTextView(Context context) {
	super(context);
	// TODO Auto-generated constructor stub
    }

    public SwitchableTextView(Context context, AttributeSet attrs) {
	super(context, attrs);
	setCustomFont(context, attrs);
    }

    public SwitchableTextView(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
	setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
	TypedArray a = ctx.obtainStyledAttributes(attrs,
		R.styleable.SwitchableTextView);
	String customFont = a
		.getString(R.styleable.SwitchableTextView_customFont);
	if (customFont == null) {
	    setCustomFont(ctx, "SegoeWP-Semilight.ttf");
	} else {
	    setCustomFont(ctx, customFont);
	}
	a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
	Typeface tf = null;
	try {
	    tf = Typeface.createFromAsset(ctx.getAssets(), asset);
	} catch (Exception e) {
	    Log.d("TextView", "Could not get typeface: " + e.getMessage());
	    return false;
	}

	setTypeface(tf);
	return true;
    }

}
