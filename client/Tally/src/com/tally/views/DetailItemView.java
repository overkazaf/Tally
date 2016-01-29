package com.tally.views;

import com.example.tally.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class DetailItemView extends LinearLayout {
	
	public TextView costNameTextView;
	public TextView costTextView;
	private static LinearLayout popView;
	public DetailItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		addView(inflate(context, R.layout.detail_item, null));
		costNameTextView = (TextView) findViewById(R.id.cost_text);
		costTextView = (TextView) findViewById(R.id.id_cosumname);
		popView = (LinearLayout) inflate(context, R.layout.detail_pop, null);
		ImageButton morebtn = (ImageButton)findViewById(R.id.morebtn);
	    morebtn.setOnClickListener(new OnClickListener() {
			/**
			 * 设置more按钮的点击事件
			 */
			@Override
			public void onClick(View view) {
				 showPopupWindow(view);
			}
	    });
	}
	private void showPopupWindow(View  view){
		PopupWindow pw = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		pw.setTouchable(true);
		
		
		pw.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.menuback));
		pw.showAsDropDown(view);
	}
}
