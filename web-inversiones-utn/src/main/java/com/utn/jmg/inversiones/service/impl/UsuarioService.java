package com.utn.jmg.inversiones.service.impl;

import com.utn.jmg.inversiones.dao.IUsuarioDao;
import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;
import com.utn.jmg.inversiones.service.IUsuarioService;
import com.utn.jmg.inversiones.exception.SeguridadException;

public class UsuarioService implements IUsuarioService {
	private IUsuarioDao usuarioDao;

	public IUsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(IUsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Override
	public void validarUsuario(String nickUsuario, String pass) throws SeguridadException {
		UsuarioEntity usuario = findByNick(nickUsuario);
		if (!usuario.validarContrasenia(pass)) {
			throw new SeguridadException("La contrase�a no es valida.");
		}

	}

	@Override
	public UsuarioEntity findByNick(String nick) throws SeguridadException {
		UsuarioEntity usuario = usuarioDao.findByNick(nick);
		if (usuario == null) {
			throw new SeguridadException("No existe un usuario con ese nick.");
		}
		return usuario;
	}

}
