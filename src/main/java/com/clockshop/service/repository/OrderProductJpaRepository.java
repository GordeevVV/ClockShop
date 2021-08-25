package com.clockshop.service.repository;

import com.clockshop.service.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductJpaRepository extends JpaRepository<OrderProduct,Integer> {
    List<OrderProduct> findAllByOrderId(int orderId);
}
