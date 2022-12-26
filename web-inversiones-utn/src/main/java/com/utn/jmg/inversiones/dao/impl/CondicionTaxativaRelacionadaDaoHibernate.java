package com.utn.jmg.inversiones.dao.impl;

import com.utn.jmg.inversiones.dao.ICondicionTaxativaRelacionadaDao;
import com.utn.jmg.inversiones.dao.entity.CondicionesTaxativasRelacionadasEntity;
import com.utn.jmg.inversiones.exception.DaoException;

public class CondicionTaxativaRelacionadaDaoHibernate extends BaseDaoHibernate<CondicionesTaxativasRelacionadasEntity> implements ICondicionTaxativaRelacionadaDao {

	@Override
	public void guardar(CondicionesTaxativasRelacionadasEntity entity) throws DaoException {
		this.guardarEntidad(entity);
		
	}

	@Override
	public void modificar(CondicionesTaxativasRelacionadasEntity entity) throws DaoException {
		this.modificarEntidad(entity);
		
	}

	@Override
	public void eliminar(CondicionesTaxativasRelacionadasEntity entity) throws DaoException {
		this.eliminarEntidad(entity);
		
	}

}
