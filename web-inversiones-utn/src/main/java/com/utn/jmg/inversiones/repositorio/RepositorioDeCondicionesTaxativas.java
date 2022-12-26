package com.utn.jmg.inversiones.repositorio;

import com.utn.jmg.inversiones.dao.ICondicionTaxativaDao;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;

public class RepositorioDeCondicionesTaxativas implements ICondicionTaxativaDao {

	private ICondicionTaxativaDao condicionTaxativaDao;

	@Override
	public void guardar(CondicionTaxativa entity) throws DaoException {
		condicionTaxativaDao.guardar(entity);

	}

	@Override
	public void modificar(CondicionTaxativa entity) throws DaoException {
		condicionTaxativaDao.modificar(entity);

	}

	@Override
	public void eliminar(CondicionTaxativa entity) throws DaoException {
		condicionTaxativaDao.eliminar(entity);

	}

	@Override
	public CondicionTaxativa findByDescripcionByMetodologia(String nombreCondicion, Long idMetodologia) {
		
		return condicionTaxativaDao.findByDescripcionByMetodologia(nombreCondicion, idMetodologia);
	}

	public ICondicionTaxativaDao getCondicionTaxativaDao() {
		return condicionTaxativaDao;
	}

	public void setCondicionTaxativaDao(ICondicionTaxativaDao condicionTaxativaDao) {
		this.condicionTaxativaDao = condicionTaxativaDao;
	}

	
	

}
