package com.utn.jmg.inversiones.service.impl;

import com.utn.jmg.inversiones.dao.ICondicionPriorizableDao;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;
import com.utn.jmg.inversiones.service.ICondicionPriorizableService;
import com.utn.jmg.inversiones.exception.DaoException;

public class CondicionPriorizableService implements ICondicionPriorizableService {
	private ICondicionPriorizableDao condicionPriorizableDao;

	public ICondicionPriorizableDao getCondicionPriorizableDao() {
		return condicionPriorizableDao;
	}

	public void setCondicionPriorizableDao(ICondicionPriorizableDao condicionPriorizableDao) {
		this.condicionPriorizableDao = condicionPriorizableDao;
	}

	
	@Override
	public void guardar(CondicionPriorizable entity) throws DaoException {
		condicionPriorizableDao.guardar(entity);

	}

	@Override
	public void modificar(CondicionPriorizable entity) throws DaoException {
		condicionPriorizableDao.modificar(entity);

	}

	@Override
	public void eliminar(CondicionPriorizable entity) throws DaoException {
		condicionPriorizableDao.eliminar(entity);

	}

	@Override
	public CondicionPriorizable findByDescripcionByMetodologia(String nombreCondicion, Long id) {
		
		return condicionPriorizableDao.findByDescripcionByMetodologia(nombreCondicion, id);
	}

}
