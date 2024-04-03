package com.armorbldgsolutions.products;

import com.armorbldgsolutions.products.service.PartsImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}

	@Bean
	CommandLineRunner loadData(PartsImportService partsImportService) {
		return args -> {
			partsImportService.importPartsFromWorkbook();
		};
	}
}
