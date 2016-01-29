package com.tally.activity;


import org.json.JSONException;

import com.example.tally.R;
import com.loopj.android.http.*;
import com.tally.helper.TallyHttpClient;
import com.tally.mode.TJsonHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	EditText et_name = null;
	EditText et_pssd = null;
	Button btn_login = null;
	private TextWatcher tw = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			String name = et_name.getText().toString().trim();
			String passswd = et_pssd.getText().toString().trim();
			if (!name.equals("") && !passswd.equals("")) {
				btn_login.setEnabled(true);
			} else {
				btn_login.setEnabled(false);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		btn_login = (Button) this.findViewById(R.id.btn_login);
		et_name = (EditText) findViewById(R.id.et_name);
		et_pssd = (EditText) findViewById(R.id.et_psw);
		et_pssd.addTextChangedListener(tw);

		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg) {
				String name = et_name.getText().toString().trim();
				String passwd = et_pssd.getText().toString().trim();

				try {
					RequestParams params = new RequestParams();
					params.add("username",name);
					params.add("passwd",passwd);
					TallyHttpClient.get("login", params, new TJsonHttpResponseHandler() {
						
						@Override
						public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject response) 
						{
							try {
								if(statusCode == 200 && response != null && response.get("status").equals("success")){
									//µÇÂ¼³É¹¦
									Intent intent = new Intent();
									intent.setClass(LoginActivity.this, MainActivity.class);
									startActivity(intent);
									LoginActivity.this.finish();
								}
								else 
								{
									//µÇÂ¼Ê§°Ü
									
								}
							} catch (JSONException e) {
								
							}
						};
						
					});
				} catch (Exception e) {
					
				}
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
