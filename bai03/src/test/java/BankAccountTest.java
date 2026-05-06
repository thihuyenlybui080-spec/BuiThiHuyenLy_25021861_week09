import org.example.BankAccount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    private static BankAccount bankAccount;
    private static String accountNumber = "A93049353";
    private static String ownerName = "Nguyen Van A";
    @BeforeEach
    public void steup(){
        bankAccount = new BankAccount(accountNumber, ownerName, 500.0);
    }
    @Test
    public void depositValid(){
        bankAccount.deposit(100);
        assertEquals(600.0, bankAccount.getBalance(), 0.001);
    }
    @Test
    public void testBoundaryDeposit(){
        bankAccount.deposit(0.01);
        assertEquals(500.01, bankAccount.getBalance(), 0.001);
    }
    @Test
    public void testZeroDeposit(){
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(0.0));
    }
    @Test
    public void testNegativeDeposit(){
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-10));
    }
    @Test
    public void testWithdrawValid(){
        assertTrue(bankAccount.withdraw(200));
        assertEquals(300, bankAccount.getBalance(), 0.001);
    }
    @Test
    public void testWithdrawExactBalance(){
        assertTrue(bankAccount.withdraw(500));
        assertEquals(0, bankAccount.getBalance(), 0.001);
    }
    @Test
    public void testWithdrawOverBalance(){
        assertFalse(bankAccount.withdraw(800));
        assertEquals(500, bankAccount.getBalance(), 0.001);
    }
    @Test
    public void testAccountConsistency(){
        BankAccount bankAccount1 = new BankAccount("B9392393", "Huyen Ly", 0.0);
        assertEquals(0, bankAccount1.getBalance());

        bankAccount1.deposit(500);
        assertEquals(500, bankAccount1.getBalance());

        assertTrue( bankAccount1.withdraw(200));
        assertEquals(300, bankAccount1.getBalance());

        assertFalse(bankAccount1.withdraw(400));
        assertEquals(300, bankAccount1.getBalance());
    }

    @Test
    public void testDefaultConstructor(){
        BankAccount acc = new BankAccount("C001", "Test User");
        assertEquals(0.0, acc.getBalance());
        assertEquals("C001", acc.getAccountNumber());
    }

    @Test
    public void testConstructorNegativeBalance(){
        BankAccount acc = new BankAccount("C002", "Test User", -1000.0);
        assertEquals(0.0, acc.getBalance());
    }

    @Test
    public void testWithdrawNegative(){
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100));
    }

    @Test
    public void testWithdrawZero(){
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(0));
    }

    //test các hàm getter
    @Test
    public void testGetters(){
        assertEquals("A93049353", bankAccount.getAccountNumber());
        assertEquals("Nguyen Van A", bankAccount.getOwnerName());
    }

}