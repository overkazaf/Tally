package com.example.tally;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class LoginActivity extends Activity {
	//SlidingMenu slidingMenu;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_login);
		  TextView tView = (TextView)findViewById(R.id.login);
		  tView.setText("panfei");
		  //slidingMenu = new SlidingMenu(this);
		  //slidingMenu.setMode(SlidingMenu.LEFT);
		  //slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		  
		  //slidingMenu.setMenu(R.layout.slidingmenu);
		  //slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		
	}
}
