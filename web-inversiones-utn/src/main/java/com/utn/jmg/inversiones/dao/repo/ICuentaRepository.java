package com.utn.jmg.inversiones.dao.repo;

import com.utn.jmg.inversiones.dao.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICuentaRepository extends JpaRepository<CuentaEntity, Long> {


    CuentaEntity findTop1ByNombre(String nombre);

    List<CuentaEntity> findAll();
}
