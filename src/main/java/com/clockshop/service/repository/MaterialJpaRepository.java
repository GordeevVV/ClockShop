package com.clockshop.service.repository;

import com.clockshop.service.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialJpaRepository extends JpaRepository< Material,Integer > {
}
