package com.utn.jmg.inversiones.service;

import com.utn.jmg.inversiones.dao.impl.CondicionTaxativaDaoHibernate;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;
import com.utn.jmg.inversiones.exception.DaoException;
import org.springframework.stereotype.Service;

@Service
public class CondicionTaxativaService  {
	private final CondicionTaxativaDaoHibernate condicionTaxativaDao;

	public CondicionTaxativaService(CondicionTaxativaDaoHibernate condicionTaxativaDao) {
		this.condicionTaxativaDao = condicionTaxativaDao;
	}



	public CondicionTaxativa findByDescripcionByMetodologia(String nombreCondicion, Long idMetodologia) {
	
		return condicionTaxativaDao.findByDescripcionByMetodologia(nombreCondicion, idMetodologia);
	}


	public void guardar(CondicionTaxativa entity) throws DaoException {
		condicionTaxativaDao.guardar(entity);
		
	}


	public void modificar(CondicionTaxativa entity) throws DaoException{
		condicionTaxativaDao.modificar(entity);
		
	}


	public void eliminar(CondicionTaxativa entity) throws DaoException{
		condicionTaxativaDao.eliminar(entity);
		
	}

}
