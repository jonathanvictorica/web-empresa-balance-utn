package com.utn.jmg.inversiones.service;

import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;
import com.utn.jmg.inversiones.exception.SeguridadException;

public interface IUsuarioService {

	void validarUsuario(String nickUsuario, String pass) throws SeguridadException;

	UsuarioEntity findByNick(String nick) throws SeguridadException;

}
