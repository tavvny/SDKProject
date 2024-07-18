package com.example.condencedgeek;

import java.util.List;

public class OrdrPage {
    private String value;
    private String checkData;
    private double ttlPrice;

    public OrdrPage() {
        // Обязательный пустой конструктор для использования Firebase
    }

    public OrdrPage(String value, String checkData, double ttlPrice) {
        this.value = value;
        this.checkData = checkData;
        this.ttlPrice = ttlPrice;
    }

    // Методы доступа к полям класса

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

