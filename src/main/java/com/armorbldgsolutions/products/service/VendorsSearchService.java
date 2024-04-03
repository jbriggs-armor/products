package com.armorbldgsolutions.products.service;

import com.armorbldgsolutions.products.domain.VendorDto;
import com.armorbldgsolutions.products.repository.VendorsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VendorsSearchService {

    private final VendorsRepository vendorsRepository;

    public VendorsSearchService(VendorsRepository vendorsRepository) {
        this.vendorsRepository = vendorsRepository;
    }

    public Collection<VendorDto> findAllVendors() {

        return vendorsRepository.findAllVendors();
    }
}
