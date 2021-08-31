package com.clockshop.service.controllers;

import com.clockshop.service.entity.Order;
import com.clockshop.service.repository.OrderJpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ShowAllOrders {
    private OrderJpaRepository orderJpaRepository;

    public ShowAllOrders(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    //ip вдрес - комп
    //port - приложение
    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return orderJpaRepository.findAll();
    }
}
