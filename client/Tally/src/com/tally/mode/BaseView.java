package com.tally.mode;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;


public abstract class BaseView extends LinearLayout
{

	public BaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public abstract void RefreshView();
}
