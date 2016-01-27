package com.tally.views;

import org.json.JSONObject;

import com.example.tally.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tally.helper.TallyHttpClient;
import com.tally.mode.BaseView;
import com.tally.mode.TJsonHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

import android.R.drawable;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DetailView extends BaseView {

	public DetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		
		
		addView(inflate(context, R.layout.activity_detail, null));
	}
	
	public void RefreshView()
	{
		//localhost:8080/tally/todaydetail?userID=0001
		RequestParams params = new RequestParams();
		params.add("userID", "0001");
		TallyHttpClient.get("todaydetail", params, new TJsonHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, org.json.JSONArray response) 
			{
				LinearLayout itemsView = (LinearLayout)findViewById(R.id.items);
				itemsView.removeAllViews();
				try
				{
					for(int i = 0;i<response.length();i++)
					{
						String location = response.getJSONObject(i).getString("Location");
						String cost = response.getJSONObject(i).getString("Cost");
						String name = response.getJSONObject(i).getString("ConsumName");
					    View item = inflate(getContext(), R.layout.detail_item, null);
					    TextView costtv = (TextView)item.findViewById(R.id.cost_text);
					    TextView costnametv = (TextView)item.findViewById(R.id.id_cosumname);
					    costtv.setText(cost);
					    costnametv.setText(name);
					    itemsView.addView(item);
						
					}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				
			}
		});
	
	}
}
