package com.akthon.SunSka.DTO;

public class StockAlertDTO {
    public Long id;
    public String productName;
    public int productCapacity;
    public String productUnit;
    public int  stockBar;
    public int stockMagasin;
    public boolean isAlert;

    public StockAlertDTO() {
    }

    public StockAlertDTO(Long id, String productName, int productQuantity, String productUnit  , int  stockBar, int stockMagasin, boolean isAlert) {
        this.id = id;
        this.productName = productName;
        this.productCapacity = productQuantity;
        this.productUnit = productUnit;
        this.stockBar = stockBar;
        this.stockMagasin = stockMagasin;
        this.isAlert = isAlert;
    }
}
