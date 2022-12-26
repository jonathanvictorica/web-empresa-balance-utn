package com.utn.jmg.inversiones.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.utn.jmg.inversiones.model.Metodologia;
import com.utn.jmg.inversiones.model.metodologia.CondicionFactory;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;

public class MetodologiaDto {
	private String nombreMetodologia;
	private List<CondicionDto> condiciones;

	public MetodologiaDto() {
		super();

		this.condiciones = new ArrayList<CondicionDto>();
	}

	public String getNombreMetodologia() {
		return nombreMetodologia;
	}

	public void setNombreMetodologia(String nombreMetodologia) {
		this.nombreMetodologia = nombreMetodologia;
	}

	public List<CondicionDto> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<CondicionDto> condiciones) {
		this.condiciones = condiciones;
	}

	public boolean compareTo(Metodologia metodologia) {

		return nombreMetodologia.equals(metodologia.getNombre());
	}

	public Metodologia getMetodologia() {
		Metodologia metodologia = new Metodologia();
		metodologia.setNombre(getNombreMetodologia());
//		metodologia.setCondicionesPriorizables(new ArrayList<CondicionPriorizable>());
//		metodologia.setCondicionesTaxativas(new ArrayList<CondicionTaxativa>());
		condiciones.forEach(condicion -> agregarCondTaxativaSiCorresponde(condicion, metodologia));
		condiciones.forEach(condicion -> agregarCondPriorizableSiCorresponde(condicion, metodologia));

		return metodologia;
	}

	public void agregarCondTaxativaSiCorresponde(CondicionDto condicion, Metodologia metodologia) {
		CondicionFactory condicionFactory = new CondicionFactory();
		CondicionTaxativa condicionTaxativa = condicionFactory.createCondicionTaxativa(condicion);
		if (condicionTaxativa != null) {
			metodologia.agregarCondicionTaxativa(condicionTaxativa);
		}
	}

	public void agregarCondPriorizableSiCorresponde(CondicionDto condicion, Metodologia metodologia) {
//		CondicionFactory condicionFactory = new CondicionFactory();
//		CondicionPriorizable condicionPriorizable = condicionFactory.createCondicionPriorizable(condicion, metodologia.getCondicionesTaxativas());
//		if (condicionPriorizable != null) {
//			metodologia.agregarCondicionPriorizable(condicionPriorizable);
//		}
	}

}
