package com.amfernandez3.pricing_API.pricing.infraestructure.jpaRepository;

import com.amfernandez3.pricing_API.pricing.domain.model.Price;
import com.amfernandez3.pricing_API.pricing.domain.repository.PriceRepository;
import com.amfernandez3.pricing_API.pricing.infraestructure.entity.PriceEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PriceJpaRepository implements PriceRepository {
    private final SpringDataPriceRepository springDataPriceRepository;


    public PriceJpaRepository(SpringDataPriceRepository springDataPriceRepository) {
        this.springDataPriceRepository = springDataPriceRepository;
    }

    @Override
    public List<Price> findPrices(LocalDateTime date, int productId, int brandId) {
        List<PriceEntity> entities = springDataPriceRepository //TODO: valorar el uso de una query al terminar funcionalidadad base
                .findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        brandId, productId, date, date);
        return entities.stream()
                .map(PriceEntity::toDomain)
                .collect(Collectors.toList());
    }
}