package ex2;

public class WalletService {

    void deposit(Wallet wallet, double amount) {
        if (wallet == null) return;

        if (amount <= 0) {
            System.out.println("The amount must to be greater than 0");
            return;
        }
        if (wallet.getOwner() == null || wallet.getOwner().isEmpty()) {
            System.out.println("The receiver does not exist");
            return;
        }


        wallet.setBalance(wallet.getBalance() + amount);
        wallet.getHistory().add("Deposit: " + amount);
    }

    boolean withdraw(Wallet wallet, double amount) {
        if (wallet == null) return false;

        if (amount <= 0 || amount > wallet.getBalance()) {
            System.out.println("Invalid amount or insufficient balance");
            return false;
        }
        wallet.setBalance(wallet.getBalance() - amount);
        wallet.getHistory().add("Withdraw " + amount);
        return true;

    }
}

