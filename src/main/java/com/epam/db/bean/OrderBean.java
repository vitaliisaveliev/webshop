package com.epam.db.bean;

public class OrderBean {

    private int productId;
    private int count;
    private double price;

    public OrderBean() {
    }

    public OrderBean(int productId, int count, double price) {
        this.productId = productId;
        this.count = count;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "productId=" + productId +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
