package org.example;

/**
 * Abstract class representing a product
 *
 * @author huyenly
 */
public abstract class Product {
    private String id;
    private String name;

    /**
     * Constructs a Product with the given id and name
     * @param id the product identifier
     * @param name the product name
     */
    public Product(String id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * returns the product id
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * set the product id
     * @param id new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * return the product name
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * set the product name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * perform a stocktake check for this product
     */
    public abstract void performStocktake();
}