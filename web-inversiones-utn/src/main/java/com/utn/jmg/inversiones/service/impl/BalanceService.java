package com.utn.jmg.inversiones.service.impl;

import java.util.Date;
import java.util.List;

import com.utn.jmg.inversiones.service.IBalanceService;
import com.utn.jmg.inversiones.dao.IBalanceDao;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Balance;

public class BalanceService implements IBalanceService {
	private IBalanceDao<Balance> balanceDao;

	public IBalanceDao<Balance> getBalanceDao() {
		return balanceDao;
	}

	public void setBalanceDao(IBalanceDao<Balance> balanceDao) {
		this.balanceDao = balanceDao;
	}

	public void guardar(Balance balance) throws DaoException {
		balanceDao.guardar(balance);

	}

	public Balance buscarBalance(String cuit, Date fechaComienzo, Date fechaFin) {

		return balanceDao.buscarBalance(cuit, fechaComienzo, fechaFin);
	}

	public void eliminar(Balance balance) throws DaoException {
		balanceDao.eliminar(balance);

	}

	public List<Balance> allBalance(String cuit) {

		return balanceDao.allBalance(cuit);
	}

	public void modificar(Balance balance) throws DaoException {
		balanceDao.modificar(balance);

	}

	@Override
	public List<String> obtenerTodosLosPeriodosBalance(String cuit) {
		return balanceDao.obtenerTodosLosPeriodosBalance(cuit);
	}

	@Override
	public List<String> obtenerListaCuentas() {
		return balanceDao.obtenerListaCuentas();
	}

}
