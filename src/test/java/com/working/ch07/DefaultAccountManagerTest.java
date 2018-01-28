package com.working.ch07;

import static org.junit.Assert.assertNull;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class DefaultAccountManagerTest {

	@Test
	public void testFindAccountForUser() {
		Logger logger = LogManager.getLogger(DefaultAccountManager.class);
		ResourceBundle resourceBundle = PropertyResourceBundle.getBundle("MyLabels");

		DefaultAccountManager accountManager = new DefaultAccountManager(logger, resourceBundle);
		Account account = accountManager.findAccountForUser("A001");

		assertNull(account);
	}
}
