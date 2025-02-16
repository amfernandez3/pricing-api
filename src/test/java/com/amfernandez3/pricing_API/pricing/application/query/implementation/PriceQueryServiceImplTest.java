package com.amfernandez3.pricing_API.pricing.application.query.implementation;

import com.amfernandez3.pricing_API.pricing.application.dto.PriceResponse;
import com.amfernandez3.pricing_API.pricing.domain.model.Price;
import com.amfernandez3.pricing_API.pricing.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceQueryServiceImplTest {
    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceQueryServiceImpl priceQueryService;

    @Test
    public void testGetPrice() {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        int productId = 35455;
        int brandId = 1;

        Price price = new Price(1L, brandId, LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 1, productId, 0, 35.50, "EUR");

        when(priceRepository.findPrices(date, productId, brandId))
                .thenReturn(List.of(price));


        Optional<PriceResponse> result = priceQueryService.getPrice(date, productId, brandId);


        assertTrue(result.isPresent());
        assertEquals(productId, result.get().getProductId());
        assertEquals(brandId, result.get().getBrandId());
        assertEquals(35.50, result.get().getPrice().doubleValue());
    }
}