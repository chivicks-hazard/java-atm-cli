package com.chivickshazard.atmmachine.account;

public class AccountService {
    public static void withdrawCash(Account account, double amount) {
        if (account.getBalance() > amount) {
            double tempAmount = account.getBalance() - amount;
            boolean isTransactionSuccessful = AccountDAO.changeBalance(account.getId(), account.getAccountType(), tempAmount);

            if(isTransactionSuccessful) {
                System.out.println("Transaction Successful!");
                account.setBalance(tempAmount);
                System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
                account.stringifyAccount();
            } else {
                System.out.println("Transaction Failed!");
            }

        } else {
            System.out.println("Insufficient funds.");
        }
    }
}
