package com.utn.jmg.inversiones.service;

import com.utn.jmg.inversiones.dao.impl.EmpresaDaoHibernate;
import com.utn.jmg.inversiones.model.Empresa;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService  {
	private final EmpresaDaoHibernate empresaDao;

	public EmpresaService(EmpresaDaoHibernate empresaDao) {
		this.empresaDao = empresaDao;
	}


	public Empresa findEmpresaByCuit(String cuit) {

		return empresaDao.findEmpresaByCuit(cuit);
	}

	public Empresa findEmpresaByRazonSocial(String razonSocial) {

		return empresaDao.findEmpresaByRazonSocial(razonSocial);
	}

}
