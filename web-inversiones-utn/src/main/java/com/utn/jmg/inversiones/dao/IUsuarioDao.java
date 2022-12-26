package com.utn.jmg.inversiones.dao;

import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;

public interface IUsuarioDao {

	UsuarioEntity findByNick(String nickUsuario);
	
}
