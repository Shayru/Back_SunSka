package com.akthon.SunSka.controller;

import com.akthon.SunSka.model.Partner;
import com.akthon.SunSka.model.ProductCategory;
import com.akthon.SunSka.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partners")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping
    public List<Partner> getAllPartners() {
        return partnerService.getAllPartners();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partner> getPartnerById(@PathVariable Long id) {
        Optional<Partner> partner = partnerService.getPartnerById(id);
        return partner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public Partner createPartner(@RequestBody Partner partner) {
        return partnerService.createPartner(partner);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Partner> updatePartner(@PathVariable Long id, @RequestBody Partner partner) {
        Optional<Partner> updatedPartner = partnerService.updatePartner(id, partner);
        return updatedPartner.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        boolean isDeleted = partnerService.deletePartner(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
