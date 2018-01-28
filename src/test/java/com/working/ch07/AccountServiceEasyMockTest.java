package com.working.ch07;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountServiceEasyMockTest {

	private AccountManager mockAccountManager;

	@Before
	public void setUp() {
		mockAccountManager = EasyMock.createMock("mockAccountManager", AccountManager.class);
	}

	@Test
	public void testTransferOK() {
		Account sender = new Account("A001", 200);
		Account beneficiary = new Account("A002", 100);

		mockAccountManager.updateAccount(sender);
		mockAccountManager.updateAccount(beneficiary);

		EasyMock.expect(mockAccountManager.findAccountForUser("A001")).andReturn(sender);
		EasyMock.expect(mockAccountManager.findAccountForUser("A002")).andReturn(beneficiary);

		// We're done defining the expectations
		EasyMock.replay(mockAccountManager);

		AccountService service = new AccountService();
		service.setAccountManager(mockAccountManager);
		service.transfer("A001", "A002", 50);

		assertEquals(150, sender.getBalance());
		assertEquals(150, beneficiary.getBalance());

	}

	@After
	public void tearDown() {
		EasyMock.verify(mockAccountManager);
	}
}
