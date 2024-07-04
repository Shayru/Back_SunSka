package com.akthon.SunSka.DTO;

public class StockResponseDTO {

    public int currentStock;
    public String name;
    public int capacity;
    public String unit;

    public Long productId;
    public Long stockId;

    public StockResponseDTO() {
    }

    public StockResponseDTO(int currentStock, String name, int capacity, String unit, Long productId, Long stockId) {
        this.currentStock = currentStock;
        this.name = name;
        this.capacity = capacity;
        this.unit = unit;
        this.productId = productId;
        this.stockId = stockId;
    }
}
