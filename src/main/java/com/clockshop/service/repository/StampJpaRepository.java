package com.clockshop.service.repository;

import com.clockshop.service.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampJpaRepository extends JpaRepository<Stamp,Integer> {
}
