package ex2;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private String owner;
    private double balance;
    private List<String> history;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    public Wallet(String owner) {
        this.owner = owner;
        this.balance = 0;
        this.history = new ArrayList<>();
    }
}
