package com.epam.db.entity;

import com.epam.db.bean.OrderBean;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Order {

    private int id;
    private String status;
    private String statementDescription;
    private Timestamp date;
    private int userID;
    private String paymentType;
    private String deliveryType;
    private List<OrderBean> orderedProducts;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatementDescription() {
        return statementDescription;
    }

    public void setStatementDescription(String statementDescription) {
        this.statementDescription = statementDescription;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<OrderBean> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<OrderBean> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", statementDescription='" + statementDescription + '\'' +
                ", date=" + date +
                ", paymentType='" + paymentType + '\'' +
                ", deliveryType='" + deliveryType + '\'' +
                ", userID=" + userID +
                ", orderedProducts=" + orderedProducts +
                '}';
    }
}
