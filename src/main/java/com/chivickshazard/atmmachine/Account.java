package com.chivickshazard.atmmachine;

public class Account {
    private int id;
    private String accountName;
    private int accountNumber;
    private String balance;
    private String pin;
    private String accountType;

    public Account(int id, String accountName, int accountNumber, String accountType) {
        this.id = id;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAccountName() {
        return accountName;
    }
    
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
    
    @Override
    public String toString() {
        return "Account Name: " + getAccountName() + "\n" +
        "Account Number: " + getAccountNumber() + "\n" +
        "Account Type: " + getAccountType();
    }
}
