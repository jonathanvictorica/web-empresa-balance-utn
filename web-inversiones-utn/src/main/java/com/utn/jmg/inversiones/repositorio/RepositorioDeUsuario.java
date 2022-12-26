package com.utn.jmg.inversiones.repositorio;

import com.utn.jmg.inversiones.dao.IUsuarioDao;
import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;

public class RepositorioDeUsuario implements IUsuarioDao {
	private IUsuarioDao usuarioDao;

	public IUsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(IUsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Override
	public UsuarioEntity findByNick(String nickUsuario) {
		return usuarioDao.findByNick(nickUsuario);
	}

}
