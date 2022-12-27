package com.utn.jmg.inversiones.service;

import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;
import com.utn.jmg.inversiones.dao.impl.UsuarioDaoHibernate;
import com.utn.jmg.inversiones.exception.SeguridadException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	private final UsuarioDaoHibernate usuarioDao;

	public UsuarioService(UsuarioDaoHibernate usuarioDao) {
		this.usuarioDao = usuarioDao;
	}


	public void validarUsuario(String nickUsuario, String pass) throws SeguridadException {
		UsuarioEntity usuario = findByNick(nickUsuario);
		if (!usuario.validarContrasenia(pass)) {
			throw new SeguridadException("La contrase�a no es valida.");
		}

	}


	public UsuarioEntity findByNick(String nick) throws SeguridadException {
		UsuarioEntity usuario = usuarioDao.findByNick(nick);
		if (usuario == null) {
			throw new SeguridadException("No existe un usuario con ese nick.");
		}
		return usuario;
	}

}
