package org.example;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Entry point for the warehouse application
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        WareHouse<Food> foodWareHouse = new WareHouse<>();
        WareHouse<Electronics> electronicsWareHouse = new WareHouse<>();
        foodWareHouse.add(new Food("F001", "Banh", "2024-12-31"));
        foodWareHouse.add(new Food("F002", "Sua", "2024-06-01"));
        foodWareHouse.add(new Food("F003", "Nuoc", "2025-01-15"));

        electronicsWareHouse.add(new Electronics("E001", "TiVi", 24));
        electronicsWareHouse.add(new Electronics("E002", "TuLanh", 12));
        electronicsWareHouse.add(new Electronics("E003", "MayGiat", 36));

        logger.info("=== Kho Thuc Pham ===");
        foodWareHouse.stockChecker();

        logger.info("=== Kho Dien Tu ===");
        electronicsWareHouse.stockChecker();

        logger.info("=== Xuat kho F001 va E002 ===");
        foodWareHouse.export("F001");
        electronicsWareHouse.export("E002");

        logger.info("=== Kiem kho sau khi xuat ===");
        foodWareHouse.stockChecker();
        electronicsWareHouse.stockChecker();

        logger.info("=== Application Finished ===");
    }
}