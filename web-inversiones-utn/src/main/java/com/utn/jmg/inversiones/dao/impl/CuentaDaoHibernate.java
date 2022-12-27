package com.utn.jmg.inversiones.dao.impl;

import com.utn.jmg.inversiones.dao.entity.CuentaEntity;
import com.utn.jmg.inversiones.dao.repo.ICuentaRepository;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Cuenta;
import org.springframework.stereotype.Component;

@Component
public class CuentaDaoHibernate {


	private final ICuentaRepository cuentaRepository;

	public CuentaDaoHibernate(ICuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	public Cuenta findCuentaByNombre(String nombre) {
		Cuenta cuenta = this.createCuenta(cuentaRepository.findTop1ByNombre(nombre));
		return cuenta;
	}

	private Cuenta createCuenta(CuentaEntity cuenta) {
		try {
			Cuenta cuentaFactory = new Cuenta(cuenta.getNombre());
			cuentaFactory.setId(cuenta.getIdIndicadorEconomico());
			return cuentaFactory;
		} catch (Exception e) {
			return null;
		}

	}

	public void guardar(Cuenta entity) throws DaoException {
		cuentaRepository.save(this.createCuentaEntity(entity));
	}


	public void modificar(Cuenta entity) throws DaoException {
		cuentaRepository.save(this.createCuentaEntity(entity));
	}


	public void eliminar(Cuenta entity) throws DaoException {
		cuentaRepository.delete(entity.getId());
	}


	private CuentaEntity createCuentaEntity(Cuenta cuenta) {
		CuentaEntity cuentaEntity = new CuentaEntity(cuenta.getId(), cuenta.getNombre(), cuenta.getCodigo());
		return cuentaEntity;
	}


}
