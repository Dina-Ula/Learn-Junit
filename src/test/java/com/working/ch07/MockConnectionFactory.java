package com.working.ch07;

import java.io.InputStream;

public class MockConnectionFactory implements ConnectionFactory {
	private InputStream inputStream;

	public void setData(InputStream stream) {
		this.inputStream = stream;
	}

	public InputStream getData() throws Exception {
		return this.inputStream;
	}
}
