package com.armorbldgsolutions.products.controller.mvc;

import com.armorbldgsolutions.products.service.VendorsSearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VendorsDisplayController {

    private final VendorsSearchService vendorsSearchService;

    public VendorsDisplayController(VendorsSearchService vendorsSearchService) {
        this.vendorsSearchService = vendorsSearchService;
    }

    @GetMapping("/app/vendors")
    public String vendors(Model model) {
        model.addAttribute("vendors", vendorsSearchService.findAllVendors());

        return "vendors";
    }
}
