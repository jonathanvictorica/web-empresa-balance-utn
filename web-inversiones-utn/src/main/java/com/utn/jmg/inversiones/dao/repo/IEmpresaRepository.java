package com.utn.jmg.inversiones.dao.repo;

import com.utn.jmg.inversiones.dao.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresaRepository extends JpaRepository<EmpresaEntity, Long> {


    EmpresaEntity findTop1ByCuit(String cuit);

    EmpresaEntity findTop1ByRazonSocial(String razonSocial);
}