package com.amfernandez3.pricing_API.pricing.infraestructure.controller;

import com.amfernandez3.pricing_API.pricing.application.dto.PriceResponse;
import com.amfernandez3.pricing_API.pricing.application.query.PriceQueryService;
import com.amfernandez3.pricing_API.pricing.application.service.PricingGeneralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/prices")
public class PricingController {
    private static final Logger logger = LoggerFactory.getLogger(PricingController.class);

    private PricingGeneralService priceService;
    private PriceQueryService priceQueryService;

    public PricingController(PricingGeneralService priceService, PriceQueryService priceQueryService) {
        this.priceService = priceService;
        this.priceQueryService = priceQueryService;
    }

    @GetMapping
    public ResponseEntity<List<PriceResponse>> getPrices(@RequestParam LocalDateTime date, @RequestParam int productId, @RequestParam int brandId) {
        logger.info("Received request to get prices with informed productId: {}, brandId: {}, at date: {}", productId, brandId, date);
        // Recupera la lista de precios desde el QueryService
        List<PriceResponse> priceResponses = priceQueryService.getPrices(date, productId, brandId);

        // Devuelve la lista de precios como respuesta
        return ResponseEntity.ok(priceResponses);
    }
}
