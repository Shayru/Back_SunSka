package com.akthon.SunSka.DTO;

import com.akthon.SunSka.model.Order;

import java.util.Date;
import java.util.List;

public class ProductSalesDTO {
    private Long productId;
    private String productName;
    private List<SaleDetail> sales;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<SaleDetail> getSales() {
        return sales;
    }

    public void setSales(List<SaleDetail> sales) {
        this.sales = sales;
    }



    public static class SaleDetail {
        private Long orderId;
        private Date saleDate;
        private Integer quantity;
        public String barName;

        public Long getOrderId() {
            return orderId;
        }
        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }
        public Date getSaleDate() {
            return saleDate;
        }
        public void setSaleDate(Date saleDate) {
            this.saleDate = saleDate;
        }
        public Integer getQuantity() {
            return quantity;
        }
        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

    }
}

