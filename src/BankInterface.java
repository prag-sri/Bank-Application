public interface BankInterface {
    String checkBalance();

    String checkDetails(String accountNo);

    String addMoney(String accountNo);

    String withdrawMoney(String accountNo);

    String changePassword(String accountNo);

    String calculateInterest(String accountNo);

    String fundsTransfer(String debitAccountNo, String creditAccountNo);
}
