package com.utn.jmg.inversiones.model;
// Generated 21-ago-2017 11:21:49 by Hibernate Tools 4.3.1

import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;
import com.utn.jmg.inversiones.util.FormulaUtils;

/**
 * Indicador generated by hbm2java
 */
public class Indicador implements java.io.Serializable, IndicadorEconomico {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String formula;

	private UsuarioEntity usuario;

	public Indicador() {
	}

	public Indicador(String nombre, String formula) {
		this.nombre = nombre;
		this.formula = formula;
	}

	public Indicador(String nombreIndicador) {
		this.nombre = nombreIndicador;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Double aplicarIndicador(Empresa empresa, Balance balance, List<Cuenta> cuentas) throws Exception {
		if (this.sePuedeCalcular(empresa, balance)) {
			return this.obtenerValor(empresa, balance, cuentas);
		}
		throw new Exception("No se puede calcular ");
	}

	public Double obtenerValor(Empresa empresa, Balance balance, List<Cuenta> cuentas) throws ScriptException {
		List<IndicadorEconomico> indicadoresEconomico;
		indicadoresEconomico = FormulaUtils.obtenerElementosFormula(formula, usuario.getNick());
		HashMap<String, Double> valores = new HashMap<String, Double>();
		for (IndicadorEconomico indicadorEconomico : indicadoresEconomico) {
			valores.put(indicadorEconomico.getNombreIndicadorEconomicoFormateado(), indicadorEconomico.obtenerValor(empresa, balance, cuentas));
		}
		return FormulaUtils.calcular(valores, getFormula());

	}

	public Boolean sePuedeCalcular(Empresa empresa, Balance balance) throws Exception {
		List<IndicadorEconomico> indicadoresEconomico;
		indicadoresEconomico = FormulaUtils.obtenerElementosFormula(formula, usuario.getNick());
		
		for (IndicadorEconomico ind : indicadoresEconomico) {
			try {
				ind.sePuedeCalcular(empresa, balance);
			} catch (Exception e) {
				return false;
			}
		}
		return true;

	}

	public boolean compareTo(Indicador indicador) {

		return indicador.getNombreIndicadorEconomico().equals(getNombreIndicadorEconomico());
	}

	@Override
	public String tipoIndicador() {

		return "Indicador";
	}

	@Override
	public String getNombreIndicadorEconomicoFormateado() {
		return "i_" + nombre;
	}

	@Override
	public String getNombreIndicadorEconomico() {

		return nombre;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

}