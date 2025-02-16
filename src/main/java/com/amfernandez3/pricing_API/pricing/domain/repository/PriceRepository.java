package com.amfernandez3.pricing_API.pricing.domain.repository;

import com.amfernandez3.pricing_API.pricing.domain.model.Price;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface PriceRepository {
    List<Price> findPrices(LocalDateTime date, int productId, int brandId);
}
