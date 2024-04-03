package com.armorbldgsolutions.products.repository;

import com.armorbldgsolutions.products.domain.ProductDto;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;

@Repository
public class ProductsRepository {

    private final JdbcClient jdbcClient;

    public ProductsRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    public Collection<ProductDto> findAllProducts() {

        return jdbcClient.sql("select * from products")
                .query(ProductDto.class)
                .set();
    }
}
