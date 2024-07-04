package com.akthon.SunSka.DTO;

import com.akthon.SunSka.model.Order;

import java.lang.reflect.Array;
import java.util.List;

public class OrderWithTypeCreateDTO {
    public Order.OrderType type;
    public Long buildingId;

    public List<long[]> stockQtts;

    public Integer quantity;
}
