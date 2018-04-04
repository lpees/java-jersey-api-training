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

public class ProtocoloHttp {
	
	private static HttpClient httpClient;

	public static HttpResponse enviaPost (Object obj, String url) throws ClientProtocolException, IOException {

		HttpPost configReq = new HttpPost(url);

		httpClient = HttpClientBuilder.create().build();
		configReq.addHeader("Content-Type", "application/json");

		configReq.setEntity(new StringEntity(new GsonBuilder().create().toJson(obj)));

		return httpClient.execute(configReq);
	}
	
	public static HttpResponse enviaGet (String url) {
		HttpGet configReq = null;
		HttpResponse resposta = null;
		
		try {
			configReq = new HttpGet(url);

			httpClient = HttpClientBuilder.create().build();
			configReq.addHeader("Content-Type", "application/json");
			resposta = httpClient.execute(configReq);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return resposta;
	}

	public static String pegaResposta(HttpResponse resposta) throws UnsupportedOperationException, IOException {

		StringBuilder contResposta = new StringBuilder();

		HttpEntity configResposta = resposta.getEntity();
		InputStream is = configResposta.getContent();

		BufferedReader leitor = new BufferedReader(new InputStreamReader(is));
		String linhaAtual;

		while ((linhaAtual = leitor.readLine()) != null) {
			contResposta.append(linhaAtual);
		}

		return contResposta.toString();

	}
}
