package ex2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletServiceTest {

    @Test
    void shouldDepositMoneyCorrectly() {
        Wallet wallet = new Wallet("Andres");
        WalletService service = new WalletService();

        service.deposit(wallet, 100);
        assertEquals(100.0, wallet.getBalance());
        assertEquals(1, wallet.getHistory().size());
    }


    @Test
    void shouldNotWithdrawIfBalanceIsInsufficient() {
        Wallet wallet = new Wallet("Andres");
        WalletService service = new WalletService();

        boolean result = service.withdraw(wallet, 100);

        assertFalse(result, "Should be false, there are no founds.");
        assertEquals(0.0, wallet.getBalance());
    }

    @Test
    void shouldWithdrawCorrectlyIfBalanceIsEnough() {
        Wallet wallet = new Wallet("Andres");
        WalletService service = new WalletService();

        wallet.setBalance(50.0);

        boolean result = service.withdraw(wallet, 20);

        assertTrue(result, "The withdraw is successful");
        assertEquals(30.0, wallet.getBalance(), "Balance should be 30 after (actual 50 - withdraw 20 = 30 left)");
    }

    @Test
    void shouldReturnNullWithUnderZeroDeposit() {
        Wallet wallet = new Wallet("Andres");
        WalletService service = new WalletService();

        service.deposit(null, 20);

        assertEquals(0.0, wallet.getBalance());
    }

    @Test
    void shouldNotDepositMoneyWhenOwnerIsEmpty() {
        Wallet wallet = new Wallet("");
        WalletService service = new WalletService();

        service.deposit(wallet, 20);
        assertEquals(0.0, wallet.getBalance());
    }

    @Test
    void shouldNotDepositNegativeAmount() {
        Wallet wallet = new Wallet("Andres");
        WalletService service = new WalletService();

        service.deposit(wallet, -10);

        assertEquals(0.0, wallet.getBalance(), "Balance should not change with negative numbers");
        assertTrue(wallet.getHistory().isEmpty());
    }

    @Test
    void shouldNotWithdrawNegativeAmount() {
        Wallet wallet = new Wallet("Andres");
        WalletService service = new WalletService();

        service.withdraw(wallet, -10);
        assertEquals(0.0, wallet.getBalance(), "Balance should not change with negative numbers");
        assertTrue(wallet.getHistory().isEmpty());
    }

    @Test
    void shouldReturnFalseWhenWithdrawWalletIsNull() {
        WalletService service = new WalletService();

        boolean result = service.withdraw(null, 100.0);

        assertFalse(result);

    }

    @Test
    void shouldNotDepositWhenOwnerIsNull() {
        Wallet wallet = new Wallet(null);
        WalletService service = new WalletService();

        service.deposit(wallet, 50.0);


        assertEquals(0.0, wallet.getBalance(), "Balance should not change with null owner");
        assertTrue(wallet.getHistory().isEmpty());
    }

}
