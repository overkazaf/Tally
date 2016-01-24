package com.tally.views;

import com.example.tally.R;
import com.tally.mode.BaseView;

import android.R.drawable;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class DetailView extends BaseView {

	public DetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		
		
		addView(inflate(context, R.layout.activity_detail, null));
	}
	
	public void RefreshView()
	{
		
	}
}
