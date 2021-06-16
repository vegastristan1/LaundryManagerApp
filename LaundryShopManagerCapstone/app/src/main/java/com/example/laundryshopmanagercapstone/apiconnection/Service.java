package com.example.laundryshopmanagercapstone.apiconnection;

public class Service {
    private int id;
    private String service_name, service_price, service_desc, item_name, category_name, store_name;

    public Service(int id, String service_name, String service_price, String service_desc, String item_name, String category_name, String store_name) {
        this.id = id;
        this.service_name = service_name;
        this.service_price = service_price;
        this.service_desc = service_desc;
        this.item_name = item_name;
        this.category_name = category_name;
        this.store_name = store_name;
    }

    public int getId() {
        return id;
    }

    public String getService_name() {
        return service_name;
    }

    public String getService_price() {
        return service_price;
    }

    public String getService_desc() {
        return service_desc;
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
