// En el servicio (PriceQueryServiceImpl.java)
package com.amfernandez3.pricing_API.pricing.application.query.implementation;

import com.amfernandez3.pricing_API.pricing.application.dto.PriceResponse;
import com.amfernandez3.pricing_API.pricing.application.query.PriceQueryService;
import com.amfernandez3.pricing_API.pricing.domain.model.Price;
import com.amfernandez3.pricing_API.pricing.domain.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceQueryServiceImpl implements PriceQueryService {
    private final PriceRepository priceRepository;

    public PriceQueryServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<PriceResponse> getPrices(LocalDateTime date, int productId, int brandId) {
        List<Price> prices = priceRepository.findPrices(date, productId, brandId);
        return prices.stream()
                .map(price -> new PriceResponse(
                        price.getProductId(),
                        price.getBrandId(),
                        price.getPriceList(),
                        price.getStartDate(),
                        price.getEndDate(),
                        BigDecimal.valueOf(price.getPrice()),
                        price.getCurrency()))
                .collect(Collectors.toList());
    }
}