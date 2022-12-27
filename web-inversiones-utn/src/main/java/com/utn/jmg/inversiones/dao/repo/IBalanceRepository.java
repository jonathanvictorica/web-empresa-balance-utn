package com.utn.jmg.inversiones.dao.repo;

import com.utn.jmg.inversiones.dao.entity.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IBalanceRepository extends JpaRepository<BalanceEntity, Long> {

    List<BalanceEntity> findAllByEmpresa_cuit(String cuit);

    BalanceEntity findTop1ByEmpresa_cuitAndFechaComienzoAndFechaCierre(String cuit, Date fechaComienzo, Date fechaFin);
}