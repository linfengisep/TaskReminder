package com.example.remote.service;

public class Product {
    private int id;
    private String name;
    private int price;
    private int buyingPrice;

    public Product(int id, String name, int price, int buyingPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.buyingPrice = buyingPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(int buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", buyingPrice=" + buyingPrice +
                '}';
    }
}
