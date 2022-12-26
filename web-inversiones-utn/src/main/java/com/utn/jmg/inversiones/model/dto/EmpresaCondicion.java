package com.utn.jmg.inversiones.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.utn.jmg.inversiones.model.Balance;
import com.utn.jmg.inversiones.model.Empresa;

public class EmpresaCondicion {
	private Empresa empresa;
	private List<Balance> balances;

	public EmpresaCondicion() {

		this.balances = new ArrayList<Balance>();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Balance> getBalances() {
		return balances;
	}

	public void setBalances(List<Balance> balances) {
		this.balances = balances;
	}

}
