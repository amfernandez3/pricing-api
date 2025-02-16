package com.amfernandez3.pricing_API.pricing.application.query;

import com.amfernandez3.pricing_API.pricing.application.dto.PriceResponse;
import com.amfernandez3.pricing_API.pricing.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceQueryService {
    List<PriceResponse> getPrices(LocalDateTime date, int productId, int brandId);
}
