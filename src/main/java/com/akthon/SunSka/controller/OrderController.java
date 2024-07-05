package com.akthon.SunSka.controller;

import com.akthon.SunSka.DTO.*;
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
    public Order createOrder(@RequestBody OrderCreateDTO orderData) {
        return orderService.createOrder(orderData);
    }

    @PostMapping("/{type}")
    public Order createOrderByType(@PathVariable Order.OrderType type, @RequestBody OrderWithTypeCreateDTO orderData) {
        // TODO  faire la gestion du stockOrder
        return orderService.createOrderWithType(type, orderData);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody OrderUpdateDTO orderData) {
        Optional<Order> updatedOrder = orderService.updateOrderStatus(id, orderData);
        return updatedOrder.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/bar/{barId}")
    public List<Long> getOrdersWithStockByBar(@PathVariable Long barId) {
        return orderService.getOrdersWithStockByBar(barId);
    }



    //TODO Faire la validation de la commande et la modif dans le stock

    //TODO Recup les commandes de tous les bar
    @GetMapping("/bars")
    public List<Order> getAllTypeOfOrderOfAllBar() {
        return orderService.getAllTypeOfOrderOfAllBar();
    }


    //PARTIE HISTORIQUE
    //TODO get toutes les ventes d'un bar
    @GetMapping("/building/{buildingId}")
    public List<Order> getSalesByBuilding(@PathVariable Long buildingId) {
        return orderService.getOrdersByBuildingAndType(buildingId, Order.OrderType.SALE);
    }
    //TODO get toutes les ventes d'un produit
    @GetMapping("/sales/product/{productId}")
    public ResponseEntity<ProductSalesDTO> getSalesByProductId(@PathVariable Long productId) {
        ProductSalesDTO productSalesDTO = orderService.getSalesByProductId(productId);


        return ResponseEntity.ok(productSalesDTO);
    }

    //TODO get toutes les ventes d'un type de produit
    @GetMapping("/sales/category/{categoryId}")
    public List<CategorySaleDTO> getSalesByCategory(@PathVariable Long categoryId) {
        return orderService.getSalesByCategory(categoryId);
    }


}
