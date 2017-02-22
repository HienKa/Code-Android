package com.nguyen.bzc.model;

/**
 * Created by Hien on 2/21/2017.
 */

public class Sale {
    private String name;
    private String detail;
    private String key;
    private String store;
    private String description;
    private String saleURL;
    private String begins;
    private String ends;

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public String getEnds() {

        return ends;
    }

    public Sale(String name) {
        this.name = name;
    }

    public Sale(String name, String detail, String key,
                String store, String description, String saleURL,
                String begins, String ends, String imageURL) {
        this.name = name;
        this.detail = detail;
        this.key = key;
        this.store = store;
        this.description = description;
        this.saleURL = saleURL;
        this.begins = begins;
        this.ends = ends;
        this.imageURL = imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {

        return imageURL;
    }

    private String imageURL;


    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public String getKey() {
        return key;
    }

    public String getStore() {
        return store;
    }

    public String getDescription() {
        return description;
    }

    public String getSaleURL() {
        return saleURL;
    }

    public String getBegins() {
        return begins;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSaleURL(String saleURL) {
        this.saleURL = saleURL;
    }

    public void setBegins(String begins) {
        this.begins = begins;
    }


}
