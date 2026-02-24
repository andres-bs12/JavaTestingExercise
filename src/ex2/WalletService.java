package ex2;

import java.util.SortedMap;

public class WalletService {

    boolean deposit(Wallet wallet, double amount) {
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet can't be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("amount can not be negative");
        }
        if (wallet.getOwner() == null || wallet.getOwner().isEmpty()) {
            throw new IllegalArgumentException("The receiver does not exist");
        }

        wallet.setBalance(wallet.getBalance() + amount);
        wallet.getHistory().add("Deposit: " + amount);
        return true;
    }

    void withdraw(Wallet wallet, double amount) {
        if (wallet == null)
            throw new IllegalArgumentException("Wallet can't be null");
        ;

        if (amount <= 0) {
            throw new IllegalArgumentException("The amount has to be positive");
        }

        if (amount > wallet.getBalance()) {
            throw new IllegalArgumentException("Not enough balance: you have " + wallet.getBalance());

        }
        wallet.setBalance(wallet.getBalance() - amount);
        wallet.getHistory().add("Withdraw " + amount);


    }

    void transfer(Wallet senderWallet, Wallet reciverWallet, double amount) {
        if (senderWallet == null || reciverWallet == null) {
            throw new IllegalArgumentException("The wallets can't be null");
        }

        if (senderWallet == reciverWallet) {
            throw new IllegalArgumentException("You can not transfer to yourself");
        }

        if (reciverWallet.getOwner() == null || reciverWallet.getOwner().isEmpty()) {
            throw new IllegalArgumentException("Receiver invalid");
        }

        withdraw(senderWallet, amount);
        deposit(reciverWallet, amount);


        senderWallet.getHistory().add("Transfer sent to " + reciverWallet.getOwner());
        reciverWallet.getHistory().add("Transfer received from " + senderWallet.getOwner());

    }

}


