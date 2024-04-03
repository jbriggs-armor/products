package com.armorbldgsolutions.products.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PartDto(
        Long id,
        String nickname,
        String actualName,
        String partNumber,
        Long vendorId,
        BigDecimal cost,
        BigDecimal price,
        String unitOfMeasure,

        LocalDateTime createdDatetime,
        LocalDateTime modifiedDatetime
) {
}
