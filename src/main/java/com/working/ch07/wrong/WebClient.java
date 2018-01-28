package com.working.ch07.wrong;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient {
	public String getContent(URL url) {
		StringBuffer content = new StringBuffer();
		try {

			HttpURLConnection connection = createHttpURLConnection(url);
			connection.setDoInput(true);
			InputStream inStream = connection.getInputStream();
			int count;
			while (-1 != (count = inStream.read())) {
				content.append(new String(Character.toChars(count)));
			}

		} catch (IOException e) {
			return null;
		}
		return content.toString();
	}

	protected HttpURLConnection createHttpURLConnection(URL url) throws IOException {
		return (HttpURLConnection) url.openConnection();
	}
}
