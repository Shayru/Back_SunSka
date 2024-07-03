package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.StockCreateDTO;
import com.akthon.SunSka.model.*;
import com.akthon.SunSka.repository.BuildingRepository;
import com.akthon.SunSka.repository.EventRepository;
import com.akthon.SunSka.repository.ProductRepository;
import com.akthon.SunSka.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private ProductRepository productRepository;

//    public Optional<Building> addUserToBuilding(Long buildingId, Long userId) {
//        Optional<Building> building = buildingRepository.findById(buildingId);
//        Optional<User> user = userRepository.findById(userId);
//
//        if (building.isPresent() && user.isPresent()) {
//            Building existingBuilding = building.get();
//            User existingUser = user.get();
//
//            existingBuilding.getUsers().add(existingUser);
//            existingUser.getBuildings().add(existingBuilding);
//
//            buildingRepository.save(existingBuilding);
//            userRepository.save(existingUser);
//
//            return Optional.of(existingBuilding);
//        }
//
//        return Optional.empty();
//    }

    public Stock createStock(StockCreateDTO stock) {
        Stock s = new Stock();

        Optional<Event> event = eventRepository.findById(stock.eventId);
        if(event.isPresent()){
            s.setEvent(event.get());
        }

        Optional<Building> building = buildingRepository.findById(stock.buildingId);
        if(building.isPresent()){
            s.setBuilding(building.get());
        }

        Optional<Product> product = productRepository.findById(stock.productId);
        if(product.isPresent()){
            s.setProduct(product.get());
        }

        s.setInitialStock(stock.initialStock);
        s.setCurrentStock(stock.currentStock);
        s.setWarningAlert(stock.warningAlert);
        return stockRepository.save(s);
    }

    public List<Stock> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stockRepository.findAll();
    }

    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id);
    }


    public List<Long> findStockInAlertForYear(int year) {
        return stockRepository.findStockInAlertForYear(year);
    }

    public List<Long> findStockInAlertForBarAndYear(Long idBar, int year) {
        return stockRepository.findStockInAlertForBarAndYear(idBar, year);
    }
}
