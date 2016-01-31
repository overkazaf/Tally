package com.tally.activity;

import com.example.tally.R;
import com.tally.mode.BaseView;
import com.tally.views.DetailView;
import com.tally.views.HistoryView;
import com.tally.views.SettingView;
import com.tally.views.SlidingMenu;
import com.tally.views.StatisticView;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.LinearLayout;




public class MainActivity extends Activity {
	private static SlidingMenu mLeftMenu ;
	private static LinearLayout wrap;
	private static DetailView detailView;
	private static HistoryView historyView;
	private static SettingView settingView;
	private static StatisticView statisticView;
	private static BaseView currentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.activity_main);
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
        wrap = (LinearLayout)findViewById(R.id.id_content);
        detailView = new DetailView(wrap.getContext(), null);
        historyView = new HistoryView(wrap.getContext(), null);
        settingView = new SettingView(wrap.getContext(), null);
        statisticView = new StatisticView(wrap.getContext(), null);
        //初始化menu的点击事件
        LinearLayout wrapper = (LinearLayout)findViewById(R.id.id_menuwrapper);
        for (int i = 0; i < wrapper.getChildCount(); i++) {
			wrapper.getChildAt(i).setOnTouchListener(new LeftMenuTouchListener());
		}
        
        super.onCreate(savedInstanceState);
        
    }
    
    public void toggleMenu(View view)
	{
		mLeftMenu.toggle();
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    class LeftMenuTouchListener implements OnTouchListener{
    	
    	private void refreshView(LinearLayout wrap, BaseView view)
    	{
    		view.RefreshView();
    		wrap.addView(view);
    	}
    	
    	@Override
    	public boolean onTouch(View view, MotionEvent action) {
    		// TODO Auto-generated method stub
    		
    		int atc = action.getAction();
    		switch (atc) {
    		case MotionEvent.ACTION_DOWN:
    			view.setBackgroundColor(Color.WHITE); //点击背景色变白色
    			break;
    		case MotionEvent.ACTION_UP:
    			view.setBackgroundResource(0); //清空背景色
    			
    			String tag = view.getTag().toString();
    			
    			wrap.removeAllViews();
    			currentView = null;
    			if(tag.equals("detail"))
    			{
    				currentView = detailView;
    			}
    			if(tag.equals("history"))
    			{
    				currentView = historyView;
				}
    			if(tag.equals("statistic"))
    			{
    				currentView = statisticView;
    			}
    			if(tag.equals("setting"))
    			{
    				currentView = settingView;
    			}
    			refreshView(wrap, currentView);
    			mLeftMenu.toggle();
    			return false;
    			
    		default:
    			break;
    		}
    		return true;
    	}
    }
    
    public static void freshView()
    {
    	if(currentView != null){
    		currentView.RefreshView();
    	}
    }
}