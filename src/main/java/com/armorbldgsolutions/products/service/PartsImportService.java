package com.armorbldgsolutions.products.service;

import com.armorbldgsolutions.products.domain.PartDto;
import com.armorbldgsolutions.products.domain.VendorDto;
import com.armorbldgsolutions.products.repository.PartsRepository;
import com.armorbldgsolutions.products.repository.VendorsRepository;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

@Service
public class PartsImportService {

    private final PartsRepository partsRepository;
    private final VendorsRepository vendorsRepository;

    public PartsImportService(PartsRepository partsRepository, VendorsRepository vendorsRepository) {
        this.partsRepository = partsRepository;
        this.vendorsRepository = vendorsRepository;
    }

    // This is currently built for an initial load only - not an upsert
    public Collection<PartDto> importPartsFromWorkbook() {
        // We should inject the workbook or inputstream here but for now will get it from static file
        try (var input = new ClassPathResource("/data/SALES-CALC-11292023.xlsm").getInputStream()) {
            var partsSheet = new XSSFWorkbook(input)
                    .getSheet("Parts");
            var dataFormatter = new DataFormatter();
            // part range from row 4 - 164
            var partsToSave = new HashSet<PartDto>();
            var vendorsFromPersistence = vendorsRepository.findAllVendors();
            for (var i = 3; i <= 163; i++) {
                var row = partsSheet.getRow(i);
                // A = 'nickname'
                var nickname = row.getCell(0);
                // B = 'actual name'
                var actualName = row.getCell(1);
                // C = 'part number'
                var partNumber = row.getCell(2);
                // D = 'vendor'
                var vendor = row.getCell(3);
                // H = 'cost'
                var cost = row.getCell(7);
                // I = 'price'
                var price = row.getCell(8);
                // N = 'uom'
                var unitOfMeasure = row.getCell(13);

                partsToSave.add(
                        new PartDto(
                                null,
                                Optional.ofNullable(nickname).map(dataFormatter::formatCellValue).orElse(null),
                                Optional.ofNullable(actualName).map(dataFormatter::formatCellValue).orElse(null),
                                Optional.ofNullable(partNumber).map(dataFormatter::formatCellValue).orElse(null),
                                Objects.isNull(vendor) ? null :
                                        vendorsFromPersistence.stream().filter(v -> v.name().equals(dataFormatter.formatCellValue(vendor))).findFirst()
                                                .map(VendorDto::id)
                                                .orElse(null),
                                Optional.ofNullable(cost).map(XSSFCell::getNumericCellValue).map(BigDecimal::valueOf).map(bd -> bd.setScale(3, RoundingMode.HALF_UP)).orElse(null),
                                Optional.ofNullable(price).map(XSSFCell::getNumericCellValue).map(BigDecimal::valueOf).map(bd -> bd.setScale(3, RoundingMode.HALF_UP)).orElse(null),
                                Optional.ofNullable(unitOfMeasure).map(dataFormatter::formatCellValue).orElse(null),
                                null,
                                null
                        )
                );
            }

            return partsRepository.save(partsToSave);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
