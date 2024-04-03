package com.armorbldgsolutions.products.controller.mvc;

import com.armorbldgsolutions.products.service.PartsSearchService;
import com.armorbldgsolutions.products.service.VendorsSearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartsDisplayController {

    private final PartsSearchService partsSearchService;

    public PartsDisplayController(PartsSearchService partsSearchService) {
        this.partsSearchService = partsSearchService;
    }

    @GetMapping("/app/parts")
    public String vendors(Model model) {
        model.addAttribute("parts", partsSearchService.findAllParts());

        return "parts";
    }
}
