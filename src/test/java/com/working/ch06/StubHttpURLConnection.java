package com.working.ch06;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class StubHttpURLConnection extends HttpURLConnection {

	private boolean isInput = Boolean.TRUE;

	protected StubHttpURLConnection(URL url) {
		super(url);
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean usingProxy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void connect() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public InputStream getInputStream() throws IOException {
		if (!isInput) {
			throw new ProtocolException("Cannot read from URLConnection if doInput=false (call setDoInput(true))");
		}

		ByteArrayInputStream inputStream = new ByteArrayInputStream(new String("It Works").getBytes());

		return inputStream;
	}

}
