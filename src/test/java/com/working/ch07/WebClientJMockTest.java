package com.working.ch07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

public class WebClientJMockTest {
	private Mockery context = new JUnit4Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	@Test
	public void testGetContentOK() throws Exception {
		final ConnectionFactory connectionFactory = context.mock(ConnectionFactory.class);
		final InputStream inputStream = context.mock(InputStream.class);

		context.checking(new Expectations() {
			{
				oneOf(connectionFactory).getData();
				will(returnValue(inputStream));

				atLeast(1).of(inputStream).read();
				will(onConsecutiveCalls(returnValue(new Integer((byte) 'W')), returnValue(new Integer((byte) 'o')),
						returnValue(new Integer((byte) 'r')), returnValue(new Integer((byte) 'k')),
						returnValue(new Integer((byte) 's')), returnValue(new Integer((byte) '!')), returnValue(-1)));

				oneOf(inputStream).close();
			}
		});

		WebClient client = new WebClient();
		String result = client.getContent(connectionFactory);

		assertEquals("Works!", result);
	}

	@Test
	public void testGetConnectOK_CloseInputStream() throws Exception {
		final ConnectionFactory connectionFactory = context.mock(ConnectionFactory.class);
		final InputStream inputStream = context.mock(InputStream.class);

		context.checking(new Expectations() {
			{
				oneOf(connectionFactory).getData();
				will(returnValue(inputStream));

				oneOf(inputStream).read();
				will(returnValue(-1));

				oneOf(inputStream).close();
				will(throwException(new IOException("Cannot close")));
			}
		});

		WebClient client = new WebClient();
		String result = client.getContent(connectionFactory);

		assertNull(result);
	}
}
