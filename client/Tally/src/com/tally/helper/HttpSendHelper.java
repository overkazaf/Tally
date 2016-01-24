package com.tally.helper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpSendHelper {
	private static HttpGet httpGet;
	private static DefaultHttpClient httpClient;
	static
	{
		httpGet = new HttpGet();
		httpClient = new DefaultHttpClient();
	}
	
	public static HttpResponse SendQuery(String Url) throws ClientProtocolException, IOException, URISyntaxException
	{
		
		httpGet.setURI(new URI(Url));
		HttpResponse response = httpClient.execute(httpGet);
		System.out.println(response.getEntity());
		return response;
	}
}
