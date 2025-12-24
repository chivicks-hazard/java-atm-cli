package com.chivickshazard.atmmachine.account;

import java.util.Scanner;

public class AccountService {
    private static Scanner scanner = new Scanner(System.in);
    private static String command = "";

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

    public static void rechargeAirtime(Account account) {
        int amount;

        System.out.println();
        System.out.println("Pick the recharge amount: ");
        System.out.println("1. 200");
        System.out.println("2. 500");
        System.out.println("3. 1000");
        System.out.println("4. 2000");
        System.out.print("Select one: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                amount = 200;

                System.out.println("Are you sure you want to recharge $" + amount + " to " + account.getCustomer().getPhone() + "?");
                System.out.println("Yes");
                System.out.println("No");
                System.out.print("Select an option: ");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("yes")) {
                    if (account.getBalance() > amount) {
                        double newBalance = account.getBalance() - amount;
                        boolean isTransactionSuccessful = AccountDAO.changeBalance(account.getId(), account.getAccountType(), newBalance);

                        if(isTransactionSuccessful) {
                            System.out.println("Transaction Successful!");
                            account.setBalance(newBalance);
                            System.out.println("Purchase successful. New balance: $" + account.getBalance());
                            account.stringifyAccount();
                        } else {
                            System.out.println("Transaction Failed!");
                        }

                    } else {
                        System.out.println("Insufficient funds.");
                    }
                }
                break;

            case 2:
                amount = 500;

                System.out.println("Are you sure you want to recharge $" + amount + " to " + account.getCustomer().getPhone() + "?");
                System.out.println("Yes");
                System.out.println("No");
                System.out.print("Select an option: ");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("yes")) {
                    if (account.getBalance() > amount) {
                        double newBalance = account.getBalance() - amount;
                        boolean isTransactionSuccessful = AccountDAO.changeBalance(account.getId(), account.getAccountType(), newBalance);

                        if(isTransactionSuccessful) {
                            System.out.println("Transaction Successful!");
                            account.setBalance(newBalance);
                            System.out.println("Purchase successful. New balance: $" + account.getBalance());
                            account.stringifyAccount();
                        } else {
                            System.out.println("Transaction Failed!");
                        }

                    } else {
                        System.out.println("Insufficient funds.");
                    }
                }
                break;

            case 3:
                amount = 1000;

                System.out.println("Are you sure you want to recharge $" + amount + " to " + account.getCustomer().getPhone() + "?");
                System.out.println("Yes");
                System.out.println("No");
                System.out.print("Select an option: ");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("yes")) {
                    if (account.getBalance() > amount) {
                        double newBalance = account.getBalance() - amount;
                        boolean isTransactionSuccessful = AccountDAO.changeBalance(account.getId(), account.getAccountType(), newBalance);

                        if(isTransactionSuccessful) {
                            System.out.println("Transaction Successful!");
                            account.setBalance(newBalance);
                            System.out.println("Purchase successful. New balance: $" + account.getBalance());
                            account.stringifyAccount();
                        } else {
                            System.out.println("Transaction Failed!");
                        }

                    } else {
                        System.out.println("Insufficient funds.");
                    }
                }
                break;

            case 4:
                amount = 2000;

                System.out.println("Are you sure you want to recharge $" + amount + " to " + account.getCustomer().getPhone() + "?");
                System.out.println("Yes");
                System.out.println("No");
                System.out.print("Select an option: ");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("yes")) {
                    if (account.getBalance() > amount) {
                        double newBalance = account.getBalance() - amount;
                        boolean isTransactionSuccessful = AccountDAO.changeBalance(account.getId(), account.getAccountType(), newBalance);

                        if(isTransactionSuccessful) {
                            System.out.println("Transaction Successful!");
                            account.setBalance(newBalance);
                            System.out.println("Purchase successful. New balance: $" + account.getBalance());
                            account.stringifyAccount();
                        } else {
                            System.out.println("Transaction Failed!");
                        }

                    } else {
                        System.out.println("Insufficient funds.");
                    }
                }
                break;
        
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void rechargeData(Account account) {
        int amount;
        String dataPlan;

        System.out.println();
        System.out.println("What kind of data plan do you want?");
        System.out.println("Daily");
        System.out.println("Weekly");
        System.out.println("Monthly");
        System.out.print("Select one: ");;
        String choice = scanner.nextLine();

        switch (choice.toLowerCase()) {
            case "daily":
                System.out.println("Select a data plan");
                System.out.println("1. 50MB -- $50");
                System.out.println("2. 100MB -- $100");
                System.out.println("3. 200MB -- $200");
                System.out.print("Select one: ");
                choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("50mb")) {
                    dataPlan = "50MB";
                    amount = 50;
                } else if (choice.equalsIgnoreCase("2") || choice.equalsIgnoreCase("100mb")) {
                    dataPlan = "100MB";
                    amount = 100;
                } else if (choice.equalsIgnoreCase("3") || choice.equalsIgnoreCase("200mb")) {
                    dataPlan = "200MB";
                    amount = 200;
                } else {
                    System.out.println("Invalid choice.");
                    return;
                }

                System.out.println("Are you sure you want to recharge " + dataPlan + " for $" + amount + " to " + account.getCustomer().getPhone() + "?");
                System.out.println("Yes");
                System.out.println("No");
                System.out.print("Select an option: ");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("yes")) {
                    if (account.getBalance() > amount) {
                        double newBalance = account.getBalance() - amount;
                        boolean isTransactionSuccessful = AccountDAO.changeBalance(account.getId(), account.getAccountType(), newBalance);

                        if(isTransactionSuccessful) {
                            System.out.println("Transaction Successful!");
                            account.setBalance(newBalance);
                            System.out.println("Purchase successful. New balance: $" + account.getBalance());
                            account.stringifyAccount();
                        } else {
                            System.out.println("Transaction Failed!");
                        }

                    } else {
                        System.out.println("Insufficient funds.");
                    }
                }
                break;

            case "weekly":
                System.out.println("Select a data plan");
                System.out.println("1. 1GB -- $500");
                System.out.println("2. 2.5GB -- $1000");
                System.out.println("3. 4GB -- $2000");
                System.out.print("Select one: ");
                choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("50mb")) {
                    dataPlan = "1GB";
                    amount = 500;
                } else if (choice.equalsIgnoreCase("2") || choice.equalsIgnoreCase("100mb")) {
                    dataPlan = "1.5GB";
                    amount = 1000;
                } else if (choice.equalsIgnoreCase("3") || choice.equalsIgnoreCase("200mb")) {
                    dataPlan = "4GB";
                    amount = 2000;
                } else {
                    System.out.println("Invalid choice.");
                    return;
                }

                System.out.println("Are you sure you want to recharge " + dataPlan + " for $" + amount + " to " + account.getCustomer().getPhone() + "?");
                System.out.println("Yes");
                System.out.println("No");
                System.out.print("Select an option: ");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("yes")) {
                    if (account.getBalance() > amount) {
                        double newBalance = account.getBalance() - amount;
                        boolean isTransactionSuccessful = AccountDAO.changeBalance(account.getId(), account.getAccountType(), newBalance);

                        if(isTransactionSuccessful) {
                            System.out.println("Transaction Successful!");
                            account.setBalance(newBalance);
                            System.out.println("Purchase successful. New balance: $" + account.getBalance());
                            account.stringifyAccount();
                        } else {
                            System.out.println("Transaction Failed!");
                        }

                    } else {
                        System.out.println("Insufficient funds.");
                    }
                }
                break;

            case "monthly":
                System.out.println("Select a data plan");
                System.out.println("1. 2GB -- $1000");
                System.out.println("2. 4.5GB -- $2000");
                System.out.println("3. 10GB -- $5000");
                System.out.print("Select one: ");
                choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("50mb")) {
                    dataPlan = "2GB";
                    amount = 1000;
                } else if (choice.equalsIgnoreCase("2") || choice.equalsIgnoreCase("100mb")) {
                    dataPlan = "4.5GB";
                    amount = 2000;
                } else if (choice.equalsIgnoreCase("3") || choice.equalsIgnoreCase("200mb")) {
                    dataPlan = "10GB";
                    amount = 5000;
                } else {
                    System.out.println("Invalid choice.");
                    return;
                }

                System.out.println("Are you sure you want to recharge " + dataPlan + " for $" + amount + " to " + account.getCustomer().getPhone() + "?");
                System.out.println("Yes");
                System.out.println("No");
                System.out.print("Select an option: ");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("yes")) {
                    if (account.getBalance() > amount) {
                        double newBalance = account.getBalance() - amount;
                        boolean isTransactionSuccessful = AccountDAO.changeBalance(account.getId(), account.getAccountType(), newBalance);

                        if(isTransactionSuccessful) {
                            System.out.println("Transaction Successful!");
                            account.setBalance(newBalance);
                            System.out.println("Purchase successful. New balance: $" + account.getBalance());
                            account.stringifyAccount();
                        } else {
                            System.out.println("Transaction Failed!");
                        }

                    } else {
                        System.out.println("Insufficient funds.");
                    }
                }
                break;

            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

}
