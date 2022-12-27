package com.utn.jmg.inversiones.service;

import com.utn.jmg.inversiones.dao.impl.CondicionPriorizableDaoHibernate;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;
import com.utn.jmg.inversiones.exception.DaoException;
import org.springframework.stereotype.Service;

@Service
public class CondicionPriorizableService  {
	private final CondicionPriorizableDaoHibernate condicionPriorizableDao;

	public CondicionPriorizableService(CondicionPriorizableDaoHibernate condicionPriorizableDao) {
		this.condicionPriorizableDao = condicionPriorizableDao;
	}



	public void guardar(CondicionPriorizable entity) throws DaoException {
		condicionPriorizableDao.guardar(entity);

	}


	public void modificar(CondicionPriorizable entity) throws DaoException {
		condicionPriorizableDao.modificar(entity);

	}


	public void eliminar(CondicionPriorizable entity) throws DaoException {
		condicionPriorizableDao.eliminar(entity);

	}


	public CondicionPriorizable findByDescripcionByMetodologia(String nombreCondicion, Long id) {
		
		return condicionPriorizableDao.findByDescripcionByMetodologia(nombreCondicion, id);
	}

}
