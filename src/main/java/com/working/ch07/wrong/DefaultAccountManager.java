package com.working.ch07.wrong;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.working.ch07.Account;
import com.working.ch07.AccountManager;

public class DefaultAccountManager implements AccountManager {

	private static final Logger LOGGER = LogManager.getLogger(DefaultAccountManager.class);

	@Override
	public Account findAccountForUser(String userId) {
		LOGGER.debug("Getting account for user: {}", userId);
		ResourceBundle bundle = PropertyResourceBundle.getBundle("MyLabels");
		String sql = bundle.getString("FIND_ACCOUNT_FOR_USER");
		System.out.println(sql);
		return null;
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub

	}

	public static void main(String args[]) {
		DefaultAccountManager accountManager = new DefaultAccountManager();
		accountManager.findAccountForUser("test");
	}

}
