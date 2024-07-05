package com.akthon.SunSka.controller;

import com.akthon.SunSka.DTO.*;
import com.akthon.SunSka.model.ProductCategory;
import com.akthon.SunSka.model.Stock;
import com.akthon.SunSka.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public Stock createStock(@RequestBody StockCreateDTO stockData) {
        return stockService.createStock(stockData);
    }

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        Optional<Stock> stock = stockService.getStockById(id);
        return stock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/alert")
    public ResponseEntity<Stock> updateStockAlert(@PathVariable Long id, @RequestBody StockAlertUpdateDTO alert) {
        return stockService.updateAlert(id, alert.alert);
    }

    @GetMapping("/{year}/{building}")
    public List<StockResponseDTO> getStockForBuildingByYear(@PathVariable int year, @PathVariable Long building) {
        return stockService.findStockForBuildingByYear(year, building);
    }

    @GetMapping("/{year}/{building}/alert")
    public List<StockAlertDTO> getStockForBuildingByYearAndIfAlert(@PathVariable int year, @PathVariable Long building) {
        return stockService.findStockForBuildingByYearAndAlerts(year, building);
    }

    @GetMapping("/alert/{year}")
    public List<Long> getStockInAlertByYear(@PathVariable int year) {
        return stockService.findStockInAlertForYear(year);
    }

    @GetMapping("/alert/{year}/{bar}")
    public List<Long> getStockInAlertByYear(@PathVariable int year, @PathVariable Long bar) {
        return stockService.findStockInAlertForBarAndYear(bar, year);
    }

    @GetMapping("/{year}/shop")
    public ShopResponseDTO getShopByYear(@PathVariable int year) {
        return stockService.findShopByYear(year);
    }

//    @GetMapping("/bar-shop-stocks/{bar}")
//    public List<ProductStockDTO> getBarShopStocks(@PathVariable Long bar) {
//        return stockService.getBarShopStocks(bar);
//    }


    // TODO GET stock by building
}
