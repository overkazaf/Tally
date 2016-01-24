package com.tally.activity;

import com.example.tally.R;
import com.litesuits.http.LiteHttp;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.request.StringRequest;

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
import com.litesuits.http.response.Response;

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
				// String name = et_name.getText().toString().trim();
				// String passwd = et_pssd.getText().toString().trim();

				try {

					//ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
					//NetworkInfo info = cm.getActiveNetworkInfo();
					
					LiteHttp liteHttp = LiteHttp.newApacheHttpClient(null);
					liteHttp.executeAsync(new StringRequest("http://localhost:8080/tally/login?username=panfei&passwd=123456").setHttpListener(new HttpListener<String>(false) {
			            @Override
			            public void onSuccess(String s,  Response<String> response) {
			                // 成功：主线程回调，反馈一个string
			                response.printInfo();
			            }
			            @Override
			            public void onFailure(HttpException e, Response<String> response) {
			                // 失败：主线程回调，反馈异常
			                response.printInfo();
			            }
			        }));
					
					// HttpSendHelper.SendQuery("http://localhost:8080/tally/login?username=panfei&passwd=123456");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}

				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				LoginActivity.this.finish();
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
