package com.example.laundryshopmanagercapstone.apiconnection;

public class Manager {
    private int id;
    private String store_name, store_owner, store_contact_number, store_address, store_password, store_email;

    public Manager(int id, String store_name, String store_owner, String store_contact_number, String store_address, String store_password, String store_email) {
        this.id = id;
        this.store_name = store_name;
        this.store_owner = store_owner;
        this.store_contact_number = store_contact_number;
        this.store_address = store_address;
        this.store_password = store_password;
        this.store_email = store_email;
    }

    public int getId() {
        return id;
    }

    public String getStore_name() {
        return store_name;
    }

    public String getStore_owner() {
        return store_owner;
    }

    public String getStore_contact_number() {
        return store_contact_number;
    }

    public String getStore_address() {
        return store_address;
    }

    public String getStore_password() {
        return store_password;
    }

    public String getStore_email() {
        return store_email;
    }
}
