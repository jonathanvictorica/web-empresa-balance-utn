package com.utn.jmg.inversiones.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.utn.jmg.inversiones.model.Cuenta;
import com.utn.jmg.inversiones.model.Empresa;
import com.utn.jmg.inversiones.model.Indicador;
import com.utn.jmg.inversiones.service.BalanceService;
import com.utn.jmg.inversiones.service.EmpresaService;
import com.utn.jmg.inversiones.service.IndicadorService;
import com.utn.jmg.inversiones.service.UsuarioService;
import org.json.JSONArray;
import org.json.JSONObject;

import com.utn.jmg.inversiones.model.Balance;
import com.utn.jmg.inversiones.util.FormulaUtils;
import com.utn.jmg.inversiones.util.StringUtils;

public class IndicadorController extends BaseController {
	private static final long serialVersionUID = 1L;
	private final IndicadorService indicadorService;
	private final EmpresaService empresaService;
	private final BalanceService balanceService;

	public IndicadorController(UsuarioService usuarioService, IndicadorService indicadorService, EmpresaService empresaService, BalanceService balanceService) {
		super(usuarioService);
		this.indicadorService = indicadorService;
		this.empresaService = empresaService;
		this.balanceService = balanceService;
	}

	public String guardar() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();
		try {
			Indicador indicador = indicadorService.buscarIndicador(datos.getString("nombre"), this.obtenerUsuarioLogueado().getNick());
			if (indicador != null) {
				throw new Exception("El indicador ya existe, por favor ingrese con otro nombre");
			}
			FormulaUtils.validarFormula(datos.getString("formula"));
			indicador = new Indicador(datos.getString("nombre"), datos.getString("formula"));
			indicador.setUsuario(this.obtenerUsuarioLogueado());
			indicadorService.guardar(indicador);
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

	public String modificar() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();
		try {
			Indicador indicador = indicadorService.buscarIndicador(datos.getString("nombreAntiguo"), this.obtenerUsuarioLogueado().getNick());
			if (indicador == null) {
				throw new Exception("El indicador no existe.");
			}
			if (!datos.getString("nombreAntiguo").equalsIgnoreCase(datos.getString("nombre"))) {
				Indicador indicadorBuscar = indicadorService.buscarIndicador(datos.getString("nombre"), this.obtenerUsuarioLogueado().getNick());
				if (indicadorBuscar != null) {
					throw new Exception("Ya existe un indicador con ese nombre.");
				}
			}
			FormulaUtils.validarFormula(datos.getString("formula"));
			indicador.setFormula(datos.getString("formula"));
			indicador.setNombre(datos.getString("nombre"));
			indicadorService.modificar(indicador);
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

	public String eliminar() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();
		try {
			Indicador indicador = indicadorService.buscarIndicador(datos.getString("nombre"), this.obtenerUsuarioLogueado().getNick());
			if (indicador == null) {
				throw new Exception("El indicador no existe.");
			}
			indicadorService.eliminar(indicador);
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

	public String buscar() {
		JSONObject datos = new JSONObject(resultado);
		JSONObject result = new JSONObject();
		try {

			String nombre = datos.getString("nombre");
			Indicador indicador = indicadorService.buscarIndicador(nombre, this.obtenerUsuarioLogueado().getNick());

			if (indicador != null) {
				result.put("result", "success");
				result.put("indicador", new JSONObject(gson.toJson(indicador)));
			} else {
				result.put("result", "error");
				result.put("resultado", "El indicador no existe.");
			}
			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}
	}

	public String aplicarIndicador() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();
		try {

			String nombre = datos.getString("nombre");
			Indicador indicador = indicadorService.buscarIndicador(nombre, this.obtenerUsuarioLogueado().getNick());

			if (indicador == null) {
				throw new Exception("No existe el indicador");
			}

			String cuit = datos.getString("cuit");
			Empresa empresa = empresaService.findEmpresaByCuit(cuit);

			String periodoSeleccionado = datos.getString("periodoSeleccionado");
			Date fechaComienzo = Balance.getFechaComienzo(periodoSeleccionado);
			Date fechaFin = Balance.getFechaFin(periodoSeleccionado);

			Balance balance = balanceService.buscarBalance(empresa.getCuit(), fechaComienzo, fechaFin);
			if (balance == null) {
				throw new Exception("No existe el balance");
			}
			List<Cuenta> cuentas = new ArrayList<Cuenta>();
			if (indicador.getUsuario() == null) {
				indicador.setUsuario(this.obtenerUsuarioLogueado());
			}
			Double valor = indicador.aplicarIndicador(empresa, balance, cuentas);
			result.put("result", "success");
			result.put("cuentas", new JSONArray(gson.toJson(cuentas)));
			result.put("resultadoIndicador", valor);
			System.out.println("\n" + result.toString());
			this.resultado = StringUtils.replaceAll(result.toString(), '\\', "");
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}
	}

	public String formCrearIndicador() {
		return SUCCESS;
	}

	public String formModificarIndicador() {
		// JSONObject datos = new JSONObject(getInput());
		// new ModificarIndicadorForm(datos.getString("nombreIndicador"));

		this.buscar();
		return SUCCESS;
	}

	public String obtenerTodosLosIndicadores() {
		JSONObject result = new JSONObject(this.getInput());
		try {
			List<Indicador> indicadores = indicadorService.allIndicadoresByUser(this.obtenerUsuarioLogueado().getNick(), result.getBoolean("nativos"));
			if (indicadores.isEmpty()) {
				throw new Exception("No hay indicadores almacenados");
			}
			result.put("result", "success");
			result.put("indicadores", new JSONArray(gson.toJson(indicadores)));
			this.resultado = StringUtils.replaceAll(result.toString(), '\\', "");
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}
	}

	public String formBuscarIndicador() {
		return SUCCESS;

	}

	public String formAplicarIndicador() {
		// JSONObject datos = new JSONObject(getInput());
		// String periodo = datos.getString("periodoSeleccionado");
		// String cuit = datos.getString("cuit");
		// this.resultado=getInput();
		return SUCCESS;

	}



}
