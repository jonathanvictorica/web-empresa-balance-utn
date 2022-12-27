package com.utn.jmg.inversiones.dao.impl;

import com.utn.jmg.inversiones.dao.EntityFactory;
import com.utn.jmg.inversiones.dao.entity.CondicionPriorizableEntity;
import com.utn.jmg.inversiones.dao.entity.CondicionesTaxativasRelacionadasEntity;
import com.utn.jmg.inversiones.dao.entity.MetodologiaEntity;
import com.utn.jmg.inversiones.dao.repo.CondicionTaxativaRelacionadaDaoHibernate;
import com.utn.jmg.inversiones.dao.repo.ICondicionPriorizableRepository;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.factory.ModelFactory;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Component
public class CondicionPriorizableDaoHibernate  {
	private  final CondicionTaxativaRelacionadaDaoHibernate condicionTaxativaRelacionadaDao;

	private final CondicionTaxativaDaoHibernate condicionTaxativaDao;
	protected final ModelFactory factoryModel ;
	protected final EntityFactory entityFactory ;

	private final ICondicionPriorizableRepository condicionPriorizableRepository;

	public CondicionPriorizableDaoHibernate(CondicionTaxativaRelacionadaDaoHibernate condicionTaxativaRelacionadaDao, CondicionTaxativaDaoHibernate condicionTaxativaDao, ModelFactory factoryModel, EntityFactory entityFactory, ICondicionPriorizableRepository condicionPriorizableRepository) {
		this.condicionTaxativaRelacionadaDao = condicionTaxativaRelacionadaDao;
		this.condicionTaxativaDao = condicionTaxativaDao;
		this.factoryModel = factoryModel;
		this.entityFactory = entityFactory;
		this.condicionPriorizableRepository = condicionPriorizableRepository;
	}


	public void guardar(CondicionPriorizable entity) throws DaoException {
		MetodologiaEntity metodologia = entityFactory.createMetodologiaEntity(entity.getMetodologia());
	
		
		CondicionPriorizableEntity condicion = entityFactory.createCondicionPriorizableEntity(entity, metodologia);
		Set<CondicionesTaxativasRelacionadasEntity> conRelacionadas = condicion.getCondicionesTaxativasRelacionadases();
		condicionPriorizableRepository.save(condicion);
		conRelacionadas.stream().forEach(cond -> {
			try {
				CondicionTaxativa condicionTax = condicionTaxativaDao.findByDescripcionByMetodologia(cond.getCondicionTaxativa().getNombreCondicion(), metodologia.getIdMetodologia());
				cond.setCondicionTaxativa(this.entityFactory.createCondicionTaxativaEntity(condicionTax, metodologia));
				condicionTaxativaRelacionadaDao.save(cond);
			} catch (Exception e) {
			}
		});

	}


	public void modificar(CondicionPriorizable entity) throws DaoException {
		MetodologiaEntity metodologia = entityFactory.createMetodologiaEntity(entity.getMetodologia());
		CondicionPriorizableEntity condicion = entityFactory.createCondicionPriorizableEntity(entity, metodologia);
		this.condicionPriorizableRepository.save(condicion);

	}


	public void eliminar(CondicionPriorizable entity) throws DaoException {
		condicionPriorizableRepository.delete(entity.getId());

	}


	public CondicionPriorizable findByDescripcionByMetodologia(String descripcion, Long id) {
		return null;
	}



}
