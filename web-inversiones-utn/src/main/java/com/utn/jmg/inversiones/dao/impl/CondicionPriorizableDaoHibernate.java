package com.utn.jmg.inversiones.dao.impl;

import java.util.Set;

import com.utn.jmg.inversiones.dao.ICondicionPriorizableDao;
import com.utn.jmg.inversiones.dao.ICondicionTaxativaDao;
import com.utn.jmg.inversiones.dao.ICondicionTaxativaRelacionadaDao;
import com.utn.jmg.inversiones.dao.entity.CondicionPriorizableEntity;
import com.utn.jmg.inversiones.dao.entity.CondicionesTaxativasRelacionadasEntity;
import com.utn.jmg.inversiones.dao.entity.MetodologiaEntity;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;

public class CondicionPriorizableDaoHibernate extends BaseDaoHibernate<CondicionPriorizableEntity> implements ICondicionPriorizableDao {

	private ICondicionTaxativaRelacionadaDao condicionTaxativaRelacionadaDao;

	private ICondicionTaxativaDao condicionTaxativaDao;

	@Override
	public void guardar(CondicionPriorizable entity) throws DaoException {
		MetodologiaEntity metodologia = entityFactory.createMetodologiaEntity(entity.getMetodologia());
	
		
		CondicionPriorizableEntity condicion = entityFactory.createCondicionPriorizableEntity(entity, metodologia);
		Set<CondicionesTaxativasRelacionadasEntity> conRelacionadas = condicion.getCondicionesTaxativasRelacionadases();
		this.guardarEntidad(condicion);
		conRelacionadas.stream().forEach(cond -> {
			try {
				CondicionTaxativa condicionTax = condicionTaxativaDao.findByDescripcionByMetodologia(cond.getCondicionTaxativa().getNombreCondicion(), metodologia.getIdMetodologia());
				cond.setCondicionTaxativa(this.entityFactory.createCondicionTaxativaEntity(condicionTax, metodologia));
				condicionTaxativaRelacionadaDao.guardar(cond);
			} catch (Exception e) {
			}
		});

	}

	@Override
	public void modificar(CondicionPriorizable entity) throws DaoException {
		MetodologiaEntity metodologia = entityFactory.createMetodologiaEntity(entity.getMetodologia());
		CondicionPriorizableEntity condicion = entityFactory.createCondicionPriorizableEntity(entity, metodologia);
		this.modificarEntidad(condicion);

	}

	@Override
	public void eliminar(CondicionPriorizable entity) throws DaoException {
		MetodologiaEntity metodologia = entityFactory.createMetodologiaEntity(entity.getMetodologia());
		CondicionPriorizableEntity condicion = entityFactory.createCondicionPriorizableEntity(entity, metodologia);
		this.eliminarEntidad(condicion);

	}

	public ICondicionTaxativaRelacionadaDao getCondicionTaxativaRelacionadaDao() {
		return condicionTaxativaRelacionadaDao;
	}

	public void setCondicionTaxativaRelacionadaDao(ICondicionTaxativaRelacionadaDao condicionTaxativaRelacionadaDao) {
		this.condicionTaxativaRelacionadaDao = condicionTaxativaRelacionadaDao;
	}

	@Override
	public CondicionPriorizable findByDescripcionByMetodologia(String nombreCondicion, Long id) {
		// TODO: hacer esto
		return null;
	}

	public ICondicionTaxativaDao getCondicionTaxativaDao() {
		return condicionTaxativaDao;
	}

	public void setCondicionTaxativaDao(ICondicionTaxativaDao condicionTaxativaDao) {
		this.condicionTaxativaDao = condicionTaxativaDao;
	}

}
