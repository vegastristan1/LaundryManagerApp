package com.example.laundryshopmanagercapstone.apiconnection;

public class Product {
    private int id;
    private String product_name, product_price, product_desc, item_name, category_name, store_name;

    public Product(int id, String product_name, String product_price, String product_desc, String item_name, String category_name, String store_name) {
        this.id = id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_desc = product_desc;
        this.item_name = item_name;
        this.category_name = category_name;
        this.store_name = store_name;
    }

    public int getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getStore_name() {
        return store_name;
    }
}
