package com.working.ch07;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.working.ch07.Account;
import com.working.ch07.AccountManager;

public class DefaultAccountManager implements AccountManager {

	private Logger logger;
	private ResourceBundle resourceBundle;

	public DefaultAccountManager() {
		this(LogManager.getLogger(DefaultAccountManager.class), PropertyResourceBundle.getBundle("MyLabels"));
	}

	public DefaultAccountManager(Logger logger, ResourceBundle resourceBundle) {
		this.logger = logger;
		this.resourceBundle = resourceBundle;
	}

	@Override
	public Account findAccountForUser(String userId) {
		logger.debug("Getting account for user: {}", userId);
		logger.debug("Query to find the user: {}", resourceBundle.getString("FIND_ACCOUNT_FOR_USER"));
		// TODO - Add logic to lookup database
		return null;
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub

	}

}
