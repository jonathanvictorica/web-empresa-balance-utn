package com.utn.jmg.inversiones.dao.impl;

import com.utn.jmg.inversiones.dao.entity.ComparadorEntity;
import com.utn.jmg.inversiones.dao.repo.IComparadorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class ComparadorDaoHibernate   {


private final IComparadorRepository comparadorRepository;

	public ComparadorDaoHibernate(IComparadorRepository comparadorRepository) {
		this.comparadorRepository = comparadorRepository;
	}

	public ComparadorEntity findComparadorByDescripcion(String descripcion) {

		return comparadorRepository.findTop1ByDescripcion(descripcion);

	}




}
