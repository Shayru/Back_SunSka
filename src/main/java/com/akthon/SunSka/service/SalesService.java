package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.SaleCreateDTO;
import com.akthon.SunSka.model.Sales;
import com.akthon.SunSka.model.Stock;
import com.akthon.SunSka.model.User;
import com.akthon.SunSka.repository.SalesRepository;
import com.akthon.SunSka.repository.StockRepository;
import com.akthon.SunSka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SalesService {
    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Optional<Sales> getSaleById(Long id) {
        return salesRepository.findById(id);
    }

    public Sales createSale(SaleCreateDTO saleData) {
        Optional<User> user = this.userRepository.findById(saleData.userId);
        Optional<Stock> stock = this.stockRepository.findById(saleData.stockId);

        if (user.isEmpty() || stock.isEmpty()) {
            return null;
        }

        Sales sale = new Sales();
        sale.setNbSold(saleData.nbSold);
        sale.setUser(user.get());
        sale.setStock(stock.get());
        sale.setCreatedAt(new Date());

        if (saleData.positive) {
            sale.setPositive(true);
            stock.get().setCurrentStock(stock.get().getCurrentStock() + saleData.nbSold);
        } else {
            sale.setPositive(false);
            stock.get().setCurrentStock(stock.get().getCurrentStock() - saleData.nbSold);
        }

        return salesRepository.save(sale);
    }
}
