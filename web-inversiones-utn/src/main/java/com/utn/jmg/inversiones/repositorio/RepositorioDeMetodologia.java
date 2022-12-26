package com.utn.jmg.inversiones.repositorio;

import java.util.List;

import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Metodologia;
import com.utn.jmg.inversiones.dao.IMetodologiaDao;

public class RepositorioDeMetodologia implements IMetodologiaDao<Metodologia> {
	private IMetodologiaDao<Metodologia> metodologiaDao;

	public void guardar(Metodologia entity) throws DaoException {
		this.metodologiaDao.guardar(entity);

	}

	public void modificar(Metodologia entity) throws DaoException {
		this.metodologiaDao.modificar(entity);

	}

	public void eliminar(Metodologia entity) throws DaoException {
		this.metodologiaDao.eliminar(entity);

	}

	public IMetodologiaDao<Metodologia> getMetodologiaDao() {
		return metodologiaDao;
	}

	public void setMetodologiaDao(IMetodologiaDao<Metodologia> metodologiaDao) {
		this.metodologiaDao = metodologiaDao;
	}

	@Override
	public Metodologia buscarMetodologia(String nombreMetodologia, String nickUsuario) {
		return metodologiaDao.buscarMetodologia(nombreMetodologia,nickUsuario);
	}

	@Override
	public List<Metodologia> allByUsuario(String nickUsuario) {
		return metodologiaDao.allByUsuario(nickUsuario);
	}

}
