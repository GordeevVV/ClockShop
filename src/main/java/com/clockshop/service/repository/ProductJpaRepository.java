package com.clockshop.service.repository;

import com.clockshop.service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<Product,Integer> {
    List<Product> findByMechId(int mechId);
    List<Product> findByMaterialId(int materialId);
    List<Product> findByStampId(int stampId);
}
