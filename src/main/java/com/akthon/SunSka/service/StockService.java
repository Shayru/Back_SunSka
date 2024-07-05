package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.*;
import com.akthon.SunSka.model.*;
import com.akthon.SunSka.repository.BuildingRepository;
import com.akthon.SunSka.repository.EventRepository;
import com.akthon.SunSka.repository.ProductRepository;
import com.akthon.SunSka.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        //On ne gère actuellement que l'année en cours
        Optional<Event> event = eventRepository.findById(Long.valueOf(1));
        if(event.isEmpty()){
            return null;
        }
        s.setEvent(event.get());


        Optional<Building> building = buildingRepository.findById(stock.buildingId);
        if(building.isEmpty()){
            return null;
        }
        s.setBuilding(building.get());

        Optional<Product> product = productRepository.findById(stock.productId);
        if(product.isEmpty()){
            return  null;
        }
        s.setProduct(product.get());

        s.setInitialStock(stock.stock);
        s.setCurrentStock(stock.stock);
        s.setWarningAlert(10);
        return stockRepository.save(s);
    }

    public List<Stock> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stockRepository.findAll();
    }

    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id);
    }

    public List<StockResponseDTO> findStockForBuildingByYear(int year, long buildingId) {
        return stockRepository.findStockForBuildingByYear(year, buildingId);
    }

    public List<Long> findStockInAlertForYear(int year) {
        return stockRepository.findStockInAlertForYear(year);
    }

    public List<Long> findStockInAlertForBarAndYear(Long idBar, int year) {
        return stockRepository.findStockInAlertForBarAndYear(idBar, year);
    }

    public ShopResponseDTO findShopByYear(int year) {
        return stockRepository.findShopByYear(year);
    }

    public ResponseEntity<Stock> updateAlert(Long id, int alert) {
        Optional<Stock> stock = stockRepository.findById(id);
        if (stock.isPresent()) {
            Stock s = stock.get();
            s.setWarningAlert(alert);
            return ResponseEntity.ok(stockRepository.save(s));
        }
        return ResponseEntity.notFound().build();
    }

    public List<StockAlertDTO> findStockForBuildingByYearAndAlerts(int year, Long buildingId) {
        //Il n'y a qu'1 magasin donc on le récup
        Building magasin = buildingRepository.findByType(Building.BuildingType.SHOP).get(0);

        Optional<Building> building = buildingRepository.findById(buildingId);
        if (building.isEmpty()) {
            return null;
        }

        List<StockAlertDTO> stockAlerts = new java.util.ArrayList<>(List.of());

        Set<Stock> stockBar = building.get().getStocks();
        for (Stock stock : stockBar) {
            if(stock.getEvent().getYear() == year) {
                StockAlertDTO stockAlertDTO = new StockAlertDTO();
                stockAlertDTO.id = stock.getId();
                stockAlertDTO.productName = stock.getProduct().getName();
                stockAlertDTO.productCapacity = stock.getProduct().getCapacity();
                stockAlertDTO.productUnit = stock.getProduct().getUnit();
                stockAlertDTO.stockBar = stock.getCurrentStock();
                if(stock.getCurrentStock() <= stock.getWarningAlert()) {
                    stockAlertDTO.isAlert = true;
                } else {
                    stockAlertDTO.isAlert = false;
                }

                magasin.getStocks().stream()
                        .filter(stockMagasin ->
                                stockMagasin.getProduct().getId().equals(stock.getProduct().getId()) &&
                                        stockMagasin.getEvent().getYear() == year
                        )
                        .forEach(stockMagasin -> {
                            stockAlertDTO.stockMagasin = stockMagasin.getCurrentStock();
                        });

                stockAlerts.add(stockAlertDTO);
            }
        }

        return stockAlerts;





    }

//    public List<ProductStockDTO> getBarShopStocks(Long idBar) {
//        List<ProductStockDTO> result = new ArrayList<>();
//
//        // Get all products
//        List<Product> products = productService.getAllProducts();
//
//        // Loop through each product
//        for (Product product : products) {
//            ProductStockDTO productStockDTO = new ProductStockDTO();
//            productStockDTO.setProduct(product);
//
//            // Get stock in bars for the current product
//            List<Stock> barStocks = stockService.getStocksByProductIdAndBuildingType(product.getId(), "bar");
//            int totalBarStock = barStocks.stream().mapToInt(Stock::getCurrentStock).sum();
//            productStockDTO.setBarStock(totalBarStock);
//
//            // Get stock in shop for the current product
//            Stock shopStock = stockService.getStockByProductIdAndBuildingId(product.getId(), 1); // Assuming 1 is the shop id
//            productStockDTO.setShopStock(shopStock != null ? shopStock.getCurrentStock() : 0);
//
//            result.add(productStockDTO);
//        }
//
//        return ResponseEntity.ok(result);
//    }
}
