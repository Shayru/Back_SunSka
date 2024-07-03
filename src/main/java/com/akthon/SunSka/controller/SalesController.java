package com.akthon.SunSka.controller;

import com.akthon.SunSka.DTO.SaleCreateDTO;
import com.akthon.SunSka.model.Sales;
import com.akthon.SunSka.service.SalesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSaleById(@PathVariable Long id) {
        Optional<Sales> sale = salesService.getSaleById(id);
        return sale.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sales createSale(@RequestBody SaleCreateDTO saleData) {
        return salesService.createSale(saleData);
    }

    //PARTIE HISTORIQUE
    //TODO recup les ventes (positive false) + par bar...
    //TODO recup les rajouts (positive true) + par bar...
    //
    //TODO get toutes les sales par bar
    //TODO get toutes les sales par produit
    //TODO get toutes les sales par type de produit
    //....
}
