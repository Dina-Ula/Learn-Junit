package com.working.ch07;

public interface AccountManager {
	Account findAccountForUser(String userId);

	void updateAccount(Account account);
}
