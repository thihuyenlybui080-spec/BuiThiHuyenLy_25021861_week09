package org.example;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract base class representing a bank account.
 *
 * <p>Provides common fields and operations for all account types,
 * including deposit, withdrawal, and transaction history tracking.
 *
 * @author BuiThiHuyenLy
 */
public abstract class Account {
    public static final String CHEKING_TYPE = "CHECKING";
    public static final String SAVINGS_TYPE = "SAVINGS";

    private long accountNumber;
    private double balance;
    protected List<Transaction> transactions;

    private static final Logger logger = LoggerFactory.getLogger(Account.class);
    /**
     * Constructs a new Account with the given account number and initial balance.
     *
     * @param accountNumber the unique account identifier
     * @param balance the initial account balance
     */
    public Account(long accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<Transaction>();
    }

    public long getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
        }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Returns the list of transactions.
     *
     * @return transaction list
     */
    public List<Transaction> getTransactionList() {
        return transactions;
    }
    public void setTransactionList(List<Transaction> transactionList) {
        if (transactionList == null) {
            this.transactions = new ArrayList<Transaction>();
        }
        else
            this.transactions = transactionList;
    }

    /**
     * Deposits the given amount into the account
     * @param amount the amount to deposit
     */
    public abstract void deposit(double amount);

    /**
     * Withdraws the given amount from the account
     * @param amount the amount to withdraw
     */
    public abstract void withdraw(double amount);

    /**
     * Performs the actual deposit logic with validation
     *
     * @param amount
     * @throws InvalidFundingAmountException
     */
    protected void doDepositing(double amount) throws InvalidFundingAmountException {
        if (amount <= 0) {
            throw new InvalidFundingAmountException(amount);
        }
        balance += amount;
    }

    /**
     * Performs the actual withdraw logic with validation
     * @param amount
     * @throws InvalidFundingAmountException
     * @throws InsufficientFundsException
     */
    protected void doWithdrawing(double amount) throws InvalidFundingAmountException, InsufficientFundsException {
        // Vi phạm: Tung ra Exception quá chung chung thay vì Exception cụ thể
        if (amount <= 0) {
            throw new InvalidFundingAmountException(amount);
        }
        if (amount > balance){
            throw new InsufficientFundsException(amount);
        }
        balance -= amount;
    }

    /**
     * Adds a transaction to the history if non-null
     * @param transaction
     */
    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
        }
    }

    public String getTransactionHistory() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lịch sử giao dịch của tài khoản ").append(accountNumber).append(":\n");
        for (int i = 0; i < transactions.size(); i++) {
            sb.append(transactions.get(i).getTransactionSummary()); // Vi phạm: Không dùng StringBuilder
            if (i < transactions.size() - 1) {
                sb.append("\n");
            }
        }
        logger.debug("Đã lấy lịch sử cho tài khoản: {}", accountNumber);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        Account other = (Account) obj;
        return this.accountNumber == other.accountNumber;
    }

    @Override
    public int hashCode() {
        return (int) (accountNumber ^ (accountNumber >>> 32));
    }
}
