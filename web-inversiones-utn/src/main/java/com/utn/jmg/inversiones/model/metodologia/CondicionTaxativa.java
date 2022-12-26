package com.utn.jmg.inversiones.model.metodologia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.utn.jmg.inversiones.model.Cuenta;
import com.utn.jmg.inversiones.model.Empresa;
import com.utn.jmg.inversiones.model.dto.EmpresaCondicion;
import com.utn.jmg.inversiones.model.Balance;
import com.utn.jmg.inversiones.model.IndicadorEconomico;
import com.utn.jmg.inversiones.model.dto.CondicionDto;
import com.utn.jmg.inversiones.util.FormulaUtils;

public abstract class CondicionTaxativa extends CondicionMetodologia {
	private IndicadorEconomico indicadorEconomico; // puede ser cuenta o
													// indicador
	private Comparador comparador;

	protected TipoCondiciones descripcionCondicion;

	protected CondicionTaxativa(IndicadorEconomico indicadorEconomico, Comparador comparador) {
		super();
		this.indicadorEconomico = indicadorEconomico;
		this.comparador = comparador;
	}

	public Boolean empresaCumple(EmpresaCondicion empresa) {
		try {
			for (Balance balance : empresa.getBalances()) {

				if (this.cumpleCondicionTaxativa(balance).equals(false)) {
					return false;
				}

			}
			return true;
		} catch (Exception e) {
			return false;
		}
		// return !empresa.getBalances().stream().anyMatch(balance ->
		// this.cumpleCondicionTaxativa(balance).equals(false));
	}

	private Boolean cumpleCondicionTaxativa(Balance balance) throws Exception {
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Double valorIndicadorEconomico = indicadorEconomico.obtenerValor(balance.getEmpresa(), balance, cuentas);
		Double valorComparar = this.valorComparar(balance.getEmpresa(), balance);
		HashMap<String, Double> valores = new HashMap<String, Double>();
		valores.put("X", valorIndicadorEconomico);
		valores.put("Y", valorComparar);
		return FormulaUtils.calcularCondicion(valores, "X" + comparador.getSigno() + "Y");
	}

	public abstract Double valorComparar(Empresa empresa, Balance balance) throws Exception;

	public abstract CondicionDto getCondicionDto();

	public IndicadorEconomico getIndicadorEconomico() {
		return indicadorEconomico;
	}

	public void setIndicadorEconomico(IndicadorEconomico indicadorEconomico) {
		this.indicadorEconomico = indicadorEconomico;
	}

	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}

	public TipoCondiciones getTipoCondicion() {
		return descripcionCondicion;
	}

	
}
