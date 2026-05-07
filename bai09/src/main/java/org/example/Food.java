package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a food product with an expiry date
 */
public class Food extends Product {
    private static final Logger logger = LoggerFactory.getLogger(Food.class);
    private String expiryDate;

    /**
     * Constructs a Food product
     * @param id the product identifier
     * @param name the name product
     * @param expiryDate the expiry date
     */
    public Food(String id, String name, String expiryDate){
        super(id, name);
        this.expiryDate = expiryDate;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    @Override
    public void performStocktake(){
        logger.info("{} - {}", getName(), expiryDate);
    }
}