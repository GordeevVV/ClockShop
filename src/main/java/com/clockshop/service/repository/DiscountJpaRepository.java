package com.clockshop.service.repository;

import com.clockshop.service.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountJpaRepository extends JpaRepository<Discount,Integer> {
}
