package com.project.OrderBookSimulation;

public class Order {
    private String id;
    private String type; // "buy" or "sell"
    private String orderType; // "market" or "limit"
    private double price;
    private int quantity;
    private long timestamp; // to prioritize by time

    public Order(String id, String type, String orderType, double price, int quantity) {
        this.id = id;
        this.type = type;
        this.orderType = orderType;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getOrderType() {
        return orderType;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', type='" + type + "', orderType='" + orderType + "', price=" + price + ", quantity=" + quantity + ", timestamp=" + timestamp + '}';
    }
}

