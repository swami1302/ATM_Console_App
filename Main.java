// package ATM_simple;

import java.util.*;

import javax.security.sasl.SaslException;

class ATM {
    HashMap<String, HashMap<String, Integer>> accounts = new HashMap<>();
    
    String CurrUser;
    

    ATM() {
        HashMap<String, Integer> account = new HashMap<>();
        account.put("Pin", 1234);
        account.put("Balance", 10000);
        accounts.put("Swami", account);
        CurrUser = null;
    }

    public void menu() {
        System.out.println("1. Login");
        System.out.println("2. Create Account");
        System.out.println("3. Logout");
        
    }

    public void Create_Account() {
        Scanner info = new Scanner(System.in);
        System.out.print("Enter Account holder name: ");
        String name = info.next();
        System.out.print("Enter Pin number: ");
        int pin = info.nextInt();
        if (!(accounts.containsKey(name)) || accounts.get(name).get("Pin") != pin) {
            HashMap<String, Integer> account = new HashMap<>();
            account.put("Pin", pin);
            account.put("Balance", 0);
            accounts.put(name, account);
            System.out.println("Account Created Successfully");
            System.out.println();
        } else {
            System.out.println("Account already exist, Kindly login");
            System.out.println();
        }
    }

    public boolean login() {
        Scanner info = new Scanner(System.in);
        System.out.print("Enter Account Holder Name: ");
        String name = info.next();
        System.out.print("Enter Pin: ");
        int pin = info.nextInt();
        if (accounts.containsKey(name) && accounts.get(name).get("Pin") == pin) {
            CurrUser = name;
            return true;
        }
        return false;
    }

    public void Deposit() {
        Scanner amt = new Scanner(System.in);
        System.out.print("Enter the deposit amount: ");
        int amount = amt.nextInt();
        if (amount <= 0) {
            System.out.println("Enter a vaild amount");
        } else {
            int CurrBalance = accounts.get(CurrUser).get("Balance");
            accounts.get(CurrUser).put("Balance", CurrBalance + amount);
            System.out.println("The amount has been deposited succesfully");
            System.out.println();
        }
    }

    public void Withdraw() {
        Scanner amt = new Scanner(System.in);
        System.out.print("Enter the withdraw amount: ");
        int amount = amt.nextInt();
        if (accounts.get(CurrUser).get("Balance") < amount) {
            System.out.println("Insufficent Balance");
            System.out.println();
        } else {
            int CurrBalance = accounts.get(CurrUser).get("Balance");
            accounts.get(CurrUser).put("Balance", CurrBalance - amount);
            System.out.println("The amount has been Withrdrawen succesfully");
            System.out.println();
        }
    }

    public void Balance() {
        System.out.println("The available balance of the user "+CurrUser+" is "+accounts.get(CurrUser).get("Balance"));
        System.out.println();
    }



    public void Login_menu() {

        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }
}

public class Main {
    public static void main(String[] args) {
        ATM obj = new ATM();
        while (true) {
            obj.menu();
            Scanner option = new Scanner(System.in);
            int menu = option.nextInt();
            if (menu == 1) {
                if (obj.login()) {
                    System.out.println("Login Successfull");
                    System.out.println();
                    while (true) {
                        obj.Login_menu();
                        System.out.print("Enter the options to proceed further: ");
                        int choice = option.nextInt();
                        if (choice == 1) {
                            obj.Deposit();
                        } else if (choice == 2) {
                            obj.Withdraw();
                        } else if (choice == 3) {
                            obj.Balance();
                        } else if (choice == 4) {
                            break;
                        }

                    }
                } else {
                    System.out.println("Invalid credintials, Try again!!");
                    System.out.println();
                }
            } else if (menu == 2) {
                obj.Create_Account();
            } else if (menu == 3) {
                break;
            } else {
                System.out.println("Invalid Choice, Please Try again");
            }
        }
    }
}
