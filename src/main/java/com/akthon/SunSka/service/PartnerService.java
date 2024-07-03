package com.akthon.SunSka.service;

import com.akthon.SunSka.model.Partner;
import com.akthon.SunSka.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    public List<Partner> getAllPartners() {
        return partnerRepository.findAll();
    }

    public Optional<Partner> getPartnerById(Long id) {
        return partnerRepository.findById(id);
    }

    public Partner createPartner(Partner partner) {
        return partnerRepository.save(partner);
    }

    public Optional<Partner> updatePartner(Long id, Partner partner) {
        return partnerRepository.findById(id).map(existingPartner -> {
            existingPartner.setName(partner.getName());
            return partnerRepository.save(existingPartner);
        });
    }

    public boolean deletePartner(Long id) {
        return partnerRepository.findById(id).map(partner -> {
            partnerRepository.delete(partner);
            return true;
        }).orElse(false);
    }
}
