package com.armorbldgsolutions.products.controller.rest;

import com.armorbldgsolutions.products.domain.ProductDto;
import com.armorbldgsolutions.products.service.ProductsSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ProductsSearchRestController {

   private final ProductsSearchService productsSearchService;

    public ProductsSearchRestController(ProductsSearchService productsSearchService) {
        this.productsSearchService = productsSearchService;
    }

    @GetMapping("/api/products")
    public Collection<ProductDto> findAllProducts() {

        return productsSearchService.findAllProducts();
    }
}
