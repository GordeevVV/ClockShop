package com.clockshop.service.repository;

import com.clockshop.service.entity.Material;
import org.springframework.data.repository.CrudRepository;

public interface MaterialJpaRepository extends CrudRepository< Material,Integer > {

}
