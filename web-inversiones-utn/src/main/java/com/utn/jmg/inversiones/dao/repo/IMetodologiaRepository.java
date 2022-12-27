package com.utn.jmg.inversiones.dao.repo;

import com.utn.jmg.inversiones.dao.entity.MetodologiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMetodologiaRepository extends JpaRepository<MetodologiaEntity, Long> {


    MetodologiaEntity findTop1ByNombreAndUsuario_nick(String nombreMetodologia, String nickUsuario);

    List<MetodologiaEntity> findAllByUsuario_nick(String nickUsuario);
}
