package com.epam.service.impl;

import com.epam.db.dao.OrderDAO;
import com.epam.db.entity.Order;
import com.epam.service.OrderService;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Order createOrder(Order order) {
        return orderDAO.createOrder(order);
    }
}
