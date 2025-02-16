package com.amfernandez3.pricing_API.pricing.infraestructure.jpaRepository;

import com.amfernandez3.pricing_API.pricing.infraestructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {
}
