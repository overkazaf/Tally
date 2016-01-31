package com.tally.views;



import com.example.tally.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tally.activity.MainActivity;
import com.tally.helper.TallyHttpClient;

import cz.msebera.android.httpclient.Header;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class DetailEditView extends LinearLayout {

	private static LinearLayout detailEditView;
	public static EditText consumeNameEditText;
	public static EditText consumeLocationEditText;
	public static EditText consumeCostEditText;
	public static Spinner consumeTypeSpinner;
	public static Button submitButton;

	public DetailEditView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		detailEditView = (LinearLayout) inflate(context, R.layout.detail_edit,
				null);
		addView(detailEditView);
		consumeCostEditText = (EditText) findViewById(R.id.edit_cscost);
		consumeNameEditText = (EditText) findViewById(R.id.edit_csname);
		consumeLocationEditText = (EditText) findViewById(R.id.edit_loc);
		consumeTypeSpinner = (Spinner) findViewById(R.id.spinner2);
		submitButton = (Button) findViewById(R.id.submitbtn);
		
		submitButton.setOnClickListener(new OnClickListener() {
			private void addConsume(RequestParams params){
				
				TallyHttpClient.get("addconsume", params,
						new JsonHttpResponseHandler() {

							@Override
							public void onSuccess(int statusCode,
									Header[] headers,
									org.json.JSONObject response) {
								// TODO Auto-generated method stub
								try {
									if (statusCode == 200
											&& response != null
											&& response.get("status").equals("success"))
										Toast.makeText(getContext(), "插入成功",Toast.LENGTH_LONG).show();
									DetailView.dismissPopWind();
									MainActivity.freshView();
								} catch (Exception ex) {
									ex.printStackTrace();
								}
								super.onSuccess(statusCode, headers, response);
							}
						});
			}
			
			private void updateConsume(RequestParams params){
				String Id = DetailView.costlist.get(DetailItemView.currentItem).getId();
				params.add("Id", Id);
				TallyHttpClient.get("updateconsume", params,
						new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode,
							Header[] headers, org.json.JSONObject response) {
						// TODO Auto-generated method stub
						try {
							if (statusCode == 200
									&& response != null
									&& response.get("status").equals("success"))
								Toast.makeText(getContext(), "更新成功",Toast.LENGTH_LONG).show();
							DetailView.dismissPopWind();
							MainActivity.freshView();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						super.onSuccess(statusCode, headers, response);
					}
				});
			}
			@Override
			public void onClick(View view) {
				String userID = "0001";
				String consumeName = consumeNameEditText.getText().toString();
				String consumeCost = consumeCostEditText.getText().toString();
				String consumeLoc = consumeLocationEditText.getText()
						.toString();
				String consumeType = consumeTypeSpinner.getSelectedItem()
						.toString();
				RequestParams params = new RequestParams();
				params.add("userID", userID);
				params.add("consumeType", consumeType);
				params.add("consumeName", consumeName);
				params.add("cost", consumeCost);
				params.add("location", consumeLoc);
				if(DetailEditView.submitButton.getText().equals("添加"))addConsume(params);
				else updateConsume(params);
			}
		});
	}
}
