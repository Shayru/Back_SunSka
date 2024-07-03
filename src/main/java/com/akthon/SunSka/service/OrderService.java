package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.OrderUpdateDTO;
import com.akthon.SunSka.model.Order;
import com.akthon.SunSka.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> updateOrderStatus(Long id, OrderUpdateDTO orderData) {
        return orderRepository.findById(id).map(existingOrder -> {
            existingOrder.setStatus(orderData.status);
            existingOrder.setDateRestock(orderData.dateRestock);
            return orderRepository.save(existingOrder);
        });
    }
}
