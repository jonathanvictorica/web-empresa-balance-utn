package com.utn.jmg.inversiones.service;

import java.util.Date;
import java.util.List;

import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Balance;

public interface IBalanceService {

	void guardar(Balance balance) throws DaoException ;

	Balance buscarBalance(String cuit, Date fechaComienzo, Date fechaFin);

	void eliminar(Balance balance) throws DaoException ;

	List<Balance> allBalance(String cuit);

	void modificar(Balance balance) throws DaoException;

	List<String> obtenerTodosLosPeriodosBalance(String cuit);

	List<String> obtenerListaCuentas();

}
