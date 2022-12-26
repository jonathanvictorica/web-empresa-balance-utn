package com.utn.jmg.inversiones.dao;

import java.util.Date;
import java.util.List;

import com.utn.jmg.inversiones.model.Balance;

public interface IBalanceDao<Entity> extends IBaseDao<Entity> {

	List<Entity> allBalance(String cuit);

	Balance buscarBalance(String cuit, Date fechaComienzo, Date fechaFin);

	List<String> obtenerTodosLosPeriodosBalance(String cuit);

	List<String> obtenerListaCuentas();

}
