package com.armorbldgsolutions.products.service;

import com.armorbldgsolutions.products.domain.ProductDto;
import com.armorbldgsolutions.products.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductsSearchService {

    private final ProductsRepository productsRepository;

    public ProductsSearchService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Collection<ProductDto> findAllProducts() {

        return productsRepository.findAllProducts();
    }
}
