package com.utn.jmg.inversiones.service;

import java.util.Date;
import java.util.List;

import com.utn.jmg.inversiones.dao.impl.BalanceDaoHibernate;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Balance;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
	private final BalanceDaoHibernate balanceDao;

	public BalanceService(BalanceDaoHibernate balanceDao) {
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


	public List<String> obtenerTodosLosPeriodosBalance(String cuit) {
		return balanceDao.obtenerTodosLosPeriodosBalance(cuit);
	}


	public List<String> obtenerListaCuentas() {
		return balanceDao.obtenerListaCuentas();
	}

}
