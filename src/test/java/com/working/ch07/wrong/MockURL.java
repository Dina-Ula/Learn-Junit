package com.working.ch07.wrong;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class cannot be extends as the URL class in JDK is a final class
 * 
 * @author dulaga
 *
 */
@SuppressWarnings("unused")
public class MockURL /* extends URL */ {
	// TODO - Mock the class java.net.URL
	
	public MockURL() {
		// TODO Auto-generated constructor stub
	}

	public void setupOpenConnection(HttpURLConnection connection) {
		// TODO - Setup open connection
	}
}
