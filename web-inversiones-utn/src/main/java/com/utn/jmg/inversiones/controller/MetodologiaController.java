package com.utn.jmg.inversiones.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.utn.jmg.inversiones.model.Empresa;
import com.utn.jmg.inversiones.model.Metodologia;
import com.utn.jmg.inversiones.model.dto.EmpresaCondicion;
import com.utn.jmg.inversiones.model.dto.EmpresaResultado;
import com.utn.jmg.inversiones.model.metodologia.CondicionFactory;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;
import com.utn.jmg.inversiones.service.*;
import org.json.JSONArray;
import org.json.JSONObject;

import com.utn.jmg.inversiones.model.Balance;
import com.utn.jmg.inversiones.model.dto.CondicionDto;

public class MetodologiaController extends BaseController {

	private static final long serialVersionUID = 1L;
	private final MetodologiaService metodologiaService;
	private final IndicadorService indicadorService;
	private final BalanceService balanceService;
	private final EmpresaService empresaService;

	public MetodologiaController(UsuarioService usuarioService, MetodologiaService metodologiaService, IndicadorService indicadorService, BalanceService balanceService, EmpresaService empresaService) {
		super(usuarioService);
		this.metodologiaService = metodologiaService;
		this.indicadorService = indicadorService;
		this.balanceService = balanceService;
		this.empresaService = empresaService;
	}

	public String aplicarCondicionesTaxativas() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();
		try {
			String nombreMetodologia = datos.getString("nombreMetodologia");
			Metodologia metodologia = metodologiaService.buscarMetodologiaPorNombre(nombreMetodologia,this.obtenerUsuarioLogueado().getNick());
			if (metodologia == null) {
				throw new Exception("No existe la metodologia");
			}
			List<EmpresaCondicion> empresasCondicion = obtenerEmpresasSeleccionadas(datos);
			List<EmpresaResultado> empresasResultado = metodologia.aplicarCondicionesTaxativas(empresasCondicion);

			JSONArray resultados = new JSONArray();
			for (EmpresaResultado empResultado : empresasResultado) {
				resultados.put(empResultado.resultadoCondicionTaxativa());
			}
			result.put("resultadoCondicion", resultados);
			result.put("result", "success");
			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}
	}

	public String aplicarCondicionPriorizable() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();
		try {
			String nombreMetodologia = datos.getString("nombreMetodologia");
			Metodologia metodologia = metodologiaService.buscarMetodologiaPorNombre(nombreMetodologia,this.obtenerUsuarioLogueado().getNick());
			if (metodologia == null) {
				throw new Exception("No existe la metodologia");
			}
			List<EmpresaCondicion> empresasCondicion = obtenerEmpresasSeleccionadas(datos);
			List<EmpresaResultado> empresasResultado = metodologia.aplicarCondicionPriorizable(empresasCondicion, datos.getString("condicionPriorizable"));

			JSONArray resultados = new JSONArray();
			for (EmpresaResultado empResultado : empresasResultado) {
				resultados.put(empResultado.resultadoCondicionPriorizable());
			}

			JSONArray cabeceraFila = new JSONArray();
			cabeceraFila.put("Empresa");
			CondicionPriorizable condicion = metodologia.buscarCondicionPriorizable(datos.getString("condicionPriorizable"));
			for (int i = 0; i < condicion.getCondicionTaxativa().size(); i++) {
				cabeceraFila.put(condicion.getCondicionTaxativa().get(i).getDescripcion());
			}

			result.put("cabeceras", cabeceraFila);
			result.put("resultadoCondicion",resultados);
			result.put("result", "success");
			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}
	}

	private List<EmpresaCondicion> obtenerEmpresasSeleccionadas(JSONObject datos) throws Exception {

		List<EmpresaCondicion> empresasCondicion = new ArrayList<EmpresaCondicion>();
		JSONArray empresasSeleccionadas = datos.getJSONArray("empresasSeleccionadas");
		for (int i = 0; i < empresasSeleccionadas.length(); i++) {
			JSONObject empresaSeleccionada = empresasSeleccionadas.getJSONObject(i);

			EmpresaCondicion empresaCondicion = new EmpresaCondicion();

			Empresa empresa = empresaService.findEmpresaByCuit(empresaSeleccionada.getString("cuit"));
			if (empresa == null) {
				throw new Exception("La empresa con cuit " + empresaSeleccionada.getString("cuit") + " no existe.");
			}

			empresaCondicion.setEmpresa(empresa);

			JSONArray balancesSeleccionados = empresaSeleccionada.getJSONArray("balancesSeleccionados");
			for (int j = 0; j < balancesSeleccionados.length(); j++) {
				String balanceSeleccionado = balancesSeleccionados.getString(j);
				Date fechaComienzo = Balance.getFechaComienzo(balanceSeleccionado);
				Date fechaFin = Balance.getFechaFin(balanceSeleccionado);

				Balance balanceBuscado = balanceService.buscarBalance(empresa.getCuit(), fechaComienzo, fechaFin);
				empresaCondicion.getBalances().add(balanceBuscado);
			}
			empresasCondicion.add(empresaCondicion);
		}
		return empresasCondicion;
	}

	public String guardar() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();
		try {
			guardarMetodologia(datos);
			result.put("result", "success");
			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}
	}

	public String modificar() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();
		try {
			Metodologia metodologia = metodologiaService.buscarMetodologiaPorNombre(datos.getString("nombreMetodologia"),this.obtenerUsuarioLogueado().getNick());
			if (metodologia == null) {
				throw new Exception("No existe la metodologia que se quiere modificar.");
			}

			if (!datos.getString("nombreModificadoMetodologia").equalsIgnoreCase(datos.getString("nombreMetodologia"))) {
				Metodologia metodologiaExiste = metodologiaService.buscarMetodologiaPorNombre(datos.getString("nombreModificadoMetodologia"),this.obtenerUsuarioLogueado().getNick());
				if (metodologiaExiste != null) {
					throw new Exception("No se puede guardar la metodologia con ese nombre porque ya existe otra metodologia identificada de la misma manera..");
				}
			}
			metodologiaService.eliminar(metodologia);
			datos.put("nombreMetodologia", datos.getString("nombreModificadoMetodologia"));
			guardarMetodologia(datos);
			result.put("result", "success");
			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}
	}

	public String eliminar() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();
		try {
			Metodologia metodologia = metodologiaService.buscarMetodologiaPorNombre(datos.getString("nombreModificadoMetodologia"),this.obtenerUsuarioLogueado().getNick());
			if (metodologia == null) {
				throw new Exception("No existe la metodologia que se quiere eliminar.");
			}
			metodologiaService.eliminar(metodologia);
			result.put("result", "success");
			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}
	}

	public String buscarMetodologia() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();
		try {
			Metodologia metodologia = metodologiaService.buscarMetodologiaPorNombre(datos.getString("nombreMetodologia"),this.obtenerUsuarioLogueado().getNick());
			if (metodologia == null) {
				throw new Exception("No existe la metodologia.");
			}

			result.put("result", "success");
			result.put("metodologia", new JSONObject(gson.toJson(metodologia)));
			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}

	}

	public String obtenerTodasLasMetodologias() {
		JSONObject result = new JSONObject();
		try {
			List<Metodologia> metodologias = metodologiaService.allByUser(this.obtenerUsuarioLogueado().getNick());
			result.put("result", "success");
			result.put("metodologias", new JSONArray(gson.toJson(metodologias)));
			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}

	}

	private void guardarMetodologia(JSONObject datos) throws Exception {

		Metodologia metodologia = new Metodologia();

		CondicionFactory condicionFactory = new CondicionFactory();

		metodologia.setNombre(datos.getString("nombreMetodologia"));

		JSONArray condiciones = datos.getJSONArray("condicionesTaxativas");
		for (int i = 0; i < condiciones.length(); i++) {
			JSONObject condicion = condiciones.getJSONObject(i);
			CondicionDto condicionDto = gson.fromJson(condicion.toString(), CondicionDto.class);
			CondicionTaxativa condicionTaxativa = (CondicionTaxativa) condicionFactory.createCondicion(condicionDto, null);

			metodologia.agregarCondicionTaxativa(condicionTaxativa);
		}

		condiciones = datos.getJSONArray("condicionesPriorizables");
		for (int i = 0; i < condiciones.length(); i++) {
			JSONObject condicion = condiciones.getJSONObject(i);
			CondicionDto condicionDto = gson.fromJson(condicion.toString(), CondicionDto.class);
			CondicionPriorizable condicionPriorizable = (CondicionPriorizable) condicionFactory.createCondicionPriorizable(condicionDto, metodologia.getCondicionesTaxativas());

			metodologia.agregarCondicionPriorizable(condicionPriorizable);
		}

		metodologia.setUsuario(this.obtenerUsuarioLogueado());
		metodologiaService.guardar(metodologia);
	}

	public String formAplicarMetodologia() throws Exception {
		JSONObject datos = new JSONObject(this.resultado);
		String nombreMetodologia = datos.getString("nombreMetodologia");

		Metodologia metodologia = this.metodologiaService.buscarMetodologiaPorNombre(nombreMetodologia,this.obtenerUsuarioLogueado().getNick());
		JSONArray condicionesTaxativas = new JSONArray();
		for (CondicionTaxativa cond : metodologia.getCondicionesTaxativas()) {
			condicionesTaxativas.put(new JSONObject(gson.toJson(cond.getCondicionDto())));
		}

		JSONArray condicionesPriorizable = new JSONArray();
		for (CondicionPriorizable cond : metodologia.getCondicionesPriorizables()) {
			condicionesPriorizable.put(new JSONObject(gson.toJson(cond.getCondicionDto())));
		}

		JSONObject datosEnviar = new JSONObject();
		datosEnviar.put("condicionesTaxativas", condicionesTaxativas);
		datosEnviar.put("condicionesPriorizables", condicionesPriorizable);
		datosEnviar.put("metodologia",new JSONObject(gson.toJson(metodologia)));
		this.resultado = datosEnviar.toString();
		// new AplicarCondicion(new JSONObject(gson.toJson(metodologia)),
		// condicionesTaxativas, condicionesPriorizable);
		return SUCCESS;
	}

	public String formBuscarMetodologia() {
		return SUCCESS;
	}

	public String formCrearMetodologia() {
		return SUCCESS;
	}

	public String formModificarMetodologia() throws Exception {
		JSONObject datos = new JSONObject(this.resultado);
		String nombreMetodologia = datos.getString("nombreMetodologia");

		Metodologia metodologia = this.metodologiaService.buscarMetodologiaPorNombre(nombreMetodologia,this.obtenerUsuarioLogueado().getNick());
		JSONArray condicionesTaxativas = new JSONArray();
		for (CondicionTaxativa cond : metodologia.getCondicionesTaxativas()) {
			condicionesTaxativas.put(new JSONObject(gson.toJson(cond.getCondicionDto())));
		}

		JSONArray condicionesPriorizable = new JSONArray();
		for (CondicionPriorizable cond : metodologia.getCondicionesPriorizables()) {
			condicionesPriorizable.put(new JSONObject(gson.toJson(cond.getCondicionDto())));
		}

		JSONObject datosEnviar = new JSONObject();
		datosEnviar.put("condicionesTaxativas", condicionesTaxativas);
		datosEnviar.put("condicionesPriorizables", condicionesPriorizable);
		datosEnviar.put("metodologia", new JSONObject(gson.toJson(metodologia)));
		this.resultado = datosEnviar.toString();

		// TODO: Aca en la pantalla de metodologiaForm, va ver que invocar
		// un metodo en el javascript onload para llenar estos datos.
		return SUCCESS;

		// new ModificarMetodologia(condicionesTaxativas.toString(),
		// condicionesPriorizable.toString(), gson.toJson(metodologia));
	}


}
