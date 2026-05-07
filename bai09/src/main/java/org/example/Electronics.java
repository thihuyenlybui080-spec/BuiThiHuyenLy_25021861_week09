package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * represents an electronics product with a warranty period
 */
public class Electronics extends Product {
    private static final Logger logger = LoggerFactory.getLogger(Electronics.class);
    private int warranty;

    /**
     * Constructs an electronics product
     * @param id the product identifier
     * @param name the product name
     * @param warranty the warranty period
     */
    public Electronics(String id, String name, int warranty){
        super(id, name);
        this.warranty = warranty;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }
    @Override
    public void performStocktake(){
        logger.info("{} - {} thang bao hanh", getName(), warranty);
    }
}