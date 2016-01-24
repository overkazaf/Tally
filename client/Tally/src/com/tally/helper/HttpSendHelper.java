package com.tally.helper;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpSendHelper {
	private static HttpGet httpGet;
	private static DefaultHttpClient httpClient;
	static
	{
		httpGet = new HttpGet();
	}
	
	public static HttpResponse SendQuery() throws ClientProtocolException, IOException
	{
		HttpResponse response = httpClient.execute(httpGet);
		
		return response;
	}
}
