package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.*;
import com.akthon.SunSka.model.*;
import com.akthon.SunSka.repository.BuildingRepository;
import com.akthon.SunSka.repository.OrderRepository;
import com.akthon.SunSka.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return createOrderInternal(orderData.buildingId, orderData.stockQtts, Order.OrderType.ORDER);
    }

    @Transactional
    public Order createOrderWithType(OrderWithTypeCreateDTO orderData) {
        return createOrderInternal(orderData.buildingId, orderData.stockQtts, orderData.type);
    }

    private Order createOrderInternal(Long buildingId, List<long[]> stockQtts, Order.OrderType type) {
        Optional<Building> building = buildingRepository.findById(buildingId);
        if (building.isEmpty()) {
            throw new EntityNotFoundException("Building not found");
        }

        // Extract stock IDs from stockQtts
        List<Long> stockIds = stockQtts.stream()
                .map(stockQtt -> stockQtt[0])
                .collect(Collectors.toList());

        List<Stock> stocks = stockRepository.findAllById(stockIds);
        if (stocks.size() != stockIds.size()) {
            throw new EntityNotFoundException("One or more stocks not found");
        }

        Order order = new Order();
        order.setBuilding(building.get());
        order.setStatus(Order.OrderStatus.CREATED);
        order.setType(type);
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());

        for (long[] stockQtt : stockQtts) {
            Long stockId = stockQtt[0];
            Integer quantity = (int) stockQtt[1];

            Optional<Stock> stock = stocks.stream()
                    .filter(s -> s.getId().equals(stockId))
                    .findFirst();

            if (stock.isPresent()) {
                StockOrder stockOrder = new StockOrder();
                stockOrder.setOrder(order);
                stockOrder.setStock(stock.get());
                stockOrder.setQuantity(quantity);
                order.getStockOrders().add(stockOrder);
            } else {
                throw new EntityNotFoundException("Stock not found for ID: " + stockId);
            }
        }

        this.updateStockForOrder(order, type);

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

    public List<Order> getOrdersByBuildingAndType(Long buildingId, Order.OrderType type) {
        return orderRepository.findByBuildingIdAndType(buildingId, type);
    }

    public ProductSalesDTO getSalesByProductId(Long productId) {
        List<Order> orders = orderRepository.findAll();

        String productName = orders.stream()
                .flatMap(order -> order.getStockOrders().stream())
                .filter(stockOrder -> stockOrder.getStock().getProduct().getId().equals(productId))
                .map(stockOrder -> stockOrder.getStock().getProduct().getName())
                .findFirst()
                .orElse(null);

        List<ProductSalesDTO.SaleDetail> saleDetails = orders.stream()
                .filter(order -> order.getType() == Order.OrderType.SALE)
                .flatMap(order -> order.getStockOrders().stream()
                        .filter(stockOrder -> stockOrder.getStock().getProduct().getId().equals(productId))
                        .map(stockOrder -> {
                            ProductSalesDTO.SaleDetail saleDetail = new ProductSalesDTO.SaleDetail();
                            saleDetail.setOrderId(order.getId());
                            saleDetail.setSaleDate(order.getCreatedAt());
                            saleDetail.setQuantity(stockOrder.getQuantity());
                            return saleDetail;
                        }))
                .collect(Collectors.toList());

        ProductSalesDTO productSalesDTO = new ProductSalesDTO();
        productSalesDTO.setProductId(productId);
        productSalesDTO.setProductName(productName);
        productSalesDTO.setSales(saleDetails);

        return productSalesDTO;
    }

    public void updateStockForOrder(Order order, Order.OrderType orderType) {
        if (orderType == Order.OrderType.SALE) {
            order.getStockOrders().forEach(stockOrder -> {
                Stock stock = stockOrder.getStock();
                stock.setCurrentStock(stock.getCurrentStock() - stockOrder.getQuantity());
                stockRepository.save(stock);
            });
        } else if (orderType == Order.OrderType.RESTOCK) {
            order.getStockOrders().forEach(stockOrder -> {
                Stock stock = stockOrder.getStock();
                stock.setCurrentStock(stock.getCurrentStock() + stockOrder.getQuantity());
                stockRepository.save(stock);
            });
        } else if (orderType == Order.OrderType.ORDER) {
            // TODO faire + qtt bar et - qtt magasin
            order.getStockOrders().forEach(stockOrder -> {
                Stock stock = stockOrder.getStock();
                stock.setCurrentStock(stock.getCurrentStock() + stockOrder.getQuantity());
                stockRepository.save(stock);
            });

            //get current Year basing on the createdAt of the order
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(order.getCreatedAt());
            int year = calendar.get(Calendar.YEAR);

            ShopResponseDTO shopResponse = this.stockRepository.findShopByYear(year);
            Optional<Building> shop = this.buildingRepository.findById(shopResponse.buildingId);

            //foreach stock of the order get the product and compare if the stock of the building have the same product
            order.getStockOrders().forEach(stockOrder -> {
                Product product = stockOrder.getStock().getProduct();
                Optional<Stock> stockShop = shop.get().getStocks().stream()
                        .filter(s -> s.getProduct().getId().equals(product.getId()))
                        .findFirst();
                if (stockShop.isPresent()) {
                    stockShop.get().setCurrentStock(stockShop.get().getCurrentStock() - stockOrder.getQuantity());
                    stockRepository.save(stockShop.get());
                }
            });
        }
    }
}
