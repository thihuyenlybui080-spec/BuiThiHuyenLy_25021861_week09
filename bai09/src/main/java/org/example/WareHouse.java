package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic warehouse that stores products of a specific type
 *
 * @param <T> the type of product stored
 */
public class WareHouse<T extends Product>{
    private static final Logger logger = LoggerFactory.getLogger(WareHouse.class);

    private List<T> items = new ArrayList<>();

    /**
     * Adds a item product to the warehouse
     * @param item the product to add
     */
    public void add(T item){
        if(item == null){
            logger.error("Cannot add null item to warehouse");
        }
        items.add(item);
        logger.debug("Added product: {}", item.getId());
    }

    /**
     * removes a product by id
     * @param productId the id of the product to remove
     */
    public void export(String productId){
        if(productId == null || productId.isEmpty()){
            logger.error("Invalid productId: {}", productId);
        }
        items.removeIf(item -> item.getId().equals(productId));
        logger.info("Exported product: {}", productId);
    }

    /**
     * performs stocktake for all products
     */
    public void stockChecker(){
        for(T item: items) {
            item.performStocktake();
        }
    }
}