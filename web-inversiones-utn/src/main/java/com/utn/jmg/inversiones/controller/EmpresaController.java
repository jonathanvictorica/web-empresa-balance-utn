package com.utn.jmg.inversiones.controller;

import com.utn.jmg.inversiones.model.Empresa;
import com.utn.jmg.inversiones.service.EmpresaService;
import com.utn.jmg.inversiones.service.UsuarioService;
import org.json.JSONObject;

public class EmpresaController extends BaseController {

	private final EmpresaService empresaService;

	public EmpresaController(UsuarioService usuarioService, EmpresaService empresaService) {
		super(usuarioService);
		this.empresaService = empresaService;
	}

	public String buscarEmpresa() {
		JSONObject datos = new JSONObject(this.resultado!=null && !this.resultado.isEmpty() ?  this.resultado : this.getInput());
		JSONObject result = new JSONObject();
		try {
			Empresa empresa = null;
			String filtro = datos.getString("FiltroBusqueda");
			if (filtro.equalsIgnoreCase("CUIT")) {
				String cuit = datos.getString("cuit");
				empresa = empresaService.findEmpresaByCuit(cuit);
			} else if (filtro.equalsIgnoreCase("Razon Social")) {
				String razonSocial = datos.getString("razonSocial");
				empresa = empresaService.findEmpresaByRazonSocial(razonSocial);
			}

			if (empresa != null) {
				result.put("result", "success");
				result.put("empresa", new JSONObject(gson.toJson(empresa)));
			} else {
				result.put("result", "error");
				result.put("resultado", "La empresa no existe.");
				this.resultado = result.toString();
				return INPUT;
			}
			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return INPUT;
		}
		
	}



	public String formBuscarEmpresa() {
		return SUCCESS;
	}

}
