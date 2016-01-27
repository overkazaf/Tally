package com.tally.mode;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

//���е���������ʧ�����ⶼ����onFailure��ʵ��
public class TJsonHttpResponseHandler extends JsonHttpResponseHandler {
	
	@Override
	public void onFailure(int statusCode, Header[] headers,
			String responseString, Throwable throwable) {
		// TODO Auto-generated method stub
		super.onFailure(statusCode, headers, responseString, throwable);
		showNewFailue();
	}
	
	@Override
	public void onFailure(int statusCode, Header[] headers,
			Throwable throwable, JSONArray errorResponse) {
		// TODO Auto-generated method stub
		super.onFailure(statusCode, headers, throwable, errorResponse);
		showNewFailue();
	}
	
	
	@Override
	public void onFailure(int statusCode, Header[] headers,
			Throwable throwable, JSONObject errorResponse) {
		// TODO Auto-generated method stub
		super.onFailure(statusCode, headers, throwable, errorResponse);
		showNewFailue();
	}
	
	//���ֻ���Ļ����ʾ��������ʧ�ܴ���
	private void showNewFailue()
	{
		
	}
}
