package com.utn.jmg.inversiones.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.utn.jmg.inversiones.dao.ICuentaDao;
import com.utn.jmg.inversiones.dao.entity.CuentaEntity;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Cuenta;

public class CuentaDaoHibernate extends BaseDaoHibernate<CuentaEntity> implements ICuentaDao<Cuenta> {

	@Override
	public Cuenta findCuentaByNombre(String nombre) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(CuentaEntity.class);
		crit.add(Restrictions.eq("nombre", nombre));
		CuentaEntity cuentaEntity = (CuentaEntity) crit.uniqueResult();
		Cuenta cuenta = this.factoryModel.createCuenta(cuentaEntity);
		session.close();
		return cuenta;
	}

	@Override
	public void guardar(Cuenta entity) throws DaoException {
		this.guardarEntidad(this.entityFactory.createCuentaEntity(entity));
	}

	@Override
	public void modificar(Cuenta entity) throws DaoException {
		this.modificarEntidad(this.entityFactory.createCuentaEntity(entity));
	}

	@Override
	public void eliminar(Cuenta entity) throws DaoException {
		this.eliminarEntidad(this.entityFactory.createCuentaEntity(entity));
	}

}
