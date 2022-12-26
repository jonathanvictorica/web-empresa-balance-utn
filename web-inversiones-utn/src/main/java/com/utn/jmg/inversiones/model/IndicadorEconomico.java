package com.utn.jmg.inversiones.model;

import java.util.List;

import javax.script.ScriptException;

public interface IndicadorEconomico {
	Double obtenerValor(Empresa empresa, Balance balance, List<Cuenta> cuentas) throws ScriptException;

	Boolean sePuedeCalcular(Empresa empresa, Balance balance) throws Exception;

	public String getNombreIndicadorEconomico();

	public String getNombreIndicadorEconomicoFormateado();

	public String tipoIndicador();

}
