package com.chivickshazard.atmmachine.customer;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String pin;

    public Customer(int customerId, String firstName, String lastName, String phone, String email, String pin) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.pin = pin;
    }

    public Customer(String firstName, String lastName, String phone, String email, String pin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.pin = pin;
    }

    public Customer() {}

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Customer Name: " + getFirstName() + " " + getLastName() + "\n" +
        "Customer Phone Number: " + getPhone() + "\n" +
        "Customer Email: " + getEmail();
    }
}