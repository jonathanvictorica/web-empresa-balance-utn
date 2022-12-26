package com.utn.jmg.inversiones.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.utn.jmg.inversiones.dao.EntityFactory;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.factory.ModelFactory;

public class BaseDaoHibernate<Entity> extends HibernateDaoSupport {
	private static final Logger log = Logger.getLogger(BaseDaoHibernate.class);
	protected ModelFactory factoryModel ;
	protected EntityFactory entityFactory ;

	public ModelFactory getFactoryModel() {
		return factoryModel;
	}

	public void setFactoryModel(ModelFactory factoryModel) {
		this.factoryModel = factoryModel;
	}

	public EntityFactory getEntityFactory() {
		return entityFactory;
	}

	public void setEntityFactory(EntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	public static Logger getLog() {
		return log;
	}

	public void guardarEntidad(Entity entity) throws DaoException {
		try {
			log.debug("guardando entidad..");

			getHibernateTemplate().save(entity);
			log.debug("entidad guardada.");

		} catch (org.hibernate.exception.ConstraintViolationException cve) {

			throw new DaoException(cve.getMessage() + cve.getCause());
		}

		catch (Exception e) {
			throw new DaoException(e.getMessage() + e.getCause());
		}
	}

	public void modificarEntidad(Entity entity) throws DaoException {
		try {
			log.debug("modificando entidad..");
			getHibernateTemplate().update(entity);
			log.debug("entidad modificada.");
		} catch (Exception e) {
			throw new DaoException(e.getMessage() + e.getCause());
		}
	}

	public void eliminarEntidad(Entity entity) throws DaoException {
		try {
			log.debug("eliminando entidad..");
			getHibernateTemplate().delete(entity);
			log.debug("entidad eliminada.");
		} catch (Exception e) {
			throw new DaoException(e.getMessage() + e.getCause());
		}
	}

	public Session getOpenSession() {
		return getHibernateTemplate().getSessionFactory().openSession();
	}

}
