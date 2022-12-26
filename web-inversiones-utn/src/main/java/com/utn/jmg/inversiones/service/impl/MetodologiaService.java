package com.utn.jmg.inversiones.service.impl;

import java.util.List;

import com.utn.jmg.inversiones.model.Metodologia;
import com.utn.jmg.inversiones.service.IMetodologiaService;
import com.utn.jmg.inversiones.dao.IMetodologiaDao;
import com.utn.jmg.inversiones.exception.DaoException;

public class MetodologiaService implements IMetodologiaService {
	private IMetodologiaDao<Metodologia> metodologiaDao;

	public IMetodologiaDao<Metodologia> getMetodologiaDao() {
		return metodologiaDao;
	}

	public void setMetodologiaDao(IMetodologiaDao<Metodologia> metodologiaDao) {
		this.metodologiaDao = metodologiaDao;
	}

	@Override
	public void guardar(Metodologia metodologia) throws DaoException {
		metodologiaDao.guardar(metodologia);

	}

	@Override
	public Metodologia buscarMetodologiaPorNombre(String nombreMetodologia, String nickUsuario) {

		return metodologiaDao.buscarMetodologia(nombreMetodologia,nickUsuario);
	}

	@Override
	public void eliminar(Metodologia metodologia) throws DaoException {
		metodologiaDao.eliminar(metodologia);

	}

	@Override
	public List<Metodologia> allByUser(String nick) {
		return metodologiaDao.allByUsuario(nick);
	}

}
