package com.clockshop.service.repository;

import com.clockshop.service.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogJpaRepository extends JpaRepository<Log,Long> {
}
