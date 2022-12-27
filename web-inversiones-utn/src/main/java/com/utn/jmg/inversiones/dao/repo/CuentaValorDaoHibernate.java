package com.utn.jmg.inversiones.dao.repo;

import com.utn.jmg.inversiones.dao.entity.CuentaValorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaValorDaoHibernate extends JpaRepository<CuentaValorEntity, Long>  {






}
