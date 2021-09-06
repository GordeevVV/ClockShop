package com.clockshop.service.repository;

import com.clockshop.service.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerJpaRepository extends JpaRepository<Manager,Integer> {
    Manager findByToken(String token);
    List<Manager> findAllByChatIdNotNull();
}
