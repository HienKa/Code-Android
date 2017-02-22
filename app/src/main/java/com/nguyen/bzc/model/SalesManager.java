package com.nguyen.bzc.model;

import java.util.Hashtable;

/**
 * Created by Hien on 12/22/2015.
 */
public class SalesManager {
    private Hashtable<String, Sale> sales;

    private static final SalesManager instance = new SalesManager();

    private SalesManager() {
        sales = new Hashtable<>();
    }

    public static SalesManager getInstance() {
        return instance;
    }


    public Hashtable<String, Sale> getAllSales() {

        return sales;
    }

    public Sale getSale(String name) {
        if (sales.containsKey(name)) {
            return sales.get(name);
        } else {
            return null;
        }
    }

    public void addSale(Sale sale) {
        if (sales.containsKey(sale.getName())) {

        } else {
            sales.put(sale.getName(), sale);
        }
    }

}
