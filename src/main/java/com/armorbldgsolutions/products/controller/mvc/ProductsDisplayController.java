package com.armorbldgsolutions.products.controller.mvc;

import com.armorbldgsolutions.products.service.ProductsSearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsDisplayController {

    private final ProductsSearchService productsSearchService;

    public ProductsDisplayController(ProductsSearchService productsSearchService) {
        this.productsSearchService = productsSearchService;
    }

    @GetMapping("/app/products")
    public String products(Model model) {
        model.addAttribute("products", productsSearchService.findAllProducts());
        
        return "products";
    }

    @GetMapping("/app")
    public String index(Model model) {

        return "index";
    }
}
