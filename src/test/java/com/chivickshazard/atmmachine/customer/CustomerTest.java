package com.chivickshazard.atmmachine.customer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomerTest {
    Customer customer = new Customer();

    @Test
    void getName() {
        customer.setFirstName("John");
        customer.setLastName("Doe");

        assertEquals("John Doe", customer.getName());
    }

    @Test
    void groupedAssertion() {
        customer.setFirstName("John");
        customer.setLastName("Doe");

        assertAll("Customer", 
            () -> assertEquals("John", customer.getFirstName()),
            () -> assertEquals("Doe", customer.getLastName()));
    }
}
