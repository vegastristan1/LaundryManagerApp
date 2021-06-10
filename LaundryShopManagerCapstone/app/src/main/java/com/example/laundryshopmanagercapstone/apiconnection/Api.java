package com.example.laundryshopmanagercapstone.apiconnection;

public class Api {
    //globe
    //private static final String ROOT_URL = "http://192.168.254.136/HeroApi/v1/Api.php?apicall=";
    //converge
    private static final String ROOT_URL = "http://192.168.1.6/HeroApi/v1/Api.php?apicall=";

    public static final String URL_CREATE_HERO = ROOT_URL + "createhero";
    public static final String URL_READ_HEROES = ROOT_URL + "getheroes";
    public static final String URL_UPDATE_HERO = ROOT_URL + "updatehero";
    public static final String URL_DELETE_HERO = ROOT_URL + "deletehero&id=";

    //Customer Data
    public static final String URL_CREATE_CUSTOMER = ROOT_URL + "createcustomer";
    public static final String URL_READ_CUSTOMER = ROOT_URL + "getcustomer";
    public static final String URL_UPDATE_CUSTOMER = ROOT_URL + "updatecustomer";
    public static final String URL_DELETE_CUSTOMER = ROOT_URL + "deleteacustomer&id=";
}
