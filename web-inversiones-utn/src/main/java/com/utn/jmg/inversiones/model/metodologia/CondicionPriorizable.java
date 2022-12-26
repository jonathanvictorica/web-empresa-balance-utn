package com.utn.jmg.inversiones.model.metodologia;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.utn.jmg.inversiones.model.dto.EmpresaCondicion;
import com.utn.jmg.inversiones.model.dto.EmpresaResultado;
import com.utn.jmg.inversiones.model.dto.CondicionDto;

public class CondicionPriorizable extends CondicionMetodologia {
	private List<CondicionTaxativa> condicionTaxativa;

	public CondicionPriorizable() {
		condicionTaxativa = new ArrayList<CondicionTaxativa>();
	}

	public List<EmpresaResultado> cualConvieneInvertir(List<EmpresaCondicion> empresas) {
		List<EmpresaResultado> empresaResultado = new ArrayList<EmpresaResultado>();
		empresas.forEach(unaEmpresa -> agregarAEmpresaSiCumple(unaEmpresa, empresaResultado));
		Collections.sort(empresaResultado);
		return empresaResultado;
	}

	public void agregarAEmpresaSiCumple(EmpresaCondicion empresa, List<EmpresaResultado> empresaResultado) {
		EmpresaResultado empresaRes = new EmpresaResultado();
		empresaRes.setEmpresa(empresa.getEmpresa());
		this.condicionTaxativa.forEach(unaCond -> agregarSiCumple(empresaRes, empresa, unaCond));
		empresaResultado.add(empresaRes);
	}

	public void agregarSiCumple(EmpresaResultado empresaRes, EmpresaCondicion empresa, CondicionTaxativa condTax) {
		empresaRes.getNombreCondicion().add(condTax.getDescripcion());
		try {
			empresaRes.getCumpleCondicion().add(condTax.empresaCumple(empresa));
		} catch (Exception e) {
			empresaRes.getCumpleCondicion().add(false);
		}
	}

	@Override
	public CondicionDto getCondicionDto() {
		CondicionDto condicion = new CondicionDto();
		condicion.setNombreCondicion(TipoCondiciones.PRIORIZABLE.getTipo());
		condicion.setDescripcionCondicion(this.getDescripcion());

		List<String> listCondicionesTaxativas = new ArrayList<String>();
		this.condicionTaxativa.forEach(unaCond -> listCondicionesTaxativas.add(unaCond.getDescripcion()));
		condicion.setCondicionesTaxativas(listCondicionesTaxativas);
		return condicion;
	}



	public void agregarCondicionTaxativa(CondicionTaxativa condicionTaxativa) {
		this.condicionTaxativa.add(condicionTaxativa);
	}

	@Override
	public String getFormulaCondicion() {

		return "Ordenar por ";
	}

	public List<CondicionTaxativa> getCondicionTaxativa() {
		return condicionTaxativa;
	}

}
