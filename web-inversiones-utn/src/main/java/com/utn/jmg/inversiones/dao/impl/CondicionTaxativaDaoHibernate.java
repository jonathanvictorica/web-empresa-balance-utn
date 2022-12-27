package com.utn.jmg.inversiones.dao.impl;

import com.utn.jmg.inversiones.dao.EntityFactory;
import com.utn.jmg.inversiones.dao.entity.CondicionTaxativaEntity;
import com.utn.jmg.inversiones.dao.entity.MetodologiaEntity;
import com.utn.jmg.inversiones.dao.repo.ICondicionTaxativaRepository;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.factory.ModelFactory;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;
import org.springframework.stereotype.Component;

@Component
public class CondicionTaxativaDaoHibernate {


	protected final ModelFactory factoryModel;
	protected final EntityFactory entityFactory;

	private final ICondicionTaxativaRepository condicionTaxativaRepository;

	public CondicionTaxativaDaoHibernate(ModelFactory factoryModel, EntityFactory entityFactory, ICondicionTaxativaRepository condicionTaxativaRepository) {
		this.factoryModel = factoryModel;
		this.entityFactory = entityFactory;
		this.condicionTaxativaRepository = condicionTaxativaRepository;
	}


	public void guardar(CondicionTaxativa entity) throws DaoException {
		MetodologiaEntity metodologia = entityFactory.createMetodologiaEntity(entity.getMetodologia());
		CondicionTaxativaEntity condicion = entityFactory.createCondicionTaxativaEntity(entity, metodologia);
		this.condicionTaxativaRepository.save(condicion);

	}


	public void modificar(CondicionTaxativa entity) throws DaoException {
		MetodologiaEntity metodologia = entityFactory.createMetodologiaEntity(entity.getMetodologia());
		CondicionTaxativaEntity condicion = entityFactory.createCondicionTaxativaEntity(entity, metodologia);
		this.condicionTaxativaRepository.save(condicion);

	}


	public void eliminar(CondicionTaxativa entity) throws DaoException {
		this.condicionTaxativaRepository.delete(entity.getId());

	}


	public CondicionTaxativa findByDescripcionByMetodologia(String nombreCondicion, Long idMetodologia) {
		CondicionTaxativaEntity condicionTaxativaEntity = condicionTaxativaRepository.findTop1ByNombreCondicionAndMetodologia_idMetodologia(nombreCondicion, idMetodologia);
		CondicionTaxativa cond = this.factoryModel.createCondicionTaxativa(condicionTaxativaEntity, "");
		return cond;
	}



}
