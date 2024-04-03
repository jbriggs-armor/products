package com.armorbldgsolutions.products.repository;

import com.armorbldgsolutions.products.domain.PartDto;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PartsRepository {

    private final JdbcClient jdbcClient;

    public PartsRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    public Collection<PartDto> findAllParts() {

        return jdbcClient.sql("select * from parts")
                .query(PartDto.class)
                .set();
    }

    public Collection<PartDto> findAllPartsByIds(Collection<Long> ids) {

        return jdbcClient.sql("select * from parts where id in (:ids)")
                .params(Map.of("ids", ids))
                .query(PartDto.class)
                .set();
    }

    public Collection<PartDto> save(Collection<PartDto> parts) {
        var keysAdded = new HashSet<KeyHolder>();

        parts.forEach(part -> {
            if (Objects.nonNull(part)) {
                var keyHolder = new GeneratedKeyHolder();

                jdbcClient.sql("""
                                insert into parts
                                (nickname, actual_name, part_number, vendor_id, cost, price, unit_of_measure, created_datetime, modified_datetime)
                                values (:nickname, :actualName, :partNumber, :vendorId, :cost, :price, :unitOfMeasure, current_timestamp(), current_timestamp());
                            """)
                        .params(buildMap(part))
                        .update(keyHolder);

                keysAdded.add(keyHolder);
            }
        });
        return findAllPartsByIds(keysAdded.stream().map(kh -> Objects.requireNonNull(kh.getKey()).longValue()).collect(Collectors.toSet()));
    }

    private Map<String, ?> buildMap(PartDto part) {
        var partsParam = new HashMap<String, Object>();
        partsParam.put("nickname", part.nickname());
        partsParam.put("actualName", part.actualName());
        partsParam.put("partNumber", part.partNumber());
        partsParam.put("vendorId", part.vendorId());
        partsParam.put("cost", part.cost());
        partsParam.put("price", part.price());
        partsParam.put("unitOfMeasure", part.unitOfMeasure());

        return partsParam;
    }
}
