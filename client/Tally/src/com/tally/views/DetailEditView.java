package com.tally.views;

import org.json.JSONArray;

import com.example.tally.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
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
	private static EditText consumeNameEditText;
	private static EditText consumeLocationEditText;
	private static EditText consumeCostEditText;
	private static Spinner consumeTypeSpinner;

	public DetailEditView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		detailEditView = (LinearLayout) inflate(context, R.layout.detail_edit,
				null);
		addView(detailEditView);
		consumeCostEditText = (EditText) findViewById(R.id.edit_cscost);
		consumeNameEditText = (EditText) findViewById(R.id.edit_csname);
		consumeLocationEditText = (EditText) findViewById(R.id.edit_loc);
		Button submitBtnButton = (Button) findViewById(R.id.submitbtn);
		submitBtnButton.setOnClickListener(new OnClickListener() {

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
										Toast.makeText(getContext(), "≤Â»Î≥…π¶",Toast.LENGTH_SHORT).show();
								} catch (Exception ex) {
									ex.printStackTrace();
								}
								super.onSuccess(statusCode, headers, response);
							}
						});
			}
		});
	}
}
