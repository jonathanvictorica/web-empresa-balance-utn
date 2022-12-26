package com.utn.jmg.inversiones.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.utn.jmg.inversiones.model.Cuenta;
import com.utn.jmg.inversiones.model.Indicador;
import com.utn.jmg.inversiones.model.IndicadorEconomico;

public class CondicionDto {
	private String nombreCondicion = "";
	private String nombreIndicador = "";
	private String tipoIndicador = "";
	private String comparador = "";
	private Double valor = 0.00D;
	private String nombreIndicadorComparar = "";
	private String tipoIndicadorComparar = "";
	private String descripcionCondicion = ""; // Nombre que identifica de forma
												// univoca cada condicion
	private List<String> condicionesTaxativas = new ArrayList<String>();

	private IndicadorEconomico indicadorEconomico;
	private IndicadorEconomico indicadorEconomicoComparar;

	public String getNombreCondicion() {
		return nombreCondicion;
	}

	public void setNombreCondicion(String nombreCondicion) {
		this.nombreCondicion = nombreCondicion;
	}

	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}

	public String getTipoIndicador() {
		return tipoIndicador;
	}

	public void setTipoIndicador(String tipoIndicador) {
		this.tipoIndicador = tipoIndicador;
	}

	public String getComparador() {
		return comparador;
	}

	public void setComparador(String comparador) {
		this.comparador = comparador;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNombreIndicadorComparar() {
		return nombreIndicadorComparar;
	}

	public void setNombreIndicadorComparar(String nombreIndicadorComparar) {
		this.nombreIndicadorComparar = nombreIndicadorComparar;
	}

	public String getTipoIndicadorComparar() {
		return tipoIndicadorComparar;
	}

	public void setTipoIndicadorComparar(String tipoIndicadorComparar) {
		this.tipoIndicadorComparar = tipoIndicadorComparar;
	}

	public String getDescripcionCondicion() {
		return descripcionCondicion;
	}

	public void setDescripcionCondicion(String descripcionCondicion) {
		this.descripcionCondicion = descripcionCondicion;
	}

	public IndicadorEconomico gettIndicadorEconomico() {
		if (indicadorEconomico != null) {
			return indicadorEconomico;
		} else {
			if (this.getTipoIndicador().equalsIgnoreCase("Cuenta")) {
				return new Cuenta(getNombreIndicador());
			} else {
				return new Indicador(getNombreIndicador());
			}
		}

	}

	public void setearIndicadorEconomico(IndicadorEconomico indicadorEconomico) {
		this.indicadorEconomico = indicadorEconomico;
	}

	public IndicadorEconomico gettIndicadorEconomicoComparar() {
		if (indicadorEconomicoComparar != null) {
			return indicadorEconomicoComparar;
		} else {
			if (this.getTipoIndicadorComparar().equalsIgnoreCase("Cuenta")) {
				return new Cuenta(getNombreIndicadorComparar());
			} else {
				return new Indicador(getNombreIndicadorComparar());
			}
		}
	}

	public void setearIndicadorEconomicoComparar(IndicadorEconomico indicadorEconomicoComparar) {
		this.indicadorEconomicoComparar = indicadorEconomicoComparar;
	}

	public List<String> getCondicionesTaxativas() {
		return condicionesTaxativas;
	}

	public void setCondicionesTaxativas(List<String> condicionesTaxativas) {
		this.condicionesTaxativas = condicionesTaxativas;
	}

}
