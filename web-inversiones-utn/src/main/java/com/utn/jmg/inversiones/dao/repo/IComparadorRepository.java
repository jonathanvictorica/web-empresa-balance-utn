package com.utn.jmg.inversiones.dao.repo;

import com.utn.jmg.inversiones.dao.entity.ComparadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComparadorRepository extends JpaRepository<ComparadorEntity, Long> {

    ComparadorEntity findTop1ByDescripcion(String descripcion);
}