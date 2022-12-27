package com.utn.jmg.inversiones.dao.repo;

import com.utn.jmg.inversiones.dao.entity.CondicionPriorizableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICondicionPriorizableRepository extends JpaRepository<CondicionPriorizableEntity, Long> {

}