package com.working.ch07;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class WebClientTest {

	@Test
	public void testGetContentOK() throws Exception {
		MockConnectionFactory connectionFactory = new MockConnectionFactory();
		connectionFactory.setData(new ByteArrayInputStream(new String("It Works.").getBytes()));

		WebClient client = new WebClient();
		String result = client.getContent(connectionFactory);

		assertEquals("It Works.", result);
	}

	@Test
	public void testGetContentOK_CloseCount() throws Exception {
		MockConnectionFactory connectionFactory = new MockConnectionFactory();

		MockInputStream mockStream = new MockInputStream();
		mockStream.setBuffer("It Works.");

		connectionFactory.setData(mockStream);

		WebClient client = new WebClient();
		String result = client.getContent(connectionFactory);

		assertEquals("It Works.", result);
		mockStream.verify();
	}
}
