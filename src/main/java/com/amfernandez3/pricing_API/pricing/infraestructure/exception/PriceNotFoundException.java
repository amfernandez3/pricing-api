package com.amfernandez3.pricing_API.pricing.infraestructure.exception;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(String message) {
        super(message);
    }
}