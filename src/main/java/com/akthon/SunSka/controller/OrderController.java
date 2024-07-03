package com.akthon.SunSka.controller;

import com.akthon.SunSka.DTO.OrderUpdateDTO;
import com.akthon.SunSka.model.Order;
import com.akthon.SunSka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        // TODO  faire la gestion du stockOrder
        return orderService.createOrder(order);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody OrderUpdateDTO orderData) {
        Optional<Order> updatedOrder = orderService.updateOrderStatus(id, orderData);
        return updatedOrder.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //TODO Recup les commandes ayant du stock d'un bar
    //TODO Faire la validation de la commande et la modif dans le stock

    //TODO Recup les commandes de tous les bar

    //PARTIE HISTORIQUE
    //TODO get toutes les commandes d'un bar
    //TODO get toutes les commandes d'un produit
    //TODO get toutes les commandes d'un type de produit
    //....


}
