package com.utn.jmg.inversiones.dao.repo;

import com.utn.jmg.inversiones.dao.entity.IndicadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IIndicadorRepository extends JpaRepository<IndicadorEntity, Long> {


    IndicadorEntity findTop1ByNombreAndUsuario_nick(String nombre, String nickUsuario);

    List<IndicadorEntity> findAllByUsuario_nick(String nickUsuario);
}