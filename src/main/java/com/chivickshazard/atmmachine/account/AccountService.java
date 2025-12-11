package com.chivickshazard.atmmachine.account;

public class AccountService {
    // Withdraw Cash
    public static void withdrawCash(Account account, double amount) {
        if (account.getBalance() > amount) {
            double newBalance = account.getBalance() - amount;
            boolean isTransactionSuccessful = AccountDAO.changeBalance(account.getId(), account.getAccountType(), newBalance);

            if(isTransactionSuccessful) {
                System.out.println("Transaction Successful!");
                account.setBalance(newBalance);
                System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
                account.stringifyAccount();
            } else {
                System.out.println("Transaction Failed!");
            }

        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public static void debitAccount(Account account, double amount) {
        if (account.getBalance() > amount) {
            double newBalance = account.getBalance() - amount;
            boolean isTransactionSuccessful = AccountDAO.changeBalance(account.getId(), account.getAccountType(), newBalance);

            if(isTransactionSuccessful) {
                System.out.println("Transaction Successful!");
                account.setBalance(newBalance);
                System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
                account.stringifyAccount();
            } else {
                System.out.println("Transaction Failed!");
            }
        }  else {
            System.out.println("Insufficient funds.");
        }
    }

    public static void creditAccount(Account account, double amount) {
        double newBalance = account.getBalance() + amount;
            boolean isTransactionSuccessful = AccountDAO.changeBalance(account.getId(), account.getAccountType(), newBalance);

            if(isTransactionSuccessful) {
                System.out.println("Transaction Successful!");
                account.setBalance(newBalance);
                System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
                account.stringifyAccount();
            } else {
                System.out.println("Transaction Failed!");
            }
    }

    // Transfer Cash
    public static void transferCash(Account sender, Account receiver, double amount) {
        if (sender.getBalance() > amount) {
            double newSenderBalance = sender.getBalance() - amount;
            double newReceiverBalance = receiver.getBalance() + amount;

            boolean isTransactionSuccessful = AccountDAO.makeTransfer(sender.getId(), receiver.getId(), sender.getAccountType(), receiver.getAccountType(), newSenderBalance, newReceiverBalance);

            if(isTransactionSuccessful) {
                System.out.println("Transaction Successful!");
                sender.setBalance(newSenderBalance);
                System.out.println("Withdrawal successful. New balance: $" + sender.getBalance());
                sender.stringifyAccount();
            } else {
                System.out.println("Transaction Failed!");
            }

        } else {
            System.out.println("Insufficient funds.");
        } 
    }
}
