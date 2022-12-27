package com.utn.jmg.inversiones.dao.repo;

import com.utn.jmg.inversiones.dao.entity.CondicionesTaxativasRelacionadasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondicionTaxativaRelacionadaDaoHibernate extends JpaRepository<CondicionesTaxativasRelacionadasEntity, Long> {


}
