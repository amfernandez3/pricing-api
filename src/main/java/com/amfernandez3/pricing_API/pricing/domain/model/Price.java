package com.amfernandez3.pricing_API.pricing.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {
    private String id;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal taxRate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Price(String id, BigDecimal price, BigDecimal discount, BigDecimal taxRate) {
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.taxRate = taxRate;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { //TODO: Valorar más adelante si retirar por integridad
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
