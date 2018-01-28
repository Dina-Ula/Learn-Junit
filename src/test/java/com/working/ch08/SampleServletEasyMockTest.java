package com.working.ch08;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SampleServletEasyMockTest {

	private SampleServlet servlet;
	private HttpServletRequest request;
	private HttpSession session;

	@Before
	public void setUp() {
		servlet = new SampleServlet();
		request = EasyMock.createStrictMock(HttpServletRequest.class);
		session = EasyMock.createStrictMock(HttpSession.class);
	}

	@Test
	public void testIsAuthenticated_Authenticated() {
		EasyMock.expect(request.getSession(EasyMock.eq(false))).andReturn(session);
		EasyMock.expect(session.getAttribute(EasyMock.eq("authenticated"))).andReturn("true");

		EasyMock.replay(request);
		EasyMock.replay(session);

		assertTrue(servlet.isAuthenticated(request));
	}

	@Test
	public void testIsAuthenticated_NotAuthenticated() {
		EasyMock.expect(request.getSession(EasyMock.eq(false))).andReturn(session);
		EasyMock.expect(session.getAttribute(EasyMock.eq("authenticated"))).andReturn("false");

		EasyMock.replay(request);
		EasyMock.replay(session);

		assertFalse(servlet.isAuthenticated(request));
	}

	@Test
	public void testIsNotAuthenticated_NoSession() {
		EasyMock.expect(request.getSession(EasyMock.eq(false))).andReturn(null);
		EasyMock.replay(request);
		EasyMock.replay(session);

		assertFalse(servlet.isAuthenticated(request));
	}

	@After
	public void tearDown() {
		EasyMock.verify(request);
		EasyMock.verify(session);
	}
}
