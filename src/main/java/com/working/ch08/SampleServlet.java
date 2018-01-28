package com.working.ch08;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SampleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public boolean isAuthenticated(HttpServletRequest pRequest) {
		HttpSession session = pRequest.getSession(false);
		if (null == session) {
			return false;
		}
		String authenticated = (String) session.getAttribute("authenticated");

		return Boolean.valueOf(authenticated);
	}
}
