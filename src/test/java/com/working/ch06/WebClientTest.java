package com.working.ch06;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class WebClientTest {

	@BeforeClass
	public static void setUp() {
		WebClientTest testObj = new WebClientTest();
		URL.setURLStreamHandlerFactory(testObj.new StubStreamHandlerFactory());
	}

	private class StubStreamHandlerFactory implements URLStreamHandlerFactory {
		@Override
		public URLStreamHandler createURLStreamHandler(String protocol) {
			return new StubHttpURLStreamHandler();
		}
	}

	private class StubHttpURLStreamHandler extends URLStreamHandler {
		@Override
		protected URLConnection openConnection(URL url) throws IOException {
			return new StubHttpURLConnection(url);
		}
	}

	@Test
	public void testGetContentOk() throws Exception {
		WebClient client = new WebClient();
		String result = client.getContent(new URL("http://localhost"));
		assertEquals("It Works", result);
	}
}
