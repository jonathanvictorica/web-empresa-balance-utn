package com.utn.jmg.inversiones.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.utn.jmg.inversiones.dao.IEmpresaDao;
import com.utn.jmg.inversiones.dao.entity.EmpresaEntity;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Empresa;

public class EmpresaDaoHibernate extends BaseDaoHibernate<EmpresaEntity> implements IEmpresaDao<Empresa> {

	@Override
	public Empresa findEmpresaByCuit(String cuit) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(EmpresaEntity.class);
		
		crit.add(Restrictions.eq("cuit", cuit));
		EmpresaEntity empresa = (EmpresaEntity) crit.uniqueResult();
		Empresa empresaBuscada = factoryModel.createEmpresa(empresa);
		session.close();
		return empresaBuscada;
	}

	@Override
	public Empresa findEmpresaByRazonSocial(String razonSocial) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(EmpresaEntity.class);
		crit.add(Restrictions.eq("razonSocial", razonSocial));
		
		EmpresaEntity empresa = (EmpresaEntity) crit.uniqueResult();
		Empresa empresaBuscada = factoryModel.createEmpresa(empresa);
		session.close();
		return empresaBuscada;
	}

	@Override
	public void guardar(Empresa entity) throws DaoException {
		this.guardarEntidad(this.entityFactory.createEmpresaEntity(entity));

	}

	@Override
	public void modificar(Empresa entity) throws DaoException {
		this.modificarEntidad(this.entityFactory.createEmpresaEntity(entity));

	}

	@Override
	public void eliminar(Empresa entity) throws DaoException {
		this.eliminarEntidad(this.entityFactory.createEmpresaEntity(entity));

	}

}
