package com.utn.jmg.inversiones.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.utn.jmg.inversiones.dao.IComparadorDao;
import com.utn.jmg.inversiones.dao.entity.ComparadorEntity;

public class ComparadorDaoHibernate extends BaseDaoHibernate<ComparadorEntity> implements IComparadorDao {

	@Override
	public ComparadorEntity findComparadorByDescripcion(String descripcion) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(ComparadorEntity.class);
		crit.add(Restrictions.eq("descripcion", descripcion.toUpperCase()));
		ComparadorEntity comparadorEntity = (ComparadorEntity) crit.uniqueResult();
		session.close();
		return comparadorEntity;

	}


}
