package org.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a bank account with deposit and withdrawal operations.
 * @author BuiThiHuyenLy
 */
public class BankAccount {
    private static final Logger logger = LoggerFactory.getLogger(BankAccount.class);
    private final String accountNumber;
    private String ownerName;
    private double balance;

    /**
     * Constructs a BankAccount with zero initial balance
     * @param accountNumber
     * @param ownerName
     */
    public BankAccount(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0.0;
    }

    /**
     * Constructs a BankAccount with a given initial balance
     * @param accountNumber
     * @param ownerName
     * @param initialBalance
     */
    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        if (initialBalance < 0) {
            logger.warn("Số dư ban đầu không hợp lệ: {}.  Gán mặc định là 0.", initialBalance);
            this.balance = 0.0;
        } else {
            this.balance = initialBalance;
        }
    }

    /**
     * Deposits the given amount into this account
     * @param amount
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền nạp phải lớn hơn 0.");
        }
        this.balance += amount;
        logger.info("Nap tien {} vao tai khoan {}. So du moi: {}", amount, accountNumber, balance);
    }

    /**
     * Withdraws the given amount from this account
     * @param amount
     * @return
     */
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền rút phải lớn hơn 0.");
        }
        if (amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        logger.info("Da rut {} tu tai khoan {}. So du moi {}", amount, accountNumber, balance);
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
