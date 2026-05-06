package org.example;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Đại diện cho một giao dịch.
 */
public class Transaction {
    private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

    public static final int TYPE_DEPOSIT_CHECKING = 1;
    public static final int TYPE_WITHDRAW_CHECKING = 2;
    public static final int TYPE_DEPOSIT_SAVINGS = 3;
    public static final int TYPE_WITHDRAW_SAVINGS = 4;

    private int type;
    private double amount;
    private double initialBalance;
    private double finalBalance;

    /**
     * tao mot giao dich
     *
     * @param type loai giao dich
     * @param amount so du giao dich
     * @param initialBalance so du truoc khi giao dich
     * @param finalBalance so du sau khi giao dic
     */
    public Transaction(
            int type, double amount, double initialBalance, double finalBalance) {
        this.type = type;
        this.amount = amount;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(double finalBalance) {
        this.finalBalance = finalBalance;
    }

    /**
     * tra ve mot chuoi ki tu de doc tuong ung voi loai giao dich
     * @param transactionType
     * @return
     */
    public static String getTypeString(int transactionType) {
        switch (transactionType) {
            case TYPE_DEPOSIT_CHECKING:
                return "Nạp tiền vãng lai";
            case TYPE_WITHDRAW_CHECKING:
                return "Rút tiền vãng lai";
            case TYPE_DEPOSIT_SAVINGS:
                return "Nạp tiền tiết kiệm";
            case TYPE_WITHDRAW_SAVINGS:
                return "Rút tiền tiết kiệm";
            default:
                return "Không rõ";
        }
    }

    public String getTransactionSummary() {
        logger.debug("Generating summary process started for type:{} ", this.type);
        String typeLabel = getTypeString(type);
        String formattedInitial = String.format(Locale.US, "%.2f", initialBalance);
        String formattedAmount = String.format(Locale.US, "%.2f", amount);
        String formattedFinal = String.format(Locale.US, "%.2f", finalBalance);

        return "- Kiểu giao dịch: " + typeLabel
                + ". Số dư ban đầu: $" + formattedInitial
                + ". Số tiền: $" + formattedAmount
                +". Số dư cuối: $" + formattedFinal + ".";
    }
}
