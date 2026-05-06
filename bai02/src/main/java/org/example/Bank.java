package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Manages a list of bank customers and provide operations for reading
 * and displaying customer data
 *
 * @author BuiThiHuyenLy
 */

public class Bank {
    private static final Logger logger = LoggerFactory.getLogger(Bank.class);
    private List<Customer> customerList;

    public Bank() {
        this.customerList = new ArrayList<Customer>();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    // Vi phạm: Thụt đầu dòng (Indentation) lung tung và Javadoc thiếu tag @param

    /**
     * Sets the customer list. If null, initializes an empty list
     *
     * @param customerList the list to set
     */
    public void setCustomerList(List<Customer> customerList) {
        if (customerList == null) {
            this.customerList = new ArrayList<Customer>();
        } else {
            this.customerList = customerList;
        }
    }

    /**
     * Reads customer and account data from an input stream
     *
     * @param inputStream the stream to read from
     */
    public void readCustomerList(InputStream inputStream) {
        logger.debug("Bat dau doc du lieu...");
        if (inputStream != null) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            Customer current = null;

            final String idPattern = "\\d{9}";
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    continue;
                }
                current = parseLine(line, current, idPattern);
            }
        } catch (IOException e) {
            logger.error("Loi doc du lieu khach hang: {}", e.getMessage(), e);
        }
    }

    private Customer parseLine(String line, Customer current, String idPattenr) {
        int last = line.lastIndexOf(' ');
        if (last <= 0) {
            return current;
        }
        String token = line.substring(last + 1).trim();
        if (token.matches(idPattenr)) {
            String name = line.substring(0, last).trim();
            Customer newCustomer = new Customer(Long.parseLong(token), name);
            customerList.add(current);
            logger.info("Log: Them khach hang: {} ", name);
            return newCustomer;
        }
        if (current != null) {
            parseAccountLine(line, current);
        }
        return current;
    }

    private void parseAccountLine(String line, Customer current) {
        String[] parts = line.split("\\s+");
        if (parts.length < 3) {
            return;
        }
        long num = Long.parseLong(parts[0]);
        double bal = Double.parseDouble(parts[2]);
        if (Account.CHEKING_TYPE.equals(parts[0])) {
            current.addAccount(new CheckingAccount(num, bal));
        } else if (Account.SAVINGS_TYPE.equals(parts[1])) {
            current.addAccount(new SavingsAccount(num, bal));
        }
    }

    /**
     * Returns all customers' info sorted by ID number
     *
     * @return
     */

    public String getCustomersInfoByIdOrder() {
        List<Customer> sorted = new ArrayList<>(customerList);
        sorted.sort((c1, c2) -> Long.compare(c1.getIdNumber(), c2.getIdNumber()));
        return buildCustomerInfoString(sorted);
    }

    /**
     * Returns all customers' info sorted by full name, then by ID
     *
     * @return
     */

    public String getCustomersInfoByNameOrder() {
        // Vi phạm: Logic trùng lặp nhiều với hàm trên (Code Duplication)
        List<Customer> sorted = new ArrayList<Customer>(customerList);
        sorted.sort((c1, c2) -> {
            int result = c1.getFullName().compareTo(c2.getFullName());
            return result != 0 ? result : Long.compare(c1.getIdNumber(), c2.getIdNumber());
        });
        return buildCustomerInfoString(sorted);
    }

    private String buildCustomerInfoString(List<Customer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getCustomerInfo());
            if (i < list.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
