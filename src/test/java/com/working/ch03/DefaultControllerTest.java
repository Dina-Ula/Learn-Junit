package com.working.ch03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class DefaultControllerTest {

	private Request request;
	private RequestHandler handler;
	private DefaultController controller;

	private class SampleRequest implements Request {

		private static final String DEFAULT_NAME = "Test";

		private String mName;

		public SampleRequest() {
			mName = DEFAULT_NAME;
		}

		public SampleRequest(String pName) {
			mName = pName;
		}

		@Override
		public String getName() {
			return mName;
		}

	}

	private class SampleResponse implements Response {

		private static final String NAME = "Test";

		public String getName() {
			return NAME;
		}

		public boolean equals(Object pObject) {
			boolean result = false;
			if (pObject instanceof SampleResponse) {
				return ((SampleResponse) pObject).getName().equals(getName());
			}
			return result;
		}

		public int hashCode() {
			return NAME.hashCode();
		}

	}

	private class SampleHandler implements RequestHandler {

		@Override
		public Response process(Request request) throws Exception {
			return new SampleResponse();
		}

	}

	private class SampleExceptionHandler implements RequestHandler {

		@Override
		public Response process(Request request) throws Exception {
			throw new Exception("Error processing request.");
		}

	}

	@Before
	public void instantiate() {
		controller = new DefaultController();

		request = new SampleRequest();
		handler = new SampleHandler();

		controller.addHandler(request, handler);
	}

	@Test
	public void testAddHandler() {
		assertSame("The handler we set should be same as the handler we get.", controller.getHandler(request), handler);
	}

	@Test
	public void testProcessRequest() {
		Response response = controller.processRequest(request);

		assertNotNull("The response should not be null.", response);
		assertEquals("The response we set should be same as the handler we get.", new SampleResponse(), response);
	}

	@Test
	public void testProcessRequestAnswersErrorResponse() {

		Request request = new SampleRequest("testError");
		RequestHandler handler = new SampleExceptionHandler();

		controller.addHandler(request, handler);

		Response response = controller.processRequest(request);

		assertNotNull("The response should not be null.", response);
		assertEquals("The response we set should be same as the handler we get.", ErrorResponse.class,
				response.getClass());
	}

	@Test(expected = RuntimeException.class)
	public void testGetHandlerNotDefined() {
		Request request = new SampleRequest("testNotDefined");

		// The following line is supposed to throw an RuntimeException
		controller.getHandler(request);
	}

	@Test(expected = RuntimeException.class)
	public void testAddRequestDuplicateNames() {
		Request request = new SampleRequest();
		RequestHandler handler = new SampleHandler();

		// The following line is supposed to throw an RuntimeException
		controller.addHandler(request, handler);
	}

	@Test(timeout = 40)
	@Ignore(value = "Ignore until we find a decent time-limit")
	public void testProcessMultipleRequestsTimeout() {

		Request request;
		Response response = new SampleResponse();
		RequestHandler handler = new SampleHandler();

		for (int i = 0; i < 99999; i++) {
			request = new SampleRequest(String.valueOf(i));
			controller.addHandler(request, handler);
			response = controller.processRequest(request);
		}

		assertNotNull("The response should not be null.", response);
		assertEquals("The response we set should be same as the handler we get.", SampleResponse.class,
				response.getClass());
	}
}
