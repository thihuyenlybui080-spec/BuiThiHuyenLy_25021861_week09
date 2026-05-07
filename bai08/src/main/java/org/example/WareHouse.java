package org.example;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        items.add(item);
    }

    /**
     * removes a product by id
     * @param productId the id of the product to remove
     */
    public void export(String productId){
        items.removeIf(item -> item.getId().equals(productId));
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