At the start of the program, add all records into the HashMap using a while loop
Ask the user how many records have to be entered and then create each new record while adding it into the HashMap.

Create a new account by asking for the name, balance, and password of the user.
A UUID will be created for the account.
Create a HashMap having key type as String which will be storing UUID and for value, an ArrayList will be stored- this ArrayList will be of String type and will store name, password, and balance.
So, the HashMap will look somewhat like this:
Key 			Value
UUID    ->    Name, Password, Balance
(Note:- Do check that all details that you are entering are properly getting stored in the HashMap)


After successfully entering the records print out the actions that can be performed by the Bank user:-
Press 1 -> Check Account Details using Account no.
Press 2 -> Add money to the account
Press 3 -> Withdraw money from the account
Press 4 -> Calculate the interest amount on the account
Press 5 -> Change Password
Press 6 -> Funds Transfer
Any other key pressed -> Invalid Operation

The user will enter the int value to perform an action

Using a switch case to perform different actions:-
1) Case 1: 
Checking details of the account using account no.
So the user will be asked to enter the String i.e. enter your account no. whose details you want to know. 
If the account no. is valid that is if the String exists in the Hashmap then we will be printing out the details otherwise we will give the message "Incorrect Account No."
For getting the details of the account find the UUID stored as a key in the HashMap- when found print details in the below format
Account No. - UUID
Name of Account Holder - map.get(UUID).get(0)
Balance in Account - map.get(UUID).get(2)
(Note:- We are not going to give out the password as the details but they will be stored in the HashMap as a record)


2) Case 2:
Adding money to the account, first, we need to get the account no. and the moneyToBeAdded as the input from the user. If an account no. entered is not present in the HashMap as the key then simply we will print "Incorrect Account No."
But if account no. exists then we will get the balance from the Hashmap using the map.get(UUID).get(2) and add moneyToBeAdded in this balance and update the record with the newBalance
(Remember that we stored balance as a string in the HashMap so for performing operations first convert it into int then again convert it into String and update the record in HashMap)


3) Case 3:
Withdrawing money from the account, we will be requiring three things- account no., enteredPassword, and moneyToBeWithdrawn.
First, check if the account no. exists or not- if not print "Incorrect Account No."

Secondly, check if the password of the account matches or not with the enteredPassword- use if(password.equals(enteredPassword)) and this password we will be getting from the map.get(UUID).get(1)- if the correct password then proceeds with the third step otherwise prompt message that "Incorrect Password". Use a while loop which runs until i<=3 (i=1 initially)- and ask for the password if it gets incorrect we will be giving the user at most 3 attempts for entering the correct password after which we will come out of the loop and give the message "Entering Incorrect Password limit exceeded. You cannot withdraw money!"

The third step is that since account no. and entered password is correct you can proceed with the process of withdrawing money.
Check if moneyToBeWithdrawn is less than or greater than the balance in the account- if it is less give the message "Insufficient Balance". 
But if is equal or greater- Get the balance of the account using the map.get(UUID).get(2)- convert the String into int and initialize a newBalance which is storing balance-moneyToBeWithdrawn- now convert newBalance into a string and update this value in the HashMap


4) Case 4:
Calculating the interest amount on the account- for this get the account no. from the user- if invalid, print "Incorrect Account No.". If correct, use- map.get(UUID).get(2) to get the balance- convert it into String and call the function calculateInterest and the interest amount will be returned- print this interestAmount in the below format
Total Interest Amount -> Rs.'interestAmount'


5) Case 5:
Ask for account no. and password from the user- use a function to check if the given account no. exists in HashMap or not
You can have a separate function for checking the Password- as we did in case 3 i.e. withdrawing money- use that function to check if enteredPassword is correct or not, giving at most 3 attempts to the user. 
If enteredPassword is correct- now you will ask for the NewPassword- and simply update this NewPassword in the HashMap.


5) Case 6:
Transfering funds from one account into another- for this firstly check that both account nos. are valid then using map.get(UUIDofDebAcc).get(2) and map.get(UUIDofCredAcc).get(2)- modify the balances and then update it into the HashMap
