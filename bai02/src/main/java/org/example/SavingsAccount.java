package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tai khoan tiet kiem - Lop nay thuc thi cac quy dinh ve rut tien và nap tien.
 *
 */
public class SavingsAccount extends Account {
    private static final Logger logger = LoggerFactory.getLogger(SavingsAccount.class);

    private static final double MAX_WITHDRAW_AMOUNT = 1000.0;
    private static final double MIN_BALANCE_REQUIRED = 5000.0;

    /**
     * tao mot tai khoan tiet kiem voi so va so du
     * @param accountNumber
     * @param balance
     */
    public SavingsAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /**
     * nap so du vao tai khoan tiet kiem nay
     *
     * @param amount the amount to deposit
     */
    @Override
    public void deposit(double amount) {
        logger.debug("Giao dich dang xu ly {} cho tai khoan {}", amount, getAccountNumber());
        double initialBalance = getBalance();
        try {
            doDepositing(amount);
            double finalBalance = getBalance();
            // Vi phạm: Magic Number '3' (Nên dùng Transaction.TYPE_DEPOSIT_SAVINGS)
            // Vi phạm: Dòng code quá dài
            Transaction t = new Transaction(
                    Transaction.TYPE_DEPOSIT_SAVINGS, amount, initialBalance, finalBalance);
            addTransaction(t);
            logger.info("Nap tien vao tai khoan {} thanh cong {} ",getAccountNumber() , amount);
        } catch (BankException e) {
            logger.error("Loi nap tien: {} ", e.getMessage());
        }
    }

    /**
     * rut tien tu tai khoan tiet kiem nay
     *
     * @param amount the amount to withdraw
     */
    @Override
    public void withdraw(double amount) {
        double initialBalance = getBalance();
        try {
            if (amount > MAX_WITHDRAW_AMOUNT) {
                throw new InvalidFundingAmountException(amount);
            }
            if (initialBalance - amount < MIN_BALANCE_REQUIRED) {
                throw new InsufficientFundsException(amount);
            }
            
            doWithdrawing(amount);
            double finalBalance = getBalance();
            Transaction transaction = new Transaction(Transaction.TYPE_WITHDRAW_SAVINGS, amount, initialBalance, finalBalance);
            addTransaction(transaction);

            logger.info("Ru thanh cong cho tai khoan {}: -{}, So du con: {} ",
                    getAccountNumber(), amount, finalBalance);
        } catch (BankException e) {
            logger.error("Rut tien bi loi cho tai khoan {}: {}", getAccountNumber(), e.getMessage());
        }
    }
}