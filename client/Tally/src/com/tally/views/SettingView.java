package com.tally.views;


import com.example.tally.R;
import com.tally.mode.BaseView;

import android.content.Context;
import android.util.AttributeSet;

public class SettingView extends BaseView {

	public SettingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		addView(inflate(context, R.layout.activity_setting, null));
	}
	
	public void RefreshView()
	{
		
	}
}
