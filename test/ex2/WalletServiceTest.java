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

    // Transfer tests
    @Test
    void shouldReturnNullWhenSenderWalletIsNull() {
        Wallet senderWallet = new Wallet(null);
        Wallet receiverWaller = new Wallet("Andres");
        WalletService service = new WalletService();

        boolean result = service.transfer(senderWallet, receiverWaller, 100);
        assertFalse(result, "Should not send money when senderWallet is null");
    }

    @Test
    void shouldReturnNullWhenReceiverWalletIsNull() {
        Wallet senderWallet = new Wallet("Andres");
        Wallet receiverWaller = new Wallet(null);
        WalletService service = new WalletService();

        boolean result = service.transfer(senderWallet, receiverWaller, 100);
        assertFalse(result, "Should not send money when senderWallet is null");
    }

    @Test
    void shouldRetornFalseWhenSendingToSameAccount () {
        Wallet senderWallet = new Wallet("Andres");
        WalletService service = new WalletService();

        boolean result = service.transfer(senderWallet, senderWallet, 100);
        assertFalse(result, "Can not send money to same bank account.");
    }

    @Test
    void shouldReturnFalseWhenAmountToTransferIsLessThanBalance() {
        Wallet senderWallet = new Wallet("Andres");
        Wallet receiverWaller = new Wallet("Juan");
        WalletService service = new WalletService();

        senderWallet.setBalance(100.00);

        boolean result = service.transfer(senderWallet, receiverWaller, 101);
        assertFalse(result, "insufficient balance");
    }

    @Test
    void shouldReturnFalseWhenAmountToTransferIsBelowZero() {
        Wallet senderWallet = new Wallet("Andres");
        Wallet receiverWaller = new Wallet("Juan");
        WalletService service = new WalletService();

        senderWallet.setBalance(10);

        boolean result = service.transfer(senderWallet, receiverWaller, -101);
        assertFalse(result, "Amount below zero is not valid");
    }

    @Test
    void shouldReturnFalseIfSenderWalletIsFalse() {
        Wallet receiverWaller = new Wallet("Juan");
        WalletService service = new WalletService();
        boolean result = service.transfer(null, receiverWaller, 10);
        assertFalse(result, "Sender or receiver can not be null");
    }

    @Test
    void shouldReturnFalseIfReceiverWalletIsFalse() {
        Wallet senderWaller = new Wallet("Juan");
        WalletService service = new WalletService();
        boolean result = service.transfer(senderWaller, null, 10);
        assertFalse(result, "Sender or receiver can not be null");
    }

    @Test
    void shouldHandleFailureDuringDepositInTransfer() {
        Wallet sender = new Wallet("Andres");
        sender.setBalance(100.0);

        Wallet receiver = new Wallet(null);

        WalletService service = new WalletService();


        boolean result = service.transfer(sender, receiver, 50.0);

        assertFalse(result, "The transfer will fail if the deposit is not possible");
    }

    @Test
    void ShouldReturnFalseIfSenderWalletOwnerIsEmpty() {
        Wallet receiverWallet = new Wallet("Juan");
        Wallet senderWallet = new Wallet("");
        WalletService service = new WalletService();

        boolean result = service.transfer( senderWallet, receiverWallet, 10);
        assertFalse(result, "The sender wallet name should not be null.");
    }

    @Test
    void shouldReturnFalseIfReceiverOwnerIsEmpty() {
        Wallet sender = new Wallet("Andres");
        sender.setBalance(100.0);
        Wallet receiver = new Wallet("");
        WalletService service = new WalletService();
        boolean result = service.transfer(sender, receiver, 50.0);
        assertFalse(result, "Should fail because receptor has no name");
    }

    @Test
    void ShouldTransferMoneySuccessfully() {
        Wallet senderWallet = new Wallet("Andres");
        Wallet receiverWaller = new Wallet("Juan");
        WalletService service = new WalletService();

        senderWallet.setBalance(10);
        receiverWaller.setBalance(20);

        boolean result = service.transfer(senderWallet, receiverWaller, 10);

        assertTrue(result, "Money sent properly");
        assertEquals(30, receiverWaller.getBalance(), "should add 10 to receiver wallet, 10 + 20(actual) = 30");
        assertEquals(0, senderWallet.getBalance(), "Sender wallet should update to 0. 10 - 10 = 0");
        assertFalse(senderWallet.getHistory().isEmpty(), "The sender wallet history is updated");
        assertFalse(receiverWaller.getHistory().isEmpty(), "The receiver wallet history is updated");
    }
}
