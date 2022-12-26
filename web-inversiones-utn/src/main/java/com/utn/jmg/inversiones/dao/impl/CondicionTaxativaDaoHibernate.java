package com.utn.jmg.inversiones.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.utn.jmg.inversiones.dao.ICondicionTaxativaDao;
import com.utn.jmg.inversiones.dao.entity.CondicionTaxativaEntity;
import com.utn.jmg.inversiones.dao.entity.MetodologiaEntity;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;

public class CondicionTaxativaDaoHibernate extends BaseDaoHibernate<CondicionTaxativaEntity> implements ICondicionTaxativaDao {

	@Override
	public void guardar(CondicionTaxativa entity) throws DaoException {
		MetodologiaEntity metodologia = entityFactory.createMetodologiaEntity(entity.getMetodologia());
		CondicionTaxativaEntity condicion = entityFactory.createCondicionTaxativaEntity(entity, metodologia);
		this.guardarEntidad(condicion);

	}

	@Override
	public void modificar(CondicionTaxativa entity) throws DaoException {
		MetodologiaEntity metodologia = entityFactory.createMetodologiaEntity(entity.getMetodologia());
		CondicionTaxativaEntity condicion = entityFactory.createCondicionTaxativaEntity(entity, metodologia);
		this.modificarEntidad(condicion);

	}

	@Override
	public void eliminar(CondicionTaxativa entity) throws DaoException {
		MetodologiaEntity metodologia = entityFactory.createMetodologiaEntity(entity.getMetodologia());
		CondicionTaxativaEntity condicion = entityFactory.createCondicionTaxativaEntity(entity, metodologia);
		this.eliminarEntidad(condicion);

	}

	@Override
	public CondicionTaxativa findByDescripcionByMetodologia(String nombreCondicion, Long idMetodologia) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(CondicionTaxativaEntity.class);
		crit.createAlias("metodologia", "met", CriteriaSpecification.INNER_JOIN);
		crit.add(Restrictions.eq("nombreCondicion", nombreCondicion));
		crit.add(Restrictions.eq("met.id", idMetodologia));
		CondicionTaxativaEntity condicionTaxativaEntity = (CondicionTaxativaEntity) crit.uniqueResult();
		CondicionTaxativa cond = this.factoryModel.createCondicionTaxativa(condicionTaxativaEntity, "");
		session.close();
		return cond;
	}

}
