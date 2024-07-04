package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.OrderCreateDTO;
import com.akthon.SunSka.DTO.OrderUpdateDTO;
import com.akthon.SunSka.DTO.OrderWithTypeCreateDTO;
import com.akthon.SunSka.model.Building;
import com.akthon.SunSka.model.Order;
import com.akthon.SunSka.model.Stock;
import com.akthon.SunSka.model.StockOrder;
import com.akthon.SunSka.repository.BuildingRepository;
import com.akthon.SunSka.repository.OrderRepository;
import com.akthon.SunSka.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private StockRepository stockRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrder(OrderCreateDTO orderData) {
        return createOrderInternal(orderData.buildingId, orderData.stockId, Order.OrderType.ORDER, orderData.quantity);
    }

    @Transactional
    public Order createOrderWithType(OrderWithTypeCreateDTO orderData) {
        return createOrderInternal(orderData.buildingId, orderData.stockId, orderData.type, orderData.quantity);
    }

    private Order createOrderInternal(Long buildingId, Long stockId, Order.OrderType type, int quantity) {
        Order order = new Order();
        Optional<Building> building = buildingRepository.findById(buildingId);
        if (building.isEmpty()) {
            throw new EntityNotFoundException("Building not found");
        }
        Optional<Stock> stock = stockRepository.findById(stockId);
        if (stock.isEmpty()) {
            throw new EntityNotFoundException("Stock not found");
        }

        order.setBuilding(building.get());
        order.setStatus(Order.OrderStatus.CREATED);
        order.setType(type);
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());

        StockOrder stockOrder = new StockOrder();
        stockOrder.setOrder(order);
        stockOrder.setStock(stock.get());
        stockOrder.setQuantity(quantity);
        order.getStockOrders().add(stockOrder);

        return orderRepository.save(order);
    }

    public Optional<Order> updateOrderStatus(Long id, OrderUpdateDTO orderData) {
        return orderRepository.findById(id).map(existingOrder -> {
            existingOrder.setStatus(orderData.status);
            existingOrder.setUpdatedAt(new Date());
            return orderRepository.save(existingOrder);
        });
    }

    public List<Long> getOrdersWithStockByBar(Long barId) {
        Optional<Building> bar = this.buildingRepository.findById(barId);
        return bar.map(building -> orderRepository.findByBar(building.getId())).orElse(null);
    }
}
