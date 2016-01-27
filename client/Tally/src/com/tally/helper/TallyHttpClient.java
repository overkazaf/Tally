package com.tally.helper;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class TallyHttpClient {
	private static final String BASE_ULR_STRING = "http://10.0.2.2:8080/tally/%s";
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	public static void get(String url,RequestParams params,AsyncHttpResponseHandler responseHandler){
		client.get(getAbsolutUrl(url),params, responseHandler);
	}
	
	public static void post(String url,RequestParams params,AsyncHttpResponseHandler responseHandler){
		client.post(getAbsolutUrl(url),params, responseHandler);
	}
	
	private static String getAbsolutUrl(String url)
	{
		return String.format(BASE_ULR_STRING, url);
	}
}
