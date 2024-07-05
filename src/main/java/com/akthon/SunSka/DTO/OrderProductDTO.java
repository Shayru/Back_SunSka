package com.akthon.SunSka.DTO;

import com.akthon.SunSka.model.Order;
import com.akthon.SunSka.model.Product;

import java.util.Date;
import java.util.List;

public class OrderProductDTO {
    public Long id;
    public List<ProductBarDTO> products;

    public OrderProductDTO(Long id, List<ProductBarDTO> products) {
        this.id = id;
        this.products = products;
    }
}
