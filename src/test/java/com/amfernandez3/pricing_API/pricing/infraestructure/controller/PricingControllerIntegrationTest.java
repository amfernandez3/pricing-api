package com.amfernandez3.pricing_API.pricing.infraestructure.controller;

import com.amfernandez3.pricing_API.pricing.infraestructure.entity.PriceEntity;
import com.amfernandez3.pricing_API.pricing.infraestructure.jpaRepository.SpringDataPriceRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Propagation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PricingControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpringDataPriceRepository priceRepository;

    @BeforeEach
    public void setUp() {
        priceRepository.deleteAll();

        priceRepository.save(new PriceEntity(1L, 1, LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 1, 35455, 0, BigDecimal.valueOf(35.50), "EUR"));
        priceRepository.save(new PriceEntity(2L, 1, LocalDateTime.parse("2020-06-14T15:00:00"),
                LocalDateTime.parse("2020-06-14T18:30:00"), 2, 35455, 1, BigDecimal.valueOf(25.45), "EUR"));
        priceRepository.save(new PriceEntity(3L, 1, LocalDateTime.parse("2020-06-15T00:00:00"),
                LocalDateTime.parse("2020-06-15T11:00:00"), 3, 35455, 1, BigDecimal.valueOf(30.50), "EUR"));
        priceRepository.save(new PriceEntity(4L, 1, LocalDateTime.parse("2020-06-15T16:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 4, 35455, 1, BigDecimal.valueOf(38.95), "EUR"));
    }

    @Order(1)
    @Transactional
    @Test
    public void testGetPriceAt10AMOnDay14() throws Exception {
        // Datos de prueba
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        int productId = 35455;
        int brandId = 1;

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

    @Order(2)
    @Transactional
    @Test
    public void testGetPriceAt4PMOnDay14() throws Exception {
        // Datos de prueba
        LocalDateTime date = LocalDateTime.parse("2020-06-14T16:00:00");
        int productId = 35455;
        int brandId = 1;

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

    @Order(3)
    @Transactional
    @Test
    public void testGetPriceAt9PMOnDay14() throws Exception {
        // Datos de prueba
        LocalDateTime date = LocalDateTime.parse("2020-06-14T21:00:00");
        int productId = 35455;
        int brandId = 1;

        // Ejecutar la solicitud y verificar la respuesta
        mockMvc.perform(get("/prices")
                        .param("date", date.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId)))
                .andExpect(status().isNotFound());
    }

    @Order(4)
    @Transactional
    @Test
    public void testGetPriceAt10AMOnDay15() throws Exception {
        // Datos de prueba
        LocalDateTime date = LocalDateTime.parse("2020-06-15T10:00:00");
        int productId = 35455;
        int brandId = 1;

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

    @Order(5)
    @Transactional
    @Test
    public void testGetPriceAt9PMOnDay16() throws Exception {
        // Datos de prueba
        LocalDateTime date = LocalDateTime.parse("2020-06-16T21:00:00");
        int productId = 35455;
        int brandId = 1;

        // Ejecutar la solicitud y verificar la respuesta
        mockMvc.perform(get("/prices")
                        .param("date", date.toString())
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId)))
                .andExpect(status().isNotFound());
    }
}