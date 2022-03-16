package br.com.lab510.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.GsonBuilder;

public class ProtocolHttp {
	
	private static HttpClient httpClient;

	public static HttpResponse doPost (Object obj, String url) throws ClientProtocolException, IOException {

		HttpPost configReq = new HttpPost(url);

		httpClient = HttpClientBuilder.create().build();
		configReq.addHeader("Content-Type", "application/json");
		configReq.setEntity(new StringEntity(new GsonBuilder().create().toJson(obj)));

		return httpClient.execute(configReq);
	}
	
	public static HttpResponse doGet (String url) {
		
		HttpGet configReq = null;
		HttpResponse response = null;
		
		try {
			configReq = new HttpGet(url);
			httpClient = HttpClientBuilder.create().build();
			configReq.addHeader("Content-Type", "application/json");
			response = httpClient.execute(configReq);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}

	public static String getResponse(HttpResponse response) throws UnsupportedOperationException, IOException {

		StringBuilder contentResponse = new StringBuilder();

		HttpEntity configResponse = response.getEntity();
		InputStream is = configResponse.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		String line;
		while ((line = reader.readLine()) != null) {
			contentResponse.append(line);
		}

		return contentResponse.toString();

	}
}
