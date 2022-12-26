package com.utn.jmg.inversiones.repositorio;

import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Empresa;
import com.utn.jmg.inversiones.dao.IEmpresaDao;

public class RepositorioDeEmpresa implements IEmpresaDao<Empresa> {

	private IEmpresaDao<Empresa> empresaDao;

	public void guardar(Empresa entity) throws DaoException {
		this.empresaDao.guardar(entity);

	}

	public void modificar(Empresa entity) throws DaoException {
		this.empresaDao.modificar(entity);

	}

	public void eliminar(Empresa entity) throws DaoException {
		this.empresaDao.eliminar(entity);

	}

	public IEmpresaDao<Empresa> getEmpresaDao() {
		return empresaDao;
	}

	public void setEmpresaDao(IEmpresaDao<Empresa> empresaDao) {
		this.empresaDao = empresaDao;
	}

	@Override
	public Empresa findEmpresaByCuit(String cuit) {

		return empresaDao.findEmpresaByCuit(cuit);
	}

	@Override
	public Empresa findEmpresaByRazonSocial(String razonSocial) {

		return empresaDao.findEmpresaByRazonSocial(razonSocial);
	}

}
