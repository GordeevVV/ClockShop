package com.clockshop.service.repository;

import com.clockshop.service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByStatusAndCustomerId(String status, long customerId);
    List<Order> findAllByStatusAndCustomerId(String status,long customerId);
    List<Order> findAllByStatus(String status);
}
