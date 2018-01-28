package com.working.ch07.wrong;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.working.ch07.wrong.WebClient;

class TestableWebClient extends WebClient {
	private HttpURLConnection connection;

	public void setHttpURLConnection(HttpURLConnection connection) {
		this.connection = connection;
	}

	public HttpURLConnection createHttpURLConnection(URL url) throws IOException {
		return this.connection;
	}
}
