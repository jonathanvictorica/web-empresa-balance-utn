package com.utn.jmg.inversiones.repositorio;

import com.utn.jmg.inversiones.dao.ICondicionPriorizableDao;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;

public class RepositorioDeCondicionPriorizables implements ICondicionPriorizableDao {

	private ICondicionPriorizableDao condicionPriorizableDao;

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

	public ICondicionPriorizableDao getCondicionPriorizableDao() {
		return condicionPriorizableDao;
	}

	public void setCondicionPriorizableDao(ICondicionPriorizableDao condicionPriorizableDao) {
		this.condicionPriorizableDao = condicionPriorizableDao;
	}

	

}
