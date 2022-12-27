package com.utn.jmg.inversiones.dao.impl;

import com.utn.jmg.inversiones.dao.EntityFactory;
import com.utn.jmg.inversiones.dao.entity.MetodologiaEntity;
import com.utn.jmg.inversiones.dao.repo.IMetodologiaRepository;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Metodologia;
import com.utn.jmg.inversiones.model.factory.ModelFactory;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MetodologiaDaoHibernate  {
	private final CondicionTaxativaDaoHibernate condicionTaxativaDao;
	private final CondicionPriorizableDaoHibernate condicionPriorizableDao;
	protected final ModelFactory factoryModel ;
	protected final EntityFactory entityFactory ;

	private final IMetodologiaRepository metodologiaRepository;

	public MetodologiaDaoHibernate(CondicionTaxativaDaoHibernate condicionTaxativaDao, CondicionPriorizableDaoHibernate condicionPriorizableDao, ModelFactory factoryModel, EntityFactory entityFactory, IMetodologiaRepository metodologiaRepository) {
		this.condicionTaxativaDao = condicionTaxativaDao;
		this.condicionPriorizableDao = condicionPriorizableDao;
		this.factoryModel = factoryModel;
		this.entityFactory = entityFactory;
		this.metodologiaRepository = metodologiaRepository;
	}


	public Metodologia buscarMetodologia(String nombreMetodologia, String nickUsuario) {


		return this.factoryModel.createMetodologia(metodologiaRepository.findTop1ByNombreAndUsuario_nick(nombreMetodologia,nickUsuario));

	}



	public List<Metodologia> allByUsuario(String nickUsuario) {

		List<MetodologiaEntity> metodologias = metodologiaRepository.findAllByUsuario_nick(nickUsuario);

		List<Metodologia> metodologiaResult = metodologias.stream().map(b -> this.factoryModel.createMetodologia(b)).collect(Collectors.toList());
		return metodologiaResult;
	}


	public void guardar(Metodologia entity) throws DaoException {
		MetodologiaEntity metodologiaEntity = entityFactory.createMetodologiaEntity(entity);
		this.metodologiaRepository.save(metodologiaEntity);
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


	public void modificar(Metodologia entity) throws DaoException {
		MetodologiaEntity metodologiaEntity = entityFactory.createMetodologiaEntity(entity);
		this.metodologiaRepository.save(metodologiaEntity);
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


	public void eliminar(Metodologia entity) throws DaoException {
		this.metodologiaRepository.delete(entity.getId());

	}


}
