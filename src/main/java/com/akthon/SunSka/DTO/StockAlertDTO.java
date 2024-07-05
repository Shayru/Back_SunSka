package com.akthon.SunSka.DTO;

public class StockAlertDTO {
    public String productName;
    public int  stockBar;
    public int stockMagasin;
    public boolean isAlert;

    public StockAlertDTO() {
    }

    public StockAlertDTO(String productName, int  stockBar, int stockMagasin, boolean isAlert) {
        this.productName = productName;
        this.stockBar = stockBar;
        this.stockMagasin = stockMagasin;
        this.isAlert = isAlert;
    }
}
