package com.amfernandez3.pricing_API.pricing.application.service.implementation;

import com.amfernandez3.pricing_API.pricing.application.dto.PriceResponse;
import com.amfernandez3.pricing_API.pricing.application.service.PricingGeneralService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PricingGeneralServiceImpl implements PricingGeneralService {
    @Override
    public ArrayList<PriceResponse> getPrices() {
        return null;
    }
}
