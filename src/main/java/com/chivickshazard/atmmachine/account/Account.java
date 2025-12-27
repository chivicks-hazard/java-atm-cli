package com.chivickshazard.atmmachine.account;

import com.chivickshazard.atmmachine.customer.Customer;

/**
 * Represents a bank account with balance, type, and customer information.
 * Solution: Added null checks to prevent NullPointerException and balance validation.
 */
public class Account {
    private int id;
    private double balance;
    private String accountType;
    private Customer customer;

    public Account(int id, double balance, String accountType) {
        this.id = id;
        this.balance = balance;
        this.accountType = accountType;
    }
    
    public Account(double balance, String accountType, Customer customer) {
        this.balance = balance;
        this.accountType = accountType;
        this.customer = customer;
        this.id = customer.getCustomerId();
    }

    public Account() {}
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Sets the account balance.
     * Solution: Added validation to prevent negative balances.
     * @param balance The balance to set (must be non-negative)
     */
    public void setBalance(double balance) {
        // Solution: Validate balance to prevent negative values
        if (balance < 0) {
            throw new IllegalArgumentException("Account balance cannot be negative");
        }
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public String stringifyAccount(Customer customer) {
        return "Customer Name: " + customer.getFirstName() + " " + customer.getLastName() + "\n" +
        "Customer Phone Number: " + customer.getPhone() + "\n" +
        "Customer Email: " + customer.getEmail() + "\n" +
        "Account Type: " + accountType + "\n" +
        "Account Balance: " + balance;
    }
    
    /**
     * Returns a string representation of the account.
     * Includes null check to prevent NullPointerException if customer is not set.
     * @return String representation of the account
     */
    public String stringifyAccount() {
        // Solution: Added null check to prevent NullPointerException
        if (customer == null) {
            return "Account Type: " + accountType + "\n" +
                   "Account Balance: " + balance + "\n" +
                   "Customer: Not assigned";
        }
        return "Customer Name: " + customer.getFirstName() + " " + customer.getLastName() + "\n" +
               "Customer Phone Number: " + customer.getPhone() + "\n" +
               "Customer Email: " + customer.getEmail() + "\n" +
               "Account Type: " + accountType + "\n" +
               "Account Balance: " + balance;
    }
}
