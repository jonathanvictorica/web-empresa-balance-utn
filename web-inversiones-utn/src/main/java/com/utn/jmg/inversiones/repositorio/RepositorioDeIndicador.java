package com.utn.jmg.inversiones.repositorio;

import java.util.List;

import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Indicador;
import com.utn.jmg.inversiones.dao.IIndicadorDao;

public class RepositorioDeIndicador implements IIndicadorDao<Indicador> {
	private IIndicadorDao<Indicador> indicadorDao;

	public void guardar(Indicador entity) throws DaoException {
		this.indicadorDao.guardar(entity);

	}

	public void modificar(Indicador entity) throws DaoException {
		this.indicadorDao.modificar(entity);

	}

	public void eliminar(Indicador entity) throws DaoException {
		this.indicadorDao.eliminar(entity);

	}

	public IIndicadorDao<Indicador> getIndicadorDao() {
		return indicadorDao;
	}

	public void setIndicadorDao(IIndicadorDao<Indicador> indicadorDao) {
		this.indicadorDao = indicadorDao;
	}

	@Override
	public Indicador buscarIndicador(String nombreIndicador, String nickUsuario) {

		return indicadorDao.buscarIndicador(nombreIndicador, nickUsuario);
	}

	@Override
	public List<Indicador> allByUser(String nickUsuario) {
		return indicadorDao.allByUser(nickUsuario);
	}



}
