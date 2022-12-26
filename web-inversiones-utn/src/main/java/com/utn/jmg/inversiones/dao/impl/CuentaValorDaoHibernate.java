package com.utn.jmg.inversiones.dao.impl;

import com.utn.jmg.inversiones.dao.ICuentaValorDao;
import com.utn.jmg.inversiones.dao.entity.CuentaValorEntity;
import com.utn.jmg.inversiones.exception.DaoException;

public class CuentaValorDaoHibernate extends BaseDaoHibernate<CuentaValorEntity> implements ICuentaValorDao<CuentaValorEntity> {

	@Override
	public void guardar(CuentaValorEntity entity) throws DaoException {
		this.guardarEntidad(entity);
	}

	@Override
	public void modificar(CuentaValorEntity entity) throws DaoException {
		this.modificarEntidad(entity);

	}

	@Override
	public void eliminar(CuentaValorEntity entity) throws DaoException {
		this.eliminarEntidad(entity);

	}

}
