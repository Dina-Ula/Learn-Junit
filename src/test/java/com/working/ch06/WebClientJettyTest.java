package com.working.ch06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.util.ByteArrayISO8859Writer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.net.HttpHeaders;

/**
 * @author dulaga
 *
 */
public class WebClientJettyTest {

	@BeforeClass
	public static void setUp() throws Exception {
		Server server = new Server(8080);

		WebClientJettyTest testObj = new WebClientJettyTest();

		ContextHandler contentFoundContext = new ContextHandler(server, "/testGetContentOK");
		contentFoundContext.setHandler(testObj.new TestGetContentOK());

		ContextHandler contentNotFoundContext = new ContextHandler(server, "/testGetContentNotFound");
		contentNotFoundContext.setHandler(testObj.new TestGetContentNotFoundHandler());

		HandlerCollection handlers = new HandlerCollection();
		handlers.addHandler(contentFoundContext);
		handlers.addHandler(contentNotFoundContext);

		server.setHandler(handlers);

		// Start the server
		server.start();
	}

	@Test
	public void testGetContentOK() throws Exception {
		WebClient client = new WebClient();
		String result = client.getContent(new URL("http://localhost:8080/testGetContentOK"));
		assertEquals("It Works", result);
	}

	@Test
	public void testGetContentNotFound() throws Exception {
		WebClient client = new WebClient();
		String result = client.getContent(new URL("http://localhost:8080/testGetContentNotFound"));
		assertNull(result);
	}

	@AfterClass
	public static void tearDown() {

	}

	private class TestGetContentOK extends AbstractHandler {

		@Override
		public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
			OutputStream out = response.getOutputStream();

			ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer();
			writer.write("It Works");
			writer.flush();

			response.setIntHeader(HttpHeaders.CONTENT_LENGTH, writer.size());

			writer.writeTo(out);
			out.flush();
		}

	}

	private class TestGetContentNotFoundHandler extends AbstractHandler {

		@Override
		public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);

		}
	}
}
