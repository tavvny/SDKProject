package com.example.condencedgeek.Model;

public class Items {
    private String value;
    private String checkData;
    private double ttlPrice;

    public Items() {
        // Обязательный пустой конструктор для Firebase
    }

    public Items(String value, String checkData, double ttlPrice) {
        this.value = value;
        this.checkData = checkData;
        this.ttlPrice = ttlPrice;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCheckData() {
        return checkData;
    }

    public void setCheckData(String checkData) {
        this.checkData = checkData;
    }

    public double getTtlPrice() {
        return ttlPrice;
    }

    public void setTtlPrice(double ttlPrice) {
        this.ttlPrice = ttlPrice;
    }
}




