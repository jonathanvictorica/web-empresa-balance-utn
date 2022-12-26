package com.utn.jmg.inversiones.model.metodologia;

import com.utn.jmg.inversiones.model.Empresa;
import com.utn.jmg.inversiones.model.Balance;
import com.utn.jmg.inversiones.model.IndicadorEconomico;
import com.utn.jmg.inversiones.model.dto.CondicionDto;

public class CondicionTaxValor extends CondicionTaxativa {

	private Double valor;

	public CondicionTaxValor(IndicadorEconomico indicadorEconomico, Comparador comparador, Double valor, TipoCondiciones tipoCondicion, String descripcion) {
		super(indicadorEconomico, comparador);
		this.valor = valor;
		this.descripcionCondicion = tipoCondicion;
		this.descripcion = descripcion;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public Double valorComparar(Empresa empresa, Balance balance) {

		return valor;
	}

	@Override
	public CondicionDto getCondicionDto() {
		CondicionDto condicion = new CondicionDto();
		condicion.setComparador(this.getComparador().getNombre());
		condicion.setNombreCondicion(TipoCondiciones.TAXATIVAS_CON_VALOR.getTipo());
		condicion.setNombreIndicador(this.getIndicadorEconomico().getNombreIndicadorEconomico());
		condicion.setTipoIndicador(this.getIndicadorEconomico().tipoIndicador());
		condicion.setValor(this.getValor());
		condicion.setDescripcionCondicion(this.getDescripcion());
		return condicion;
	}

	@Override
	public String getFormulaCondicion() {

		return this.getIndicadorEconomico().getNombreIndicadorEconomico() + " sea " + this.getComparador().getSigno() + " a " + this.getValor().toString();
	}
}
