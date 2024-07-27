package com.project.OrderBookSimulation;

import java.util.PriorityQueue;
import java.util.Comparator;

public class OrderBook {
    private PriorityQueue<Order> buyOrders;
    private PriorityQueue<Order> sellOrders;

    public OrderBook() {
        buyOrders = new PriorityQueue<>(new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                if (o2.getPrice() == o1.getPrice()) {
                    return Long.compare(o1.getTimestamp(), o2.getTimestamp());
                }
                return Double.compare(o2.getPrice(), o1.getPrice());
            }
        });

        sellOrders = new PriorityQueue<>(new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                if (o1.getPrice() == o2.getPrice()) {
                    return Long.compare(o1.getTimestamp(), o2.getTimestamp());
                }
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });
    }

    public void addOrder(Order order) {
        if (order.getOrderType().equals("market")) {
            matchMarketOrder(order);
        } else {
            if (order.getType().equals("buy")) {
                buyOrders.add(order);
            } else {
                sellOrders.add(order);
            }
            matchOrders();
        }
    }

    private void matchMarketOrder(Order order) {
        if (order.getType().equals("buy")) {
            while (order.getQuantity() > 0 && !sellOrders.isEmpty()) {
                Order sellOrder = sellOrders.peek();
                int quantity = Math.min(order.getQuantity(), sellOrder.getQuantity());
                order.setQuantity(order.getQuantity() - quantity);
                sellOrder.setQuantity(sellOrder.getQuantity() - quantity);

                if (sellOrder.getQuantity() == 0) sellOrders.poll();
            }
        } else {
            while (order.getQuantity() > 0 && !buyOrders.isEmpty()) {
                Order buyOrder = buyOrders.peek();
                int quantity = Math.min(order.getQuantity(), buyOrder.getQuantity());
                order.setQuantity(order.getQuantity() - quantity);
                buyOrder.setQuantity(buyOrder.getQuantity() - quantity);

                if (buyOrder.getQuantity() == 0) buyOrders.poll();
            }
        }
    }

    private void matchOrders() {
        while (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
            Order buyOrder = buyOrders.peek();
            Order sellOrder = sellOrders.peek();

            if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                int quantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());
                buyOrder.setQuantity(buyOrder.getQuantity() - quantity);
                sellOrder.setQuantity(sellOrder.getQuantity() - quantity);

                if (buyOrder.getQuantity() == 0) buyOrders.poll();
                if (sellOrder.getQuantity() == 0) sellOrders.poll();
            } else {
                break;
            }
        }
    }

    public PriorityQueue<Order> getBuyOrders() {
        return buyOrders;
    }

    public PriorityQueue<Order> getSellOrders() {
        return sellOrders;
    }
}
