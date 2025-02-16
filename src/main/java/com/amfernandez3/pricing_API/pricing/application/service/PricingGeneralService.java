package com.amfernandez3.pricing_API.pricing.application.service;

import com.amfernandez3.pricing_API.pricing.application.dto.PriceResponse;

import java.util.ArrayList;

public interface PricingGeneralService {
    ArrayList<PriceResponse> getPrices();
}
