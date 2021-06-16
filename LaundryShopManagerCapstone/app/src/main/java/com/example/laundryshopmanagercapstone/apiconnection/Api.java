package com.example.laundryshopmanagercapstone.apiconnection;

public class Api {
    //globe
    //private static final String ROOT_URL = "http://192.168.254.136/HeroApi/v1/Api.php?apicall=";
    //private static final String ROOT_URL = "http://192.168.254.136/LaundryAppApi/v1/Api.php?apicall=";
    //converge
    //private static final String ROOT_URL = "http://192.168.1.15/HeroApi/v1/Api.php?apicall=";
    private static final String ROOT_URL = "http://192.168.1.15/LaundryAppApi/v1/Api.php?apicall=";

    public static final String URL_CREATE_HERO = ROOT_URL + "createhero";
    public static final String URL_READ_HEROES = ROOT_URL + "getheroes";
    public static final String URL_UPDATE_HERO = ROOT_URL + "updatehero";
    public static final String URL_DELETE_HERO = ROOT_URL + "deletehero&id=";

    //Customer Data
    public static final String URL_CREATE_CUSTOMER = ROOT_URL + "createcustomer";
    public static final String URL_READ_CUSTOMERS = ROOT_URL + "getcustomer";
    public static final String URL_UPDATE_CUSTOMER = ROOT_URL + "updatecustomer";
    public static final String URL_DELETE_CUSTOMER = ROOT_URL + "deleteacustomer&id=";

    //Manager Data
    public static final String URL_CREATE_STORE_MANAGER = ROOT_URL + "createstoremanager";
    public static final String URL_READ_STORE_MANAGERS = ROOT_URL + "getstoremanagers";
    public static final String URL_UPDATE_STORE_MANAGER = ROOT_URL + "updatestoremanager";
    public static final String URL_DELETE_STORE_MANAGER = ROOT_URL + "deleteastoremanager&id=";

    //Product Data
    public static final String URL_CREATE_PRODUCT = ROOT_URL + "createstoremanager";
    public static final String URL_READ_PRODUCT = ROOT_URL + "getproducts";
    public static final String URL_UPDATE_PRODUCT = ROOT_URL + "updatestoremanager";
    public static final String URL_DELETE_PRODUCT = ROOT_URL + "deleteastoremanager&id=";
}
