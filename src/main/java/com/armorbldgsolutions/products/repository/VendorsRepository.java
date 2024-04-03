package com.armorbldgsolutions.products.repository;

import com.armorbldgsolutions.products.domain.VendorDto;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;

@Repository
public class VendorsRepository {

    private final JdbcClient jdbcClient;

    public VendorsRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    public Collection<VendorDto> findAllVendors() {

        return jdbcClient.sql("select * from vendors")
                .query(VendorDto.class)
                .set();
    }
}
