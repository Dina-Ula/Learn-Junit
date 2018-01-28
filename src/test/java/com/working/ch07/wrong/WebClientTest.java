package com.working.ch07.wrong;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.net.URL;

import org.junit.Ignore;
import org.junit.Test;

import com.working.ch07.wrong.WebClient;

/**
 * Failure Mock Test
 * 
 * @author dulaga
 *
 */
public class WebClientTest {

	@Test
	@Ignore
	public void testGetContentOK_Failure() {
		// The URL cannot be mocked as the JDK class java.net.URL is final.
		MockURL mockURL = new MockURL();

		MockHttpURLConnection connection = new MockHttpURLConnection();
		mockURL.setupOpenConnection(connection);

		WebClient client = new WebClient();
		String results = null;
		/* client.getContent(mockURL); */
		assertEquals("It Works.", results);
	}

	@Test
	public void testGetContentOK() throws Exception {
		MockHttpURLConnection connection = new MockHttpURLConnection();
		connection.setExpectedInputStream(new ByteArrayInputStream(new String("It Works.").getBytes()));

		TestableWebClient client = new TestableWebClient();
		client.setHttpURLConnection(connection);

		String result = client.getContent(new URL("http://localhost"));

		assertEquals("It Works.", result);
	}
}
