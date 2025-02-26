
package com.amfernandez3.pricing_API.pricing.infraestructure.controller;

import com.amfernandez3.pricing_API.pricing.application.dto.PriceResponse;
import com.amfernandez3.pricing_API.pricing.application.query.PriceQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PricingController.class)
public class PricingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PriceQueryService priceQueryService;

    @Test
    public void testGetPrice() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        int productId = 35455;
        int brandId = 1;

        PriceResponse priceResponse = new PriceResponse(
                productId, brandId, 1, date, date.plusDays(1), BigDecimal.valueOf(35.50), "EUR");

        when(priceQueryService.getPrice(date, productId, brandId))
                .thenReturn(Optional.of(priceResponse));

        mockMvc.perform(get("/prices")
                        .param("date", date.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void testGetPriceNotFound() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        int productId = 35455;
        int brandId = 1;

        when(priceQueryService.getPrice(date, productId, brandId))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/prices")
                        .param("date", date.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId)))
                .andExpect(status().isNotFound());
    }

    //tests solicitados en el enunciado para diferentes casuísticas de entrada:

    @Test
    public void testGetPriceAt10AMOnDay14() throws Exception {
        // Datos de prueba
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        int productId = 35455;
        int brandId = 1;

        PriceResponse priceResponse = new PriceResponse(
                productId, brandId, 1, date, LocalDateTime.parse("2020-12-31T23:59:59"), BigDecimal.valueOf(35.50), "EUR");

        when(priceQueryService.getPrice(date, productId, brandId))
                .thenReturn(Optional.of(priceResponse));

        // Ejecutar la solicitud y verificar la respuesta
        mockMvc.perform(get("/prices")
                        .param("date", date.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testGetPriceAt4PMOnDay14() throws Exception {
        // Datos de prueba
        LocalDateTime date = LocalDateTime.parse("2020-06-14T16:00:00");
        int productId = 35455;
        int brandId = 1;

        PriceResponse priceResponse = new PriceResponse(
                productId, brandId, 2, date, LocalDateTime.parse("2020-06-14T18:30:00"), BigDecimal.valueOf(25.45), "EUR");

        when(priceQueryService.getPrice(date, productId, brandId))
                .thenReturn(Optional.of(priceResponse));

        // Ejecutar la solicitud y verificar la respuesta
        mockMvc.perform(get("/prices")
                        .param("date", date.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testGetPriceAt9PMOnDay14() throws Exception {
        // Datos de prueba
        LocalDateTime date = LocalDateTime.parse("2020-06-14T21:00:00");
        int productId = 35455;
        int brandId = 1;

        when(priceQueryService.getPrice(date, productId, brandId))
                .thenReturn(Optional.empty());

        // Ejecutar la solicitud y verificar la respuesta
        mockMvc.perform(get("/prices")
                        .param("date", date.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetPriceAt10AMOnDay15() throws Exception {
        // Datos de prueba
        LocalDateTime date = LocalDateTime.parse("2020-06-15T10:00:00");
        int productId = 35455;
        int brandId = 1;

        PriceResponse priceResponse = new PriceResponse(
                productId, brandId, 3, date, LocalDateTime.parse("2020-06-15T11:00:00"), BigDecimal.valueOf(30.50), "EUR");

        when(priceQueryService.getPrice(date, productId, brandId))
                .thenReturn(Optional.of(priceResponse));

        // Ejecutar la solicitud y verificar la respuesta
        mockMvc.perform(get("/prices")
                        .param("date", date.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    public void testGetPriceAt9PMOnDay16() throws Exception {
        // Datos de prueba
        LocalDateTime date = LocalDateTime.parse("2020-06-16T21:00:00");
        int productId = 35455;
        int brandId = 1;

        when(priceQueryService.getPrice(date, productId, brandId))
                .thenReturn(Optional.empty());

        // Ejecutar la solicitud y verificar la respuesta
        mockMvc.perform(get("/prices")
                        .param("date", date.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId)))
                .andExpect(status().isNotFound());
    }
}