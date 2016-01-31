package com.tally.views;


import java.util.ArrayList;

import com.example.tally.R;
import com.loopj.android.http.RequestParams;
import com.tally.entity.ConsumptionEntity;
import com.tally.helper.TallyHttpClient;
import com.tally.mode.BaseView;
import com.tally.mode.ConsumType;
import com.tally.mode.TJsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import android.content.Context;
import android.graphics.drawable.Drawable;
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
	public static PopupWindow pw;
	public static ArrayList<ConsumptionEntity> costlist;
	public static Drawable backDrawable;
	public DetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		detailView = (RelativeLayout) inflate(context, R.layout.activity_detail, null);
		addView(detailView);
		
		costlist = new ArrayList<ConsumptionEntity>(20);
		popView = new DetailEditView(context, null);
		pw = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		backDrawable = getResources().getDrawable(R.drawable.menuback);
		ImageButton addbtn = (ImageButton)findViewById(R.id.addbtn);
		
		addbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 点击添加按钮响应

				DetailEditView.submitButton.setText("添加");
				showPopWind();
			}
		});
	}
	
	public void RefreshView()
	{
		costlist.clear();
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
						String id = response.getJSONObject(i).getString("Id");
						String cost = response.getJSONObject(i).getString("Cost");
						String name = response.getJSONObject(i).getString("ConsumName");
						String location = response.getJSONObject(i).getString("Location");
					    String costType = response.getJSONObject(i).getString("ConsumType");
					    int t = ConsumType.getBackSource(costType);
					    detailItemView.costTypeImageView.setBackgroundResource(ConsumType.getBackSource(costType));
					    detailItemView.costNameTextView.setText(name);
					    detailItemView.costTextView.setText(cost);
					    detailItemView.findViewById(R.id.morebtn).setTag(i);
					    costlist.add(new ConsumptionEntity(id,name,cost,costType,location));
					    itemsView.addView(detailItemView);
					}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				
			}
		});
	
	}
	
	/**
	 * 消失detailView的PopupWindow
	 */
	public static void dismissPopWind(){
		pw.dismiss();
	}
	
	public static void showPopWind(){
		pw.setTouchable(true);
		pw.setBackgroundDrawable(backDrawable);
		pw.showAtLocation(detailView, Gravity.CENTER, 0, 0);
	}
}
