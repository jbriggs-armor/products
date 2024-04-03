package com.armorbldgsolutions.products.domain;

import java.time.LocalDateTime;

public record ProductDto (Long id, String name, LocalDateTime createdDatetime){
}
