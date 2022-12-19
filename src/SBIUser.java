import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Scanner;

public class SBIUser implements BankInterface{

    //attributes bank account should have

    private String AccountNo;
    public String name;
    private String balance;
    private String password;
    private static float rateOfInterest= 9.7f;
    private static HashMap<String,ArrayList<String>> map;

    public SBIUser(){}
    public SBIUser(String name, String balance, String password, HashMap<String,ArrayList<String>> map) {
        this.name = name;
        this.balance = balance;
        this.password= password;
        this.map= map;

        //UUID stands for Universally Unique Identifier. A UUID is 36 characters long unique number.
        //It is also known as a Globally Unique Identifier (GUID).
        //valueOf() method converts different types of values into string
        this.AccountNo= String.valueOf(UUID.randomUUID());

        ArrayList<String> curr= new ArrayList<>();
        curr.add(name);
        curr.add(password);
        curr.add(balance);
        map.put(AccountNo,curr);
    }

    Scanner sc= new Scanner(System.in);
    public static boolean checkAccountNo(String enteredAccountNo)
    {
        if(map.containsKey(enteredAccountNo))
            return true;
        return false;
    }

    public static boolean checkPassword(String accountNo)
    {
        int i=0;
        while(i<3)
        {
            System.out.println("Enter Password: ");
            Scanner sc= new Scanner(System.in);
            String enteredPassword= sc.nextLine();
            if(enteredPassword.equals(map.get(accountNo).get(1)))
                return true;
            else if(!enteredPassword.equals(map.get(accountNo).get(1)) && i<=1)
                System.out.println("Incorrect Password! Try Again!");
            i++;
        }
        return false;
    }

    public String getAccountNo() {
        return AccountNo;
    }

     public String getBalance() {
        return null;
    }

    @Override
    public String checkBalance() {
        return null;
    }


    public String checkDetails(String enteredAccountNo)
    {
        if(checkAccountNo(enteredAccountNo))
        {
            return "Account No. - "+enteredAccountNo+"\n" +
                    "Name of Account Holder - "+map.get(enteredAccountNo).get(0)+"\n" +
                    "Balance in Account - "+map.get(enteredAccountNo).get(2);
        }
        return "Incorrect Account No.!!!";
    }

    public String addMoney(String enteredAccountNo) {
        if(checkAccountNo(enteredAccountNo))
        {
            System.out.println("Enter Amount to be Added: ");
            int moneyToBeAdded= sc.nextInt();
            int currentBalance= Integer.valueOf(map.get(enteredAccountNo).get(2));
            String newBalance= String.valueOf(currentBalance+moneyToBeAdded);
            ArrayList<String> updatedList= map.get(enteredAccountNo);
            updatedList.set(2,newBalance);
            map.put(enteredAccountNo,updatedList);
            return "Rs."+moneyToBeAdded+" added to the account successfully. Account's new balance is: Rs."+newBalance+".";
        }
        return "Incorrect Account No.!!!";
    }

    @Override
    public String withdrawMoney(String enteredAccountNo) {
        if (checkAccountNo(enteredAccountNo)) {
            if (checkPassword(enteredAccountNo)) {
                System.out.println("Enter Amount to be Withdrawn: ");
                int moneyToBeWithdrawn= sc.nextInt();
                int currentBalance = Integer.valueOf(map.get(enteredAccountNo).get(2));
                if (moneyToBeWithdrawn > currentBalance)
                    return "Insufficient Balance!";
                else {
                    String newBalance = String.valueOf(currentBalance - moneyToBeWithdrawn);
                    ArrayList<String> updatedList = map.get(enteredAccountNo);
                    updatedList.set(2, newBalance);
                    map.put(enteredAccountNo, updatedList);
                    return "Rs." + moneyToBeWithdrawn + " withdrawn from the account successfully. Account's new balance is: Rs." + newBalance+".";
                }
            } else
                return "Entering Incorrect Password limit exceeded. You cannot withdraw money!";
        }
        return "Incorrect Account No.!!!";
    }

    @Override
    public String calculateInterest(String enteredAccountNo) {
        if (checkAccountNo(enteredAccountNo)) {
            System.out.println("Enter no. of years: ");
            int years= sc.nextInt();
            int balance= Integer.valueOf(map.get(enteredAccountNo).get(2));
            float interest= (balance*years*rateOfInterest)/100;
            return "Total Interest Amount -> Rs."+interest;
        }
        return "Incorrect Account No.!!!";
    }

    public String changePassword(String enteredAccountNo)
    {
        if (checkAccountNo(enteredAccountNo)) {
            if (checkPassword(enteredAccountNo)) {
                System.out.println("Enter new Password: ");
                String newPassword= sc.nextLine();
                String currentPassword= map.get(enteredAccountNo).get(1);
                ArrayList<String> updatedList = map.get(enteredAccountNo);
                updatedList.set(1, newPassword);
                map.put(enteredAccountNo, updatedList);
                return "Password changed successfully!";
            }
            else
                return "Entering Incorrect Password limit exceeded. You cannot change your password!";
        }
        return "Incorrect Account No.!!!";
    }

    public String fundsTransfer(String debitAccountNo, String creditAccountNo)
    {
        if(checkAccountNo(debitAccountNo))
        {
            if(checkAccountNo(creditAccountNo))
            {
                if(checkPassword(debitAccountNo)) {
                    System.out.println("Enter Amount to be Funded: ");
                    int fund = sc.nextInt();
                    int currentBalanceOfDebAcc = Integer.valueOf(map.get(debitAccountNo).get(2));
                    if (fund > currentBalanceOfDebAcc)
                        return "Insufficient Balance in Debit Account!";
                    else {
                        String newBalanceOfDebAcc = String.valueOf(currentBalanceOfDebAcc - fund);
                        ArrayList<String> updatedList = map.get(debitAccountNo);
                        updatedList.set(2, newBalanceOfDebAcc);
                        map.put(debitAccountNo, updatedList);

                        int currentBalanceOfCredAcc = Integer.valueOf(map.get(creditAccountNo).get(2));
                        String newBalanceOfCredAcc = String.valueOf(currentBalanceOfCredAcc + fund);
                        updatedList = map.get(creditAccountNo);
                        updatedList.set(2, newBalanceOfCredAcc);
                        map.put(creditAccountNo, updatedList);
                        return "Rs." + fund + " funded successfully.\nNew balance of Debit Account is: Rs." + newBalanceOfDebAcc + ".\nNew balance of Credit Account is: Rs." + newBalanceOfCredAcc + ".";
                    }
                }
            }
            else
                return "Incorrect Credit Account No.!!!";
        }
        return "Incorrect Debit Account No.!!!";
    }
}