package com.utn.jmg.inversiones.service.impl;

import com.utn.jmg.inversiones.dao.ICondicionTaxativaDao;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;
import com.utn.jmg.inversiones.service.ICondicionTaxativaService;
import com.utn.jmg.inversiones.exception.DaoException;

public class CondicionTaxativaService implements ICondicionTaxativaService {
	private ICondicionTaxativaDao condicionTaxativaDao;

	public ICondicionTaxativaDao getCondicionTaxativaDao() {
		return condicionTaxativaDao;
	}

	public void setCondicionTaxativaDao(ICondicionTaxativaDao condicionTaxativaDao) {
		this.condicionTaxativaDao = condicionTaxativaDao;
	}

	@Override
	public CondicionTaxativa findByDescripcionByMetodologia(String nombreCondicion, Long idMetodologia) {
	
		return condicionTaxativaDao.findByDescripcionByMetodologia(nombreCondicion, idMetodologia);
	}

	@Override
	public void guardar(CondicionTaxativa entity) throws DaoException {
		condicionTaxativaDao.guardar(entity);
		
	}

	@Override
	public void modificar(CondicionTaxativa entity) throws DaoException{
		condicionTaxativaDao.modificar(entity);
		
	}

	@Override
	public void eliminar(CondicionTaxativa entity) throws DaoException{
		condicionTaxativaDao.eliminar(entity);
		
	}

}
