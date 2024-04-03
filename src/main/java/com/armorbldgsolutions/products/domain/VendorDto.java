package com.armorbldgsolutions.products.domain;

import java.time.LocalDateTime;

public record VendorDto(Long id, String name, LocalDateTime createdDatetime, LocalDateTime modifiedDatetime) {
}
