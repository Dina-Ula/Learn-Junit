package com.working.ch07;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

public class AccountServiceJMockTest {

	private Mockery context = new JUnit4Mockery();
	private AccountManager accountManager;

	@Before
	public void setUp() {
		accountManager = context.mock(AccountManager.class);
	}

	@Test
	public void testTransferOK() {
		final Account sender = new Account("A001", 200);
		final Account beneficiary = new Account("A002", 100);

		/*
		 * Expectations expectations = new Expectations();
		 * expectations.oneOf(accountManager).findAccountForUser("A001");
		 * expectations.will(Expectations.returnValue(sender));
		 * expectations.oneOf(accountManager).findAccountForUser("A002");
		 * expectations.will(Expectations.returnValue(beneficiary));
		 * expectations.oneOf(accountManager).updateAccount(sender);
		 * expectations.oneOf(accountManager).updateAccount(beneficiary);
		 * context.checking(expectations);
		 */

		// The code below is same as the one above. (WOW!)
		context.checking(new Expectations() {
			{
				oneOf(accountManager).findAccountForUser("A001");
				will(returnValue(sender));

				oneOf(accountManager).findAccountForUser("A002");
				will(returnValue(beneficiary));

				oneOf(accountManager).updateAccount(sender);

				oneOf(accountManager).updateAccount(beneficiary);
			}
		});

		AccountService service = new AccountService();
		service.setAccountManager(accountManager);
		service.transfer("A001", "A002", 50);

		assertEquals(150, sender.getBalance());
		assertEquals(150, beneficiary.getBalance());
	}

	@Before
	public void tearDown() {

	}
}
