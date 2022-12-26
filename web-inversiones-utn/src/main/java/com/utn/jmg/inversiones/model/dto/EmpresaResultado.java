package com.utn.jmg.inversiones.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.utn.jmg.inversiones.model.Empresa;

public class EmpresaResultado implements Comparable<EmpresaResultado> {
	private Empresa empresa;
	private List<String> nombreCondicion;
	private List<Boolean> cumpleCondicion;

	private Boolean cumpleCondicionesTaxativas;

	public EmpresaResultado() {
		nombreCondicion = new ArrayList<String>();
		cumpleCondicion = new ArrayList<Boolean>();
	}

	public JSONObject resultadoCondicionTaxativa() {
		JSONObject resultado = new JSONObject();
		resultado.put("cuit", empresa.getCuit());
		if (cantidadCondicionesCumple() == cumpleCondicion.size()) {
			resultado.put("resultado", "La empresa cumple con todas las condiciones taxativas");
		} else {
			String mensaje = "No cumple las siguientes condiciones: ";
			for (int i = 0; i < cumpleCondicion.size(); i++) {
				if (!cumpleCondicion.get(i).booleanValue()) {
					mensaje += nombreCondicion.get(i) + ",";
				}
			}
			resultado.put("resultado", mensaje.substring(0, mensaje.length() - 2) + ".");
		}
		return resultado;
	}

	public JSONObject resultadoCondicionPriorizable() {
		JSONObject resultado = new JSONObject();
		JSONArray condiciones = new JSONArray();
		for (int i = 0; i < nombreCondicion.size(); i++) {
			condiciones.put(cumpleCondicion.get(i).booleanValue() ? "Cumple" : "No Cumple");
		}
		resultado.put("cuit", empresa.getCuit());
		resultado.put("condiciones", condiciones);
		return resultado;
	}

	private Long cantidadCondicionesCumple() {
		try {
			return cumpleCondicion.stream().filter(cumple -> cumple.booleanValue()).count();
		} catch (Exception e) {
			return 0L;
		}
	}

	@Override
	public int compareTo(EmpresaResultado empresa) {

		return empresa.cantidadCondicionesCumple().compareTo(this.cantidadCondicionesCumple());
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<String> getNombreCondicion() {
		return nombreCondicion;
	}

	public void setNombreCondicion(List<String> nombreCondicion) {
		this.nombreCondicion = nombreCondicion;
	}

	public List<Boolean> getCumpleCondicion() {
		return cumpleCondicion;
	}

	public void setCumpleCondicion(List<Boolean> cumpleCondicion) {
		this.cumpleCondicion = cumpleCondicion;
	}

	public Boolean getCumpleCondicionesTaxativas() {
		return cumpleCondicionesTaxativas;
	}

	public void setCumpleCondicionesTaxativas(Boolean cumpleCondicionesTaxativas) {
		this.cumpleCondicionesTaxativas = cumpleCondicionesTaxativas;
	}

	// public static List<Empresa> obtenerListaEmpresas(List<EmpresaResultado>
	// empresasOrdenadas) {
	// List<Empresa> empresas = new ArrayList<Empresa>();
	// for (EmpresaResultado empresaResultado : empresasOrdenadas) {
	// empresas.add(empresaResultado.getEmpresa());
	// }
	// return empresas;
	// }

}
