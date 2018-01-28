package com.working.ch07.wrong;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class MockHttpURLConnection extends HttpURLConnection {

	private boolean isInput = Boolean.TRUE;
	private InputStream inputStream;

	protected MockHttpURLConnection(URL url) {
		super(url);
	}

	protected MockHttpURLConnection() {
		super(null);
	}

	public void setExpectedInputStream(InputStream pInputStream) {
		this.inputStream = pInputStream;
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
		return inputStream;
	}
}
