// En infraestructura (PricingController.java)
package com.amfernandez3.pricing_API.pricing.infraestructure.controller;

import com.amfernandez3.pricing_API.pricing.application.dto.PriceResponse;
import com.amfernandez3.pricing_API.pricing.application.query.PriceQueryService;
import com.amfernandez3.pricing_API.pricing.domain.exception.PriceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PricingController {
    private static final Logger logger = LoggerFactory.getLogger(PricingController.class);

    private final PriceQueryService priceQueryService;

    public PricingController(PriceQueryService priceQueryService) {
        this.priceQueryService = priceQueryService;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam @NotNull LocalDateTime date,
            @RequestParam @NotNull int productId,
            @RequestParam @NotNull int brandId) {
        logger.info("Received request to get price with productId: {}, brandId: {}, at date: {}", productId, brandId, date);
        return priceQueryService.getPrice(date, productId, brandId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }
}