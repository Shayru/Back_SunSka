package com.akthon.SunSka.DTO;

import com.akthon.SunSka.model.Order;

import java.util.Date;

public class OrderDetailDTO {
    public Long id;
    public Date createdAt;
    public Date updatedAt;
    public Order.OrderStatus status;

    public OrderDetailDTO(Long id, Date createdAt, Date updatedAt, Order.OrderStatus status) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;

    }
}
