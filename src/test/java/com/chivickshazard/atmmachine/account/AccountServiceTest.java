package com.chivickshazard.atmmachine.account;

import org.junit.jupiter.api.Test;

public class AccountServiceTest {
    // Test case 1: Successful withdrawal
    Account account1 = new Account(1, 1000.00, AccountType.SAVINGS.toString());

    // Test case 2: Insufficient funds
    // Account account2 = new Account(2, 500.00, AccountType.CURRENT.toString());

    // Test case 3: Withdrawal of exact balance
    Account account3 = new Account(3, 300.00, AccountType.SAVINGS.toString());

    // Test case 4: Withdrawal of zero amount (should still check balance)
    Account account4 = new Account(4, 200.00, AccountType.CURRENT.toString());

    // Test case 5: Withdrawal of negative amount (should be handled
    Account account5 = new Account(5, 100.00, AccountType.SAVINGS.toString());
    
    @Test
    public void testCaseWithdrawCashOne() {
        assert(account1.getBalance() == 1000.00);
        AccountService.withdrawCash(account1, 500.00);
        assert(account1.getBalance() == 500.00);
    }

    /* @Test
    public void testCaseWithdrawCashTwo() {
        assert(account2.getBalance() == 500.00);
        AccountService.withdrawCash(account2, 1000.00);
        assert(account2.getBalance() == 500.00);
    } */
}
