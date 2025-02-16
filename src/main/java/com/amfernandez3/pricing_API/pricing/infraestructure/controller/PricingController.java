package com.amfernandez3.pricing_API.pricing.infraestructure.controller;

import com.amfernandez3.pricing_API.pricing.application.dto.PriceResponse;
import com.amfernandez3.pricing_API.pricing.application.query.PriceQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

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
            @RequestParam LocalDateTime date,
            @RequestParam int productId,
            @RequestParam int brandId) {
        logger.info("Received request to get price with productId: {}, brandId: {}, at date: {}", productId, brandId, date);
        Optional<PriceResponse> priceResponse = priceQueryService.getPrice(date, productId, brandId);
        return priceResponse
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}