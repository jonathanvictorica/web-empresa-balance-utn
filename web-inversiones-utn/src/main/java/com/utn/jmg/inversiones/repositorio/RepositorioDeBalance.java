package com.utn.jmg.inversiones.repositorio;

import java.util.Date;
import java.util.List;

import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.dao.IBalanceDao;
import com.utn.jmg.inversiones.model.Balance;

public class RepositorioDeBalance implements IBalanceDao<Balance> {

	private IBalanceDao<Balance> balanceDao;

	public void guardar(Balance entity) throws DaoException {
		this.balanceDao.guardar(entity);

	}

	public void modificar(Balance entity) throws DaoException {
		this.balanceDao.modificar(entity);

	}

	public void eliminar(Balance entity) throws DaoException {
		this.balanceDao.eliminar(entity);
	}

	public IBalanceDao<Balance> getBalanceDao() {
		return balanceDao;
	}

	public void setBalanceDao(IBalanceDao<Balance> balanceDao) {
		this.balanceDao = balanceDao;
	}

	@Override
	public List<Balance> allBalance(String cuit) {

		return this.balanceDao.allBalance(cuit);

	}

	@Override
	public Balance buscarBalance(String cuit, Date fechaComienzo, Date fechaFin) {

		return this.balanceDao.buscarBalance(cuit, fechaComienzo, fechaFin);
	}

	@Override
	public List<String> obtenerTodosLosPeriodosBalance(String cuit) {
		
		return this.balanceDao.obtenerTodosLosPeriodosBalance(cuit);
	}

	@Override
	public List<String> obtenerListaCuentas() {
	
		return this.balanceDao.obtenerListaCuentas();
	}

}
