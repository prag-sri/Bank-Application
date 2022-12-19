import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Main {
    static HashMap<String, ArrayList<String>> map;
    public static void main(String[] args) {

        map= new HashMap<>();

        Scanner sc= new Scanner(System.in);

        System.out.println("Enter no. of accounts to be created: ");
        int noOfAccounts= sc.nextInt();
        sc.nextLine();
        while(noOfAccounts>0)
        {
            System.out.println("Enter Name, Balance and Password: ");
            String name= sc.nextLine();
            String balance= sc.nextLine();
            String password= sc.nextLine();
            SBIUser acc1= new SBIUser(name,balance,password,map);
            noOfAccounts--;
        }

        //Printing all Bank Records for Checking Purposes
        System.out.println("All Accounts Details-");
        for(String key: map.keySet())
        {
            System.out.println(key+" "+map.get(key).get(0)+" "+map.get(key).get(1)+" "+map.get(key).get(2));
        }
        System.out.println();

        System.out.println("Welcome... Press a key to perform the action!");
        System.out.println(
                "Press 1 -> Check Account Details using Account no.\n" +
                "Press 2 -> Add money to the account\n" +
                "Press 3 -> Withdraw money from the account\n" +
                "Press 4 -> Calculate the interest amount on the account\n" +
                "Press 5 -> Change Password\n" +
                "Press 6 -> Funds Transfer");
        int actionPerformed= sc.nextInt();
        sc.nextLine();

        SBIUser obj= new SBIUser();

        switch (actionPerformed)
        {
            case 1:
                System.out.println("Enter Account No.: ");
                String enteredAccountNo= sc.nextLine();
                System.out.println(obj.checkDetails(enteredAccountNo));
                break;

            case 2:
                System.out.println("Enter Account No.: ");
                enteredAccountNo= sc.nextLine();
                System.out.println(obj.addMoney(enteredAccountNo));
                break;

            case 3:
                System.out.println("Enter Account No.: ");
                enteredAccountNo= sc.nextLine();
                System.out.println(obj.withdrawMoney(enteredAccountNo));
                break;

            case 4:
                System.out.println("Enter Account No.: ");
                enteredAccountNo= sc.nextLine();
                System.out.println(obj.calculateInterest(enteredAccountNo));
                break;

            case 5:
                System.out.println("Enter Account No.: ");
                enteredAccountNo= sc.nextLine();
                System.out.println(obj.changePassword(enteredAccountNo));
                break;

            case 6:
                System.out.println("Enter Account No. from which Funds are to be Debited: ");
                String debitAccountNo= sc.nextLine();
                System.out.println("Enter Account No. in which Funds are to be Credited: ");
                String creditAccountNo= sc.nextLine();
                System.out.println(obj.fundsTransfer(debitAccountNo,creditAccountNo));
                break;

            default:
                System.out.println("Invalid Operation");
        }

//        System.out.println("All Accounts New Details-");
//        for(String key: map.keySet())
//        {
//            System.out.println(key+" "+map.get(key).get(0)+" "+map.get(key).get(1)+" "+map.get(key).get(2));
//        }
//        System.out.println();
    }
}