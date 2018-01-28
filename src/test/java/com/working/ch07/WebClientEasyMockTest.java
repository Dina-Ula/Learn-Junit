package com.working.ch07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WebClientEasyMockTest {

	private ConnectionFactory connectionFactory;
	private InputStream inputStream;

	@Before
	public void setUp() {
		connectionFactory = EasyMock.createMock("connectionFactory", ConnectionFactory.class);
		inputStream = EasyMock.createMock("inputStream", InputStream.class);
	}

	@Test
	public void testGetContentOK() throws Exception {

		EasyMock.expect(connectionFactory.getData()).andReturn(inputStream);
		EasyMock.expect(inputStream.read()).andReturn(new Integer((byte) 'W'));
		EasyMock.expect(inputStream.read()).andReturn(new Integer((byte) 'o'));
		EasyMock.expect(inputStream.read()).andReturn(new Integer((byte) 'r'));
		EasyMock.expect(inputStream.read()).andReturn(new Integer((byte) 'k'));
		EasyMock.expect(inputStream.read()).andReturn(new Integer((byte) 's'));
		EasyMock.expect(inputStream.read()).andReturn(new Integer((byte) '!'));
		EasyMock.expect(inputStream.read()).andReturn(-1);

		inputStream.close();

		EasyMock.replay(connectionFactory);
		EasyMock.replay(inputStream);

		WebClient client = new WebClient();
		String result = client.getContent(connectionFactory);

		assertEquals("Works!", result);
	}

	@Test
	public void testGetConnectOK_CloseInputStream() throws Exception {
		EasyMock.expect(connectionFactory.getData()).andReturn(inputStream);
		EasyMock.expect(inputStream.read()).andReturn(-1);

		inputStream.close();

		EasyMock.expectLastCall().andThrow(new IOException());

		EasyMock.replay(connectionFactory);
		EasyMock.replay(inputStream);

		WebClient client = new WebClient();
		String result = client.getContent(connectionFactory);

		assertNull(result);
	}

	@After
	public void tearDown() {
		EasyMock.verify(connectionFactory);
		EasyMock.verify(inputStream);
	}
}
