package com.app.findmedicine.entity;

/**
 * Created by JunChiChen on 2015/7/17.
 * Chang   by JunChiChen on 2017/6/9.
 */
public class data implements java.io.Serializable {
    private String name;
    private String price;
    private String code;
    private String company;
    private String hidPrice;
    private String startyear;
    private String endyear;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public String getStartyear() {
        return startyear;
    }

    public void setStartyear(String startyear) {
        this.startyear = startyear;
    }

    public String getEndyear() {
        return endyear;
    }

    public void setEndyear(String endyear) {
        this.endyear = endyear;
    }


    public String getHidPrice() {
        return hidPrice;
    }

    public void setHidPrice(String hidPrice) {
        this.hidPrice = hidPrice;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
