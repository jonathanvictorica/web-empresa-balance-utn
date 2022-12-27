package com.utn.jmg.inversiones.controller;

import com.utn.jmg.inversiones.model.Metodologia;
import com.utn.jmg.inversiones.model.metodologia.CondicionFactory;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;
import com.utn.jmg.inversiones.service.CondicionPriorizableService;
import com.utn.jmg.inversiones.service.CondicionTaxativaService;
import com.utn.jmg.inversiones.service.MetodologiaService;
import com.utn.jmg.inversiones.service.UsuarioService;
import org.json.JSONObject;

import com.utn.jmg.inversiones.model.dto.CondicionDto;

public class CondicionController extends BaseController {

	private final CondicionTaxativaService condicionTaxativaService;
	private final CondicionPriorizableService condicionPriorizableService;
	private final MetodologiaService metodologiaService;
	private final CondicionFactory condicionFactory;

	public CondicionController(UsuarioService usuarioService, CondicionTaxativaService condicionTaxativaService, CondicionPriorizableService condicionPriorizableService, MetodologiaService metodologiaService, CondicionFactory condicionFactory) {
		super(usuarioService);
		this.condicionTaxativaService = condicionTaxativaService;
		this.condicionPriorizableService = condicionPriorizableService;
		this.metodologiaService = metodologiaService;
		this.condicionFactory = condicionFactory;
	}

	public String guardarCondicionTaxativaDeMetodologia() {
		JSONObject datos = new JSONObject(this.resultado != null && !this.resultado.isEmpty() ? this.resultado : this.getInput());
		JSONObject result = new JSONObject();
		try {
			JSONObject condicion = datos.getJSONObject("condicion");
			CondicionDto condicionDto = gson.fromJson(condicion.toString(), CondicionDto.class);
			CondicionTaxativa condicionTaxativa = null;

			condicionTaxativa = condicionTaxativaService.findByDescripcionByMetodologia(condicionDto.getDescripcionCondicion(), datos.getLong("Id_Metodologia"));
			if (condicionTaxativa != null) {
				throw new Exception("Ya existe una condicion taxativa con ese nombre");
			}

			condicionTaxativa = (CondicionTaxativa) condicionFactory.createCondicion(condicionDto, null);
			condicionTaxativaService.guardar(condicionTaxativa);

			result.put("result", "success");
			this.resultado = result.toString();
			result.put("result", "success");
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return ERROR;
		}
	}

	public String guardarCondicionPriorizableDeMetodologia() {
		JSONObject datos = new JSONObject(this.resultado != null && !this.resultado.isEmpty() ? this.resultado : this.getInput());
		JSONObject result = new JSONObject();
		try {
			JSONObject condicion = datos.getJSONObject("condicion");
			CondicionDto condicionDto = gson.fromJson(condicion.toString(), CondicionDto.class);
			CondicionPriorizable condicionPriorizable = null;

			condicionPriorizable = condicionPriorizableService.findByDescripcionByMetodologia(condicionDto.getDescripcionCondicion(), datos.getLong("Id_Metodologia"));
			if (condicionPriorizable != null) {
				throw new Exception("Ya existe una condicion priorizable con ese nombre");
			}

			Metodologia metodologia = metodologiaService.buscarMetodologiaPorNombre(datos.getString("nombre_Metodologia"), this.obtenerUsuarioLogueado().getNick());
			condicionPriorizable = (CondicionPriorizable) condicionFactory.createCondicion(condicionDto, metodologia.getCondicionesTaxativas());
			condicionPriorizableService.guardar(condicionPriorizable);
			result.put("result", "success");
			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return ERROR;
		}
	}

	public String modificarCondicionTaxativaDeMetodologia() {
		JSONObject datos = new JSONObject(this.resultado != null && !this.resultado.isEmpty() ? this.resultado : this.getInput());
		JSONObject result = new JSONObject();
		try {
			JSONObject condicion = datos.getJSONObject("condicion");
			CondicionDto condicionDto = gson.fromJson(condicion.toString(), CondicionDto.class);
			CondicionTaxativa condicionTaxativa = null;

			condicionTaxativa = condicionTaxativaService.findByDescripcionByMetodologia(condicionDto.getDescripcionCondicion(), datos.getLong("Id_Metodologia"));
			if (condicionTaxativa == null) {
				throw new Exception("No existe una condicion taxativa con ese nombre");
			}
			Long id = condicionTaxativa.getId();
			condicionTaxativa = (CondicionTaxativa) condicionFactory.createCondicion(condicionDto, null);
			condicionTaxativa.setId(id);
			condicionTaxativaService.modificar(condicionTaxativa);

			result.put("result", "success");
			this.resultado = result.toString();
			result.put("result", "success");

			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return ERROR;
		}
	}

	public String modificarCondicionPriorizableDeMetodologia() {
		JSONObject datos = new JSONObject(this.resultado != null && !this.resultado.isEmpty() ? this.resultado : this.getInput());
		JSONObject result = new JSONObject();
		try {
			JSONObject condicion = datos.getJSONObject("condicion");
			CondicionDto condicionDto = gson.fromJson(condicion.toString(), CondicionDto.class);
			CondicionPriorizable condicionPriorizable = null;

			condicionPriorizable = condicionPriorizableService.findByDescripcionByMetodologia(condicionDto.getDescripcionCondicion(), datos.getLong("Id_Metodologia"));
			if (condicionPriorizable != null) {
				throw new Exception("Ya existe una condicion priorizable con ese nombre");
			}

			Long id = condicionPriorizable.getId();
			Metodologia metodologia = metodologiaService.buscarMetodologiaPorNombre(datos.getString("nombre_Metodologia"), this.obtenerUsuarioLogueado().getNick());
			condicionPriorizable = (CondicionPriorizable) condicionFactory.createCondicion(condicionDto, metodologia.getCondicionesTaxativas());
			condicionPriorizable.setId(id);

			condicionPriorizableService.modificar(condicionPriorizable);
			result.put("result", "success");

			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return ERROR;
		}
	}

	public String eliminarCondicionTaxativaDeMetodologia() {
		JSONObject datos = new JSONObject(this.resultado != null && !this.resultado.isEmpty() ? this.resultado : this.getInput());
		JSONObject result = new JSONObject();
		try {
			JSONObject condicion = datos.getJSONObject("condicion");
			CondicionDto condicionDto = gson.fromJson(condicion.toString(), CondicionDto.class);
			CondicionTaxativa condicionTaxativa = null;

			condicionTaxativa = condicionTaxativaService.findByDescripcionByMetodologia(condicionDto.getDescripcionCondicion(), datos.getLong("Id_Metodologia"));
			if (condicionTaxativa == null) {
				throw new Exception("No existe una condicion taxativa con ese nombre");
			}

			Long id = condicionTaxativa.getId();
			condicionTaxativa = (CondicionTaxativa) condicionFactory.createCondicion(condicionDto, null);
			condicionTaxativa.setId(id);
			condicionTaxativaService.eliminar(condicionTaxativa);

			result.put("result", "success");
			this.resultado = result.toString();
			result.put("result", "success");

			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return ERROR;
		}
	}

	public String eliminarCondicionPriorizableDeMetodologia() {
		JSONObject datos = new JSONObject(this.resultado != null && !this.resultado.isEmpty() ? this.resultado : this.getInput());
		JSONObject result = new JSONObject();
		try {

			JSONObject condicion = datos.getJSONObject("condicion");
			CondicionDto condicionDto = gson.fromJson(condicion.toString(), CondicionDto.class);
			CondicionPriorizable condicionPriorizable = null;

			condicionPriorizable = condicionPriorizableService.findByDescripcionByMetodologia(condicionDto.getDescripcionCondicion(), datos.getLong("Id_Metodologia"));
			if (condicionPriorizable == null) {
				throw new Exception("No existe una condicion Priorizable con ese nombre");
			}

			Long id = condicionPriorizable.getId();
			Metodologia metodologia = metodologiaService.buscarMetodologiaPorNombre(datos.getString("nombre_Metodologia"), this.obtenerUsuarioLogueado().getNick());
			condicionPriorizable = (CondicionPriorizable) condicionFactory.createCondicion(condicionDto, metodologia.getCondicionesTaxativas());
			condicionPriorizable.setId(id);

			condicionPriorizableService.eliminar(condicionPriorizable);
			result.put("result", "success");

			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return ERROR;
		}
	}


}
