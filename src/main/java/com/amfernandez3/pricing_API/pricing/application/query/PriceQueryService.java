package com.amfernandez3.pricing_API.pricing.application.query;

import com.amfernandez3.pricing_API.pricing.application.dto.PriceResponse;
import com.amfernandez3.pricing_API.pricing.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceQueryService {
    Optional<PriceResponse> getPrice(LocalDateTime date, int productId, int brandId);
}
