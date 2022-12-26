package com.utn.jmg.inversiones.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.utn.jmg.inversiones.dao.ICondicionPriorizableDao;
import com.utn.jmg.inversiones.dao.ICondicionTaxativaDao;
import com.utn.jmg.inversiones.dao.IMetodologiaDao;
import com.utn.jmg.inversiones.dao.entity.MetodologiaEntity;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Metodologia;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;

public class MetodologiaDaoHibernate extends BaseDaoHibernate<MetodologiaEntity> implements IMetodologiaDao<Metodologia> {

	private ICondicionTaxativaDao condicionTaxativaDao;
	private ICondicionPriorizableDao condicionPriorizableDao;

	@Override
	public Metodologia buscarMetodologia(String nombreMetodologia, String nickUsuario) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(MetodologiaEntity.class);
		crit.add(Restrictions.eq("nombre", nombreMetodologia));
		crit.add(Restrictions.eq("usr.nick", nickUsuario));
		crit.createAlias("condicionTaxativas", "ct", CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("condicionPriorizables", "cp", CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("cp.condicionesTaxativasRelacionadases", "ctr", CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("ctr.condicionTaxativa", "ctrR", CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("ct.comparador", "comparador", CriteriaSpecification.INNER_JOIN);
		crit.createAlias("ct.indicadorEconomico", "indicadorEconomico", CriteriaSpecification.INNER_JOIN);
		crit.createAlias("ctr.condicionPriorizable", "ctrP", CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("usuario", "usr", CriteriaSpecification.INNER_JOIN);
		MetodologiaEntity metodologia = (MetodologiaEntity) crit.uniqueResult();

		Metodologia metodologiaBuscada = this.factoryModel.createMetodologia(metodologia);
		session.close();
		return metodologiaBuscada;
	}

	// @Override
	// public List<Metodologia> all() {
	// Session session = this.getOpenSession();
	// Criteria crit = session.createCriteria(MetodologiaEntity.class);
	// @SuppressWarnings("unchecked")
	// List<MetodologiaEntity> metodologias = (List<MetodologiaEntity>)
	// crit.list();
	//
	// List<Metodologia> metodologiaResult = metodologias.stream().map(b ->
	// this.factoryModel.createMetodologia(b)).collect(Collectors.toList());
	// session.close();
	// return metodologiaResult;
	// }

	@Override
	public List<Metodologia> allByUsuario(String nickUsuario) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(MetodologiaEntity.class);
		crit.createAlias("usuario", "usr", CriteriaSpecification.INNER_JOIN);
		crit.add(Restrictions.eq("usr.nick", nickUsuario));
		@SuppressWarnings("unchecked")
		List<MetodologiaEntity> metodologias = (List<MetodologiaEntity>) crit.list();

		List<Metodologia> metodologiaResult = metodologias.stream().map(b -> this.factoryModel.createMetodologia(b)).collect(Collectors.toList());
		session.close();
		return metodologiaResult;
	}

	@Override
	public void guardar(Metodologia entity) throws DaoException {
		MetodologiaEntity metodologiaEntity = entityFactory.createMetodologiaEntity(entity);
		// Set<CondicionTaxativaEntity> condTaxativas =
		// metodologiaEntity.getCondicionTaxativas();
		// Set<CondicionPriorizableEntity> condPriorizables =
		// metodologiaEntity.getCondicionPriorizables();

		this.guardarEntidad(metodologiaEntity);
		entity.setId(metodologiaEntity.getIdMetodologia());
		entity.getCondicionesTaxativas().stream().forEach(cond -> {
			try {
				cond.setMetodologia(entity);
				condicionTaxativaDao.guardar(cond);
			} catch (Exception e) {
			}
		});
		entity.getCondicionesPriorizables().stream().forEach(cond -> {
			try {
				cond.setMetodologia(entity);
				condicionPriorizableDao.guardar(cond);
			} catch (Exception e) {
			}
		});
	}

	@Override
	public void modificar(Metodologia entity) throws DaoException {
		MetodologiaEntity metodologiaEntity = entityFactory.createMetodologiaEntity(entity);
		this.modificarEntidad(metodologiaEntity);
		entity.getCondicionesTaxativas().stream().forEach(cond -> {
			try {
				CondicionTaxativa condicionTaxativa = condicionTaxativaDao.findByDescripcionByMetodologia(cond.getDescripcion(), entity.getId());
				if (condicionTaxativa == null) {
					condicionTaxativaDao.guardar(cond);
				} else {
					cond.setId(condicionTaxativa.getId());
					condicionTaxativaDao.modificar(cond);
				}
			} catch (Exception e) {
			}
		});
		entity.getCondicionesPriorizables().stream().forEach(cond -> {
			try {
				CondicionPriorizable condicionPriorizable = condicionPriorizableDao.findByDescripcionByMetodologia(cond.getDescripcion(), entity.getId());
				if (condicionPriorizable == null) {
					condicionPriorizableDao.guardar(cond);
				} else {
					cond.setId(condicionPriorizable.getId());
					condicionPriorizableDao.modificar(cond);
				}
			} catch (Exception e) {
			}
		});
	}

	@Override
	public void eliminar(Metodologia entity) throws DaoException {
		this.eliminarEntidad(this.entityFactory.createMetodologiaEntity(entity));

	}

	public ICondicionTaxativaDao getCondicionTaxativaDao() {
		return condicionTaxativaDao;
	}

	public void setCondicionTaxativaDao(ICondicionTaxativaDao condicionTaxativaDao) {
		this.condicionTaxativaDao = condicionTaxativaDao;
	}

	public ICondicionPriorizableDao getCondicionPriorizableDao() {
		return condicionPriorizableDao;
	}

	public void setCondicionPriorizableDao(ICondicionPriorizableDao condicionPriorizableDao) {
		this.condicionPriorizableDao = condicionPriorizableDao;
	}

}
