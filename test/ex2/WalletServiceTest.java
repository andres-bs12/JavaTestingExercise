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
    void shouldThrowExceptionWhenDepositToNull() {
        WalletService service = new WalletService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.deposit(null, 20.0);
        });
    }

    @Test
    void shouldThrowExceptionWhenBalanceIsInsufficient() {
        Wallet wallet = new Wallet("Andres");
        wallet.setBalance(10.0);
        WalletService service = new WalletService();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.withdraw(wallet, 100.0);
        });
    }

    @Test
    void shouldWithdrawCorrectlyIfBalanceIsEnough() {
        Wallet wallet = new Wallet("Andres");
        WalletService service = new WalletService();

        wallet.setBalance(50.0);
        service.withdraw(wallet, 20);

        assertEquals(30.0, wallet.getBalance(), "Balance should be 30 after (actual 50 - withdraw 20 = 30 left)");
    }

    @Test
    void shouldReturnNullWithUnderZeroDeposit() {
        Wallet wallet = new Wallet("Andres");
        WalletService service = new WalletService();
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.deposit(null, 20);
        });
    }

    @Test
    void shouldThrowExceptionWhenOwnerIsEmpty() {
        Wallet wallet = new Wallet("");
        WalletService service = new WalletService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.deposit(wallet, 20);
        });
    }

    @Test
    void shouldThrowExceptionWhenDepositIsNegative() {
        Wallet wallet = new Wallet("Andres");
        WalletService service = new WalletService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.deposit(wallet, -10);
        });
    }

    @Test
    void shouldNotWithdrawNegativeAmount() {
        Wallet wallet = new Wallet("Andres");
        wallet.setBalance(50.0);
        WalletService service = new WalletService();


        assertThrows(IllegalArgumentException.class, () -> {
            service.withdraw(wallet, -10);
        });


        assertEquals(50.0, wallet.getBalance(), "The balance should not change");
        assertTrue(wallet.getHistory().isEmpty(), "The history must be empty");
    }

    @Test
    void shouldReturnFalseWhenWithdrawWalletIsNull() {
        WalletService service = new WalletService();

        //boolean result = service.withdraw(null, 100.0);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.withdraw(null, 100.0);
        });
    }

    @Test
    void shouldThrowExceptionWhenOwnerIsNull() {
        Wallet wallet = new Wallet(null);
        WalletService service = new WalletService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.deposit(wallet, 50.0);
        });
    }

    // Transfer tests
    @Test
    void shouldReturnNullWhenSenderWalletIsNull() {
        Wallet senderWallet = new Wallet(null);
        Wallet receiverWaller = new Wallet("Andres");
        WalletService service = new WalletService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(senderWallet, receiverWaller, 100);
        });
    }

    @Test
    void shouldReturnNullWhenReceiverWalletIsNull() {
        Wallet senderWallet = new Wallet("Andres");
        Wallet receiverWaller = new Wallet(null);
        WalletService service = new WalletService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(senderWallet, receiverWaller, 100);
        });
    }

    @Test
    void shouldRetornFalseWhenSendingToSameAccount() {
        Wallet senderWallet = new Wallet("Andres");
        WalletService service = new WalletService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(senderWallet, senderWallet, 100);
        });

    }

    @Test
    void shouldReturnFalseWhenAmountToTransferIsLessThanBalance() {
        Wallet senderWallet = new Wallet("Andres");
        Wallet receiverWaller = new Wallet("Juan");
        WalletService service = new WalletService();

        senderWallet.setBalance(100.00);

        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(senderWallet, receiverWaller, 101);
        });
    }

    @Test
    void shouldReturnFalseWhenAmountToTransferIsBelowZero() {
        Wallet senderWallet = new Wallet("Andres");
        Wallet receiverWaller = new Wallet("Juan");
        WalletService service = new WalletService();

        senderWallet.setBalance(10);

        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(senderWallet, receiverWaller, -101);
        });
    }

    @Test
    void shouldReturnFalseIfSenderWalletIsFalse() {
        Wallet receiverWaller = new Wallet("Juan");
        WalletService service = new WalletService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(null, receiverWaller, 10);
        });
    }

    @Test
    void shouldReturnFalseIfReceiverWalletIsFalse() {
        Wallet senderWaller = new Wallet("Juan");
        WalletService service = new WalletService();
        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(senderWaller, null, 10);
        });
    }

    @Test
    void ShouldReturnFalseIfSenderWalletOwnerIsEmpty() {
        Wallet receiverWallet = new Wallet("Juan");
        Wallet senderWallet = new Wallet("");
        WalletService service = new WalletService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(senderWallet, receiverWallet, 10);
        });
    }

    @Test
    void shouldReturnFalseIfReceiverOwnerIsEmpty() {
        Wallet sender = new Wallet("Andres");
        sender.setBalance(100.0);
        Wallet receiver = new Wallet("");
        WalletService service = new WalletService();

        assertThrows(IllegalArgumentException.class, () -> {
            service.transfer(sender, receiver, 50.0);
        });

        assertEquals(100.0, sender.getBalance(), "The balance should not change");
        assertTrue(sender.getHistory().isEmpty(), "The history must not change");
    }

    @Test
    void ShouldTransferMoneySuccessfully() {
        Wallet senderWallet = new Wallet("Andres");
        Wallet receiverWaller = new Wallet("Juan");
        WalletService service = new WalletService();

        senderWallet.setBalance(10);
        receiverWaller.setBalance(20);

        service.transfer(senderWallet, receiverWaller, 10);

        assertEquals(30, receiverWaller.getBalance(), "should add 10 to receiver wallet, 10 + 20(actual) = 30");
        assertEquals(0, senderWallet.getBalance(), "Sender wallet should update to 0. 10 - 10 = 0");
        assertFalse(senderWallet.getHistory().isEmpty(), "The sender wallet history is updated");
        assertFalse(receiverWaller.getHistory().isEmpty(), "The receiver wallet history is updated");
    }
}
