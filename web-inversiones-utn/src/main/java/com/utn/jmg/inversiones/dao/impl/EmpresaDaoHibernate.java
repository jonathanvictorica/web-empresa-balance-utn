package com.utn.jmg.inversiones.dao.impl;

import com.utn.jmg.inversiones.dao.EntityFactory;
import com.utn.jmg.inversiones.dao.entity.EmpresaEntity;
import com.utn.jmg.inversiones.dao.repo.IEmpresaRepository;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Empresa;
import com.utn.jmg.inversiones.model.factory.ModelFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class EmpresaDaoHibernate {

	protected final ModelFactory factoryModel;
	protected final EntityFactory entityFactory;

	private final IEmpresaRepository empresaRepository;

	public EmpresaDaoHibernate(ModelFactory factoryModel, EntityFactory entityFactory, IEmpresaRepository empresaRepository) {
		this.factoryModel = factoryModel;
		this.entityFactory = entityFactory;
		this.empresaRepository = empresaRepository;
	}


	public Empresa findEmpresaByCuit(String cuit) {
		Empresa empresaBuscada = factoryModel.createEmpresa(empresaRepository.findTop1ByCuit(cuit));

		return empresaBuscada;
	}


	public Empresa findEmpresaByRazonSocial(String razonSocial) {
		Empresa empresaBuscada = factoryModel.createEmpresa(empresaRepository.findTop1ByRazonSocial(razonSocial));
		return empresaBuscada;
	}


	public void guardar(Empresa entity) throws DaoException {
		empresaRepository.save(this.entityFactory.createEmpresaEntity(entity));

	}


	public void modificar(Empresa entity) throws DaoException {
		empresaRepository.save(this.entityFactory.createEmpresaEntity(entity));

	}


	public void eliminar(Empresa entity) throws DaoException {
		empresaRepository.delete(entity.getId());

	}


}
