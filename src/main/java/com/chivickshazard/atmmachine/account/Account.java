package com.chivickshazard.atmmachine.account;

import com.chivickshazard.atmmachine.customer.Customer;

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

    public void setBalance(double balance) {
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
    
    public String stringifyAccount() {
        return "Customer Name: " + customer.getFirstName() + " " + customer.getLastName() + "\n" +
        "Customer Phone Number: " + customer.getPhone() + "\n" +
        "Customer Email: " + customer.getEmail() + "\n" +
        "Account Type: " + accountType + "\n" +
        "Account Balance: " + balance;
    }
}
