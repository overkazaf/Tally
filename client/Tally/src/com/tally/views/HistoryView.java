package com.tally.views;

import com.example.tally.R;
import com.tally.mode.BaseView;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;



public class HistoryView extends BaseView {
	public static ListView historyList = null;
	private int number = 20; // ÿ�λ�ȡ����������  
	private int maxpage = 5; // �ܹ��ж���ҳ  
	private boolean loadfinish = true; // ָʾ�����Ƿ������� 
	private LinearLayout footer = null;
	private ArrayAdapter<String> adapter; 
	public HistoryView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		addView(inflate(context, R.layout.activity_history, null));
		historyList = (ListView)findViewById(R.id.history_list);
		footer = (LinearLayout)findViewById(R.layout.listfooter);
		//adapter = new ArrayAdapter<String>(context, resource)
		historyList.setOnScrollListener(new ScrollListener());
		
	}
	
	public void RefreshView()
	{
		
	}
	
	final class ScrollListener implements OnScrollListener
	{

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
			final int loadtotal = totalItemCount;
			int lastItemid = historyList.getLastVisiblePosition(); // ��ȡ��ǰ��Ļ���Item��ID  
			if ((lastItemid + 1) == totalItemCount) { // �ﵽ���ݵ����һ����¼  
				if (totalItemCount > 0) {  
					// ��ǰҳ  
                    int currentpage = totalItemCount % number == 0 ? totalItemCount : totalItemCount / number + 1;
                    int nextpage = currentpage + 1; // ��һҳ  
                    if (nextpage <= maxpage && loadfinish) {
                    	loadfinish = false;
                    	historyList.addFooterView(footer);
                    	new Thread(new Runnable() {
							
							@Override
							public void run() {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									
								}
								
							}
						}).start();
                    }
                    
				}
			}
		}

		@Override
		public void onScrollStateChanged(AbsListView arg0, int arg1) {
			
		}
		
	}
}
