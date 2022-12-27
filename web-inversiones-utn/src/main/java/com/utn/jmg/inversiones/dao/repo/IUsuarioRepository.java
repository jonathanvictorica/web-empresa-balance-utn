package com.utn.jmg.inversiones.dao.repo;

import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {


    UsuarioEntity findTop1ByNick(String nickUsuario);
}
