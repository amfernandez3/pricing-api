package com.amfernandez3.pricing_API.pricing.application.query.implementation;

import com.amfernandez3.pricing_API.pricing.application.dto.PriceResponse;
import com.amfernandez3.pricing_API.pricing.application.query.PriceQueryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceQueryServiceImpl implements PriceQueryService {
    @Override
    public List<PriceResponse> getPrices(LocalDateTime date, int productId, int brandId) {
        return null;
    }
}
