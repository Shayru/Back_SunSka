package com.akthon.SunSka.DTO;

import com.akthon.SunSka.model.Order;

public class OrderWithTypeCreateDTO {
    public Order.OrderType type;
    public Long buildingId;

    public Long stockId;

    public Integer quantity;
}
