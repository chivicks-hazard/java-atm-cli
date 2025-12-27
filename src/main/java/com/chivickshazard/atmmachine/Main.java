package com.chivickshazard.atmmachine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.chivickshazard.atmmachine.account.Account;
import com.chivickshazard.atmmachine.account.AccountDAO;
import com.chivickshazard.atmmachine.account.AccountService;
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
            System.out.println("3. Recharge Airtime/Data");
            System.out.println("4. Pay Bills");
            System.out.println("5. Print All Accounts");
            // System.out.println("6. Create Account");
            System.out.print("Pick one: ");

            command = scanner.nextLine();

            while (!command.equalsIgnoreCase("No")) {
                double amount;
                String pin;
                String query;
                PreparedStatement stmt;
                Customer customer;
                Account account;
                
                switch (command) {
                    case "1": // Withdraw Cash
                        System.out.println();
                        System.out.println("WITHDRAW CASH");
                        System.out.print("Enter your PIN: ");
                        pin = scanner.nextLine();
                        query = "SELECT * FROM customers WHERE pin = ?";

                        try (Connection conn = DbHelper.getConnection()) {
                            stmt = conn.prepareStatement(query);

                            stmt.setString(1, pin);

                            ResultSet rs = stmt.executeQuery();

                            if(rs.next()) {
                                customer = new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("phone"), rs.getString("email"), rs.getString("pin"));
                                System.out.println();
                                System.out.println("You have successfully logged in!\n");

                                // Account Preview
                                System.out.println("Account Preview: ");
                                System.out.println(customer.toString());

                                System.out.println();
                                System.out.println("Select an account to withdraw from: ");
                                System.out.println("1. Savings Account");
                                System.out.println("2. Current Account");
                                System.out.print("Pick one: ");
                                command = scanner.nextLine();

                                if (command.equals("1")) {
                                    query = "SELECT * FROM accounts WHERE customerId = ? AND accountType = ?";
                                    stmt = conn.prepareStatement(query);
                                    stmt.setInt(1, customer.getCustomerId());
                                    stmt.setString(2, AccountType.SAVINGS.toString());
                                    rs = stmt.executeQuery();

                                    if (rs.next()) {
                                        account = new Account(rs.getDouble("balance"),  rs.getString("accountType"), customer);
                                        System.out.println();

                                        // Account Preview
                                        System.out.println("Account Preview: ");
                                        System.out.println(account.stringifyAccount());

                                        System.out.println();
                                        System.out.println("Select the amount you want to withdraw: ");
                                        System.out.println("1. $5000");
                                        System.out.println("2. $10000");
                                        System.out.println("3. $20000");
                                        System.out.println("4. Enter a custom amount");
                                        System.out.print("Pick one: ");
                                        command = scanner.nextLine();

                                        switch (command) {
                                            case "1":
                                                amount = 5000.00;
                                                System.out.println("Are you sure you want to withdraw $" + amount + "?");
                                                System.out.println("Yes");
                                                System.out.println("No");
                                                System.out.print("Select an option: ");
                                                command = scanner.nextLine();

                                                if (command.equalsIgnoreCase("yes")) {
                                                    AccountService.withdrawCash(account, amount);
                                                }
                                                break;
                                        
                                            case "2":
                                                amount = 10000.00;
                                                System.out.println("Are you sure you want to withdraw $" + amount + "?");
                                                System.out.println("Yes");
                                                System.out.println("No");
                                                System.out.print("Select an option: ");
                                                command = scanner.nextLine();

                                                if (command.equalsIgnoreCase("yes")) {
                                                    AccountService.withdrawCash(account, amount);
                                                }
                                                break;

                                            case "3":
                                                amount = 20000.00;
                                                System.out.println("Are you sure you want to withdraw $" + amount + "?");
                                                System.out.println("Yes");
                                                System.out.println("No");
                                                System.out.print("Select an option: ");
                                                command = scanner.nextLine();

                                                if (command.equalsIgnoreCase("yes")) {
                                                    AccountService.withdrawCash(account, amount);
                                                }
                                                break;

                                            case "4":
                                                System.out.print("Enter the amount you want to withdraw: ");
                                                amount = scanner.nextDouble();
                                                scanner.nextLine();
                                                System.out.println("Are you sure you want to withdraw $" + amount + "?");
                                                System.out.println("Yes");
                                                System.out.println("No");
                                                System.out.print("Select an option: ");
                                                command = scanner.nextLine();

                                                if (command.equalsIgnoreCase("yes")) {
                                                    AccountService.withdrawCash(account, amount);
                                                }
                                                break;

                                            default:
                                                break;
                                        }
                                    } else {// Create New Bank Account if account does not exist
                                        System.out.println();
                                        System.out.println("You don't have a bank account.\nDo you want to open a bank account?");
                                        System.out.println("Yes");
                                        System.out.println("No");
                                        System.out.print("Select an option: ");
                                        command = scanner.nextLine();

                                        // Create New Bank Account
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
                            } else { // Create New Customer Account if account does not exist
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
                            // Log error and continue - connection is automatically closed by try-with-resources
                            System.err.println("Error during withdrawal: " + e.getMessage());
                            e.printStackTrace();
                        }

                        break;

                    case "2": // Transfer Cash
                        System.out.println();
                        System.out.println("TRANSFER CASH");
                        System.out.print("Enter your PIN: ");
                        pin = scanner.nextLine();
                        query = "SELECT * FROM customers WHERE pin = ?";

                        try (Connection conn = DbHelper.getConnection()) {
                            stmt = conn.prepareStatement(query);

                            stmt.setString(1, pin);

                            ResultSet rs = stmt.executeQuery();

                            if(rs.next()) {
                                customer = new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("phone"), rs.getString("email"), rs.getString("pin"));
                                System.out.println();
                                System.out.println("You have successfully logged in!\n");

                                // Account Preview
                                System.out.println("Account Preview: ");
                                System.out.println(customer.toString());

                                System.out.println();
                                System.out.println("Select an account to withdraw from: ");
                                System.out.println("1. Savings Account");
                                System.out.println("2. Current Account");
                                System.out.print("Pick one: ");
                                command = scanner.nextLine();

                                if (command.equals("1")) {
                                    query = "SELECT * FROM accounts WHERE customerId = ? AND accountType = ?";
                                    stmt = conn.prepareStatement(query);
                                    stmt.setInt(1, customer.getCustomerId());
                                    stmt.setString(2, AccountType.SAVINGS.toString());
                                    rs = stmt.executeQuery();

                                    if (rs.next()) {
                                        String recipientPhoneNumber;
                                        Customer recipient;
                                        account = new Account(rs.getDouble("balance"),  rs.getString("accountType"), customer);
                                        System.out.println();

                                        // Account Preview
                                        System.out.println("Account Preview: ");
                                        System.out.println(account.stringifyAccount());

                                        System.out.print("\nEnter the phone number of the recipient: ");
                                        recipientPhoneNumber = scanner.nextLine();
                                        query = "SELECT * FROM customers WHERE phone = ?";
                                        stmt = conn.prepareStatement(query);
                                        stmt.setString(1, recipientPhoneNumber);

                                        rs = stmt.executeQuery();

                                        if (rs.next()) {
                                            recipient = new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("phone"), rs.getString("email"), rs.getString("pin"));
                                            query = "SELECT * FROM accounts WHERE customerId = ?";

                                            stmt = conn.prepareStatement(query);
                                            stmt.setInt(1, recipient.getCustomerId());

                                            rs = stmt.executeQuery();
                                            
                                            if (rs.next()) {
                                                System.out.println();

                                                Account recipientAccount = new Account(rs.getDouble("balance"),  rs.getString("accountType"), recipient);

                                                // Preview Recipient Account
                                                System.out.println("Account Preview: ");
                                                System.out.println(recipient.toString());

                                                System.out.print("\nEnter the amount you want to transfer: ");
                                                amount = scanner.nextDouble();
                                                scanner.nextLine();

                                                System.out.println("Are you sure you want to transfer $" + amount + " to \n" + recipient.toString() + "?");
                                                System.out.println("Yes");
                                                System.out.println("No");
                                                System.out.print("Select an option: ");
                                                command = scanner.nextLine();

                                                if (command.equalsIgnoreCase("yes")) {
                                                    // AccountService.transferCash(account, recipientAccount, amount);
                                                    AccountService.debitAccount(account, amount);
                                                    AccountService.creditAccount(recipientAccount, amount);
                                                }
                                            } else {
                                                System.out.println("This account does not exist.");
                                            }
                                        } else {
                                            System.out.println("This account does not exist.");
                                        }

                                    }
                                }
                            }

                        } catch (Exception e) {
                            // Log error and continue - connection is automatically closed by try-with-resources
                            System.err.println("Error during transfer: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;

                    case "3": // Recharge Airtime/Data
                        System.out.println();
                        System.out.println("RECHARGE AIRTIME/DATA");
                        System.out.print("Enter your PIN: ");
                        pin = scanner.nextLine();
                        query = "SELECT * FROM customers WHERE pin = ?";

                        try (Connection conn = DbHelper.getConnection()) {
                            stmt = conn.prepareStatement(query);

                            stmt.setString(1, pin);

                            ResultSet rs = stmt.executeQuery();

                            if(rs.next()) {
                                customer = new Customer(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("phone"), rs.getString("email"), rs.getString("pin"));
                                System.out.println();
                                System.out.println("You have successfully logged in!\n");

                                // Account Preview
                                System.out.println("Account Preview: ");
                                System.out.println(customer.toString());

                                System.out.println();
                                System.out.println("Select an account to recharge from: ");
                                System.out.println("1. Savings Account");
                                System.out.println("2. Current Account");
                                System.out.print("Pick one: ");
                                command = scanner.nextLine();

                                if (command.equals("1")) {
                                    query = "SELECT * FROM accounts WHERE customerId = ? AND accountType = ?";
                                    stmt = conn.prepareStatement(query);
                                    stmt.setInt(1, customer.getCustomerId());
                                    stmt.setString(2, AccountType.SAVINGS.toString());
                                    rs = stmt.executeQuery();

                                    if (rs.next()) {
                                        account = new Account(rs.getDouble("balance"),  rs.getString("accountType"), customer);
                                        System.out.println();

                                        // Account Preview
                                        System.out.println("Account Preview: ");
                                        System.out.println(account.stringifyAccount());

                                        System.out.println("Do you want to recharge airtime or data?");
                                        System.out.println();
                                        System.out.println("1. AIRTIME");
                                        System.out.println("2. DATA");
                                        System.out.print("Select your choice: ");
                                        command = scanner.nextLine();

                                        if (command.equals("1")) {
                                            AccountService.rechargeAirtime(account);
                                        } else if (command.equals("2")) {
                                            AccountService.rechargeData(account);
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            // Log error and continue - connection is automatically closed by try-with-resources
                            System.err.println("Error during recharge: " + e.getMessage());
                            e.printStackTrace();
                        }
                            
                        break;

                    default:
                        System.out.println("Invalid option selected. Please choose a valid service.");
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
                    System.out.println("3. Recharge Airtime/Data");
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