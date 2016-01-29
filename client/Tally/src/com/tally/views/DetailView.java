package com.tally.views;


import com.example.tally.R;
import com.loopj.android.http.RequestParams;
import com.tally.helper.TallyHttpClient;
import com.tally.mode.BaseView;
import com.tally.mode.TJsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class DetailView extends BaseView {
	public static DetailEditView popView;
	public static RelativeLayout detailView;
	public DetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		detailView = (RelativeLayout) inflate(context, R.layout.activity_detail, null);
		addView(detailView);
		
		popView = new DetailEditView(context, null);
		ImageButton addbtn = (ImageButton)findViewById(R.id.addbtn);
		addbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 点击添加按钮响应
				PopupWindow pw = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
				pw.setTouchable(true);
				pw.setBackgroundDrawable(getResources().getDrawable(
		                R.drawable.menuback));
				pw.showAtLocation(detailView, Gravity.CENTER, 0, 0);
			}
		});
	}
	
	public void RefreshView()
	{
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
						DetailItemView detailItemView = new DetailItemView(getContext(), null);
						String location = response.getJSONObject(i).getString("Location");
						String cost = response.getJSONObject(i).getString("Cost");
						String name = response.getJSONObject(i).getString("ConsumName");
					    detailItemView.costNameTextView.setText(name);
					    detailItemView.costTextView.setText(cost);
					    itemsView.addView(detailItemView);
						
					}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				
			}
		});
	
	}
}
