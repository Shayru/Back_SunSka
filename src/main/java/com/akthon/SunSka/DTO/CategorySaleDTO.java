package com.akthon.SunSka.DTO;

import com.akthon.SunSka.model.Product;

public class CategorySaleDTO {
    public String nameProduct;
    public Long qtt;
    public Long orderId;


    public CategorySaleDTO(String nameProduct, Long qtt, Long orderId) {
        this.nameProduct = nameProduct;
        this.qtt = qtt;
        this.orderId = orderId;
    }

}




