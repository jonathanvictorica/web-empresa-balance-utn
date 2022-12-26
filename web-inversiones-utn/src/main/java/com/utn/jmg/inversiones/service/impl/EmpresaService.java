package com.utn.jmg.inversiones.service.impl;

import com.utn.jmg.inversiones.model.Empresa;
import com.utn.jmg.inversiones.service.IEmpresaService;
import com.utn.jmg.inversiones.dao.IEmpresaDao;

public class EmpresaService implements IEmpresaService {
	private IEmpresaDao<Empresa> empresaDao;

	public IEmpresaDao<Empresa> getEmpresaDao() {
		return empresaDao;
	}

	public void setEmpresaDao(IEmpresaDao<Empresa> empresaDao) {
		this.empresaDao = empresaDao;
	}

	public Empresa findEmpresaByCuit(String cuit) {

		return empresaDao.findEmpresaByCuit(cuit);
	}

	public Empresa findEmpresaByRazonSocial(String razonSocial) {

		return empresaDao.findEmpresaByRazonSocial(razonSocial);
	}

}
