package com.chivickshazard.atmmachine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.chivickshazard.atmmachine.account.Account;
import com.chivickshazard.atmmachine.account.AccountDAO;
import com.chivickshazard.atmmachine.account.AccountType;
import com.chivickshazard.atmmachine.customer.Customer;
import com.chivickshazard.atmmachine.customer.CustomerDAD;
import com.chivickshazard.atmmachine.utils.DbHelper;

public class Main {
    public static void main(String[] args) {
        try {    
            Scanner scanner = new Scanner(System.in);
            String command;

            System.out.println("Welcome to the ATM Machine :)");
            System.out.println("Select an service");
            System.out.println("1. Withdraw Cash");
            System.out.println("2. Transfer Cash");
            System.out.println("3. Recharge Airtime/Cash");
            System.out.println("4. Pay Bills");
            System.out.println("5. Print All Accounts");
            // System.out.println("6. Create Account");
            System.out.print("Pick one: ");

            command = scanner.nextLine();

            while (!command.equalsIgnoreCase("No")) {
                switch (command) {
                    case "1": // Withdraw Cash
                        double amount;
                        System.out.println();
                        System.err.println("WITHDRAW CASH");
                        System.out.print("Enter your PIN: ");
                        int pin = scanner.nextInt();
                        scanner.nextLine();
                        String query = "SELECT * FROM customers WHERE pin = ?";

                        try (Connection conn = DbHelper.getConnection()) {
                            PreparedStatement stmt = conn.prepareStatement(query);

                            stmt.setInt(1, pin);

                            ResultSet rs = stmt.executeQuery();

                            if(rs.next()) {
                                Customer customer = new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("phone"), rs.getString("email"), rs.getString("pin"));
                                query = "UPDATE customers SET pin = ? WHERE pin = ?";
                                System.out.println();
                                System.out.println("You have successfully logged in!\n");
                                System.out.println("Select an account to withdraw from: ");
                                System.out.println("1. Savings Account");
                                System.out.println("2. Current Account");
                                System.out.print("Pick one: ");
                                command = scanner.nextLine();
                                if (command.equals("1")) {
                                    query = "SELECT * FROM accounts WHERE customerId = ? AND accountType = ?";
                                    stmt = conn.prepareStatement(query);
                                    stmt.setInt(1, rs.getInt("id"));
                                    stmt.setString(2, AccountType.SAVINGS.toString());
                                    rs = stmt.executeQuery();

                                    if (rs.next()) {
                                        System.out.println();
                                        System.out.println("Select the amount you want to withdraw: ");
                                        System.out.println("1. $5000");
                                        System.out.println("2. $10000");
                                        System.out.println("3. $20000");
                                        System.out.println("4. Enter a custom amount");
                                        System.out.print("Pick one: ");
                                        amount = scanner.nextInt();
                                        scanner.nextLine();

                                        if (command.equals("1")) {
                                            amount = 10000.00;
                                            System.out.println("Are you sure you want to withdraw $" + amount + "?");
                                            System.out.println("Yes");
                                            System.out.println("No");
                                            System.out.print("Select an option: ");
                                            command = scanner.nextLine(); 
                                        }
                                    } else {
                                        // Customer customer = new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("phone"), rs.getString("email"), rs.getString("pin"));
                                        System.out.println();
                                        System.out.println("You don't have a bank account.\nDo you want to open a bank account?");
                                        System.out.println("Yes");
                                        System.out.println("No");
                                        System.out.print("Select an option: ");
                                        command = scanner.nextLine();

                                        if (command.equalsIgnoreCase("Yes")) {
                                            System.out.println();
                                            System.out.println("CREATE NEW ACCOUNT");
                                            Account newAccount = new Account();
                                            System.out.println("Enter The Type of Account:");
                                            System.out.println("1. Savings Account");
                                            System.out.println("2. Current Account");
                                            System.out.print("Pick one: ");
                                            command = scanner.nextLine();

                                            if (command.equals("1")) {
                                                newAccount.setAccountType(AccountType.SAVINGS.toString());
                                            } else if (command.equals("2")) {
                                                newAccount.setAccountType(AccountType.CURRENT.toString());
                                            }

                                            System.out.println("\nDeposit any amount.\nMust not be less than $5000");
                                            System.out.print("Enter Amount: ");
                                            amount = scanner.nextDouble();
                                            newAccount.setBalance(amount);
                                            scanner.nextLine();
                                            System.out.println("\n" + newAccount.stringifyAccount(customer) + "\n");
                                            System.out.println("Do you want to create this account?");
                                            System.out.println("Yes");
                                            System.out.println("No");
                                            System.out.print("Select an option: ");
                                            command = scanner.nextLine();

                                            AccountDAO.createAccount(customer.getCustomerId(), newAccount.getAccountType(), amount);
                                        }
                                    }
                                }
                            } else {
                                System.out.println();
                                System.out.println("You don't have an account.\nDo you want to open an account?");
                                System.out.println("Yes");
                                System.out.println("No");
                                System.out.print("Select an option: ");
                                command = scanner.nextLine();

                                if (command.equalsIgnoreCase("Yes")) {
                                    System.out.println();
                                    System.out.println("CREATE NEW ACCOUNT");
                                    Customer newCustomer = new Customer();

                                    System.out.println("Enter The Necessary Details: ");
                                    System.out.print("First Name: ");
                                    newCustomer.setFirstName(scanner.nextLine());
                                    System.out.print("Last Name: ");
                                    newCustomer.setLastName(scanner.nextLine());
                                    System.out.print("Phone Number: ");
                                    newCustomer.setPhone(scanner.nextLine());
                                    System.out.print("Email Address: ");
                                    newCustomer.setEmail(scanner.nextLine());
                                    System.out.print("Set PIN: ");
                                    newCustomer.setPin(scanner.nextLine());

                                    System.out.println();
                                    System.out.println("Account Preview: ");
                                    System.out.println(newCustomer.toString());
                                    System.out.println("");
                                    System.out.println("Are you sure you want to create a new account?");
                                    System.out.println("Yes");
                                    System.out.println("No");
                                    System.out.print("Select One: ");
                                    command = scanner.nextLine();

                                    CustomerDAD.createCustomer(newCustomer);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;

                    default:
                        System.out.println("Omo ");
                        break;                                       
                }

                System.out.println("Do you still want to use the ATM machine? Type 'no' to exit.");
                System.out.println("Yes");
                System.out.println("No");
                System.out.print("Pick one: ");
                command = scanner.nextLine();
                if (!command.equalsIgnoreCase("no")) {
                    System.out.println("Select an service");
                    System.out.println("1. Withdraw Cash");
                    System.out.println("2. Transfer Cash");
                    System.out.println("3. Recharge Airtime/Cash");
                    System.out.println("4. Pay Bills");
                    System.out.println("5. Print All Accounts");
                    System.out.print("Pick one: ");
                    command = scanner.nextLine();
                }
            }

            
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Thank you :)");
    }
}