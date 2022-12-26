package com.utn.jmg.inversiones.model.metodologia;

import java.util.ArrayList;
import java.util.List;

import com.utn.jmg.inversiones.model.Cuenta;
import com.utn.jmg.inversiones.model.Empresa;
import com.utn.jmg.inversiones.model.Balance;
import com.utn.jmg.inversiones.model.IndicadorEconomico;
import com.utn.jmg.inversiones.model.dto.CondicionDto;

public class CondicionTaxIndEconomico extends CondicionTaxativa {

	private IndicadorEconomico indicadorEconomicoComparar;

	public CondicionTaxIndEconomico(IndicadorEconomico indicadorEconomico, Comparador comparador, IndicadorEconomico indicadorEconomicoComparar, TipoCondiciones tipoCondicion, String descripcion) {
		super(indicadorEconomico, comparador);
		this.indicadorEconomicoComparar = indicadorEconomicoComparar;
		this.descripcionCondicion = tipoCondicion;
		this.descripcion = descripcion;
	}

	public IndicadorEconomico getIndicadorEconomicoComparar() {
		return indicadorEconomicoComparar;
	}

	public void setIndicadorEconomicoComparar(IndicadorEconomico indicadorEconomicoComparar) {
		this.indicadorEconomicoComparar = indicadorEconomicoComparar;
	}

	@Override
	public Double valorComparar(Empresa empresa, Balance balance) throws Exception {
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		return indicadorEconomicoComparar.obtenerValor(empresa, balance, cuentas);
	}

	@Override
	public CondicionDto getCondicionDto() {
		CondicionDto condicion = new CondicionDto();
		condicion.setComparador(this.getComparador().getNombre());
		condicion.setNombreCondicion(TipoCondiciones.TAXATIVAS_CON_INDICADOR.getTipo());
		condicion.setNombreIndicador(this.getIndicadorEconomico().getNombreIndicadorEconomico());
		condicion.setTipoIndicador(this.getIndicadorEconomico().tipoIndicador());
		condicion.setNombreIndicadorComparar(this.getIndicadorEconomicoComparar().getNombreIndicadorEconomico());
		condicion.setTipoIndicadorComparar(this.getIndicadorEconomicoComparar().tipoIndicador());
		condicion.setDescripcionCondicion(this.getDescripcion());
		return condicion;
	}

	@Override
	public String getFormulaCondicion() {

		return this.getIndicadorEconomico().getNombreIndicadorEconomico() + " sea " + this.getComparador().getSigno() + " a " + this.getIndicadorEconomicoComparar().getNombreIndicadorEconomico();
	}

}
