package com.utn.jmg.inversiones.dao.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.reflect.TypeToken;
import com.utn.jmg.inversiones.dao.IBalanceDao;
import com.utn.jmg.inversiones.model.Balance;
import com.utn.jmg.inversiones.model.Empresa;

public class BalanceDaoJSON extends BaseDaoJSON implements IBalanceDao<Balance> {

	public void guardar(Balance balance) {
		List<Balance> balances = this.getAll();
		balances.add(balance);
		this.modificarArchivo(gson.toJson(balances));
	}

	public void modificar(Balance balance) {
		List<Balance> balances = this.getAll();
		balances = balances.stream().filter(bal -> !bal.compareTo(balance)).collect(Collectors.toList());
		balances.add(balance);
		this.modificarArchivo(gson.toJson(balances));
	}

	public void eliminar(Balance balance) {
		List<Balance> balances = this.getAll();
		balances = balances.stream().filter(bal -> !bal.compareTo(balance)).collect(Collectors.toList());
		this.modificarArchivo(gson.toJson(balances));
	}

	public List<Balance> getAll() {
		InputStream is;
		try {
			is = new FileInputStream(this.getFilePath());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
			Type type = new TypeToken<List<Balance>>() {
			}.getType();
			return gson.fromJson(bufferedReader, type);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Balance> allBalance(String cuit) {
		try {
			List<Balance> balances = this.getAll();
			balances = balances.stream().filter(bal -> bal.getEmpresa().getCuit().equals(cuit)).collect(Collectors.toList());
			return balances;
		} catch (Exception e) {
			return null;

		}

	}

	@Override
	public Balance buscarBalance(String cuit, Date fechaComienzo, Date fechaFin) {
		try {
			List<Balance> balances = this.getAll();
			Balance balcom = new Balance(fechaComienzo, fechaFin);
			balcom.setEmpresa(new Empresa(cuit));
			Balance balance = balances.stream().filter(bal -> balcom.compareTo(bal)).findFirst().get();
			return balance;
		} catch (Exception e) {
			return null;

		}
	}

	@Override
	public List<String> obtenerTodosLosPeriodosBalance(String cuit) {

		return allBalance(cuit).stream().map(bal -> bal.periodo()).collect(Collectors.toList());
	}

	@Override
	public List<String> obtenerListaCuentas() {
		
		return null;
	}

}
