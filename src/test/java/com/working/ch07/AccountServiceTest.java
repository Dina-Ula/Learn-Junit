package com.working.ch07;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AccountServiceTest {

	@Test
	public void testTransferOK() {
		MockAccountManager accountManager = new MockAccountManager();

		Account sender = new Account("A001", 200);
		Account beneficiary = new Account("A002", 100);

		accountManager.addAccount("A001", sender);
		accountManager.addAccount("A002", beneficiary);

		AccountService accountService = new AccountService();
		accountService.setAccountManager(accountManager);
		accountService.transfer("A001", "A002", 50);

		assertEquals(150, sender.getBalance());
		assertEquals(150, beneficiary.getBalance());
	}
}
