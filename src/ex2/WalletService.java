package ex2;

import java.util.SortedMap;

public class WalletService {

    boolean deposit(Wallet wallet, double amount) {
        if (wallet == null) return false;

        if (amount <= 0) {
            System.out.println("The amount must to be greater than 0");
            return false;
        }
        if (wallet.getOwner() == null || wallet.getOwner().isEmpty()) {
            System.out.println("The receiver does not exist");
            return false;
        }


        wallet.setBalance(wallet.getBalance() + amount);
        wallet.getHistory().add("Deposit: " + amount);
        return true;
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

    boolean transfer(Wallet senderWallet, Wallet reciverWallet, double amount) {
        if (senderWallet == null || reciverWallet == null || senderWallet == reciverWallet) {
            System.out.println("Check the data again");
            return false;
        }

        if (senderWallet.getOwner() == null || senderWallet.getOwner().isEmpty() ||
                reciverWallet.getOwner() == null || reciverWallet.getOwner().isEmpty() || amount <= 0) {
            return false;
        }

        if (withdraw(senderWallet, amount)) {
            deposit(reciverWallet, amount);
            senderWallet.getHistory().add("Transfer sent to " + reciverWallet.getOwner());
            reciverWallet.getHistory().add("Transfer received from " + senderWallet.getOwner());
            return true;
        }
        return false;
    }

}


