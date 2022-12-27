package com.utn.jmg.inversiones.dao.repo;

import com.utn.jmg.inversiones.dao.entity.CondicionTaxativaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICondicionTaxativaRepository extends JpaRepository<CondicionTaxativaEntity, Long> {

    CondicionTaxativaEntity findTop1ByNombreCondicionAndMetodologia_idMetodologia(String nombreCondicion, Long idMetodologia);
}