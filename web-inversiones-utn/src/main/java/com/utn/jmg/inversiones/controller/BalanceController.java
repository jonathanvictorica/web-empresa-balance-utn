package com.utn.jmg.inversiones.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.utn.jmg.inversiones.model.Balance;
import com.utn.jmg.inversiones.model.Cuenta;
import com.utn.jmg.inversiones.model.Empresa;
import com.utn.jmg.inversiones.service.BalanceService;
import com.utn.jmg.inversiones.service.EmpresaService;
import com.utn.jmg.inversiones.service.UsuarioService;
import org.json.JSONArray;
import org.json.JSONObject;

import com.utn.jmg.inversiones.util.StringUtils;

public class BalanceController extends BaseController {

	private static final long serialVersionUID = 1L;
	private final EmpresaService empresaService;
	private final BalanceService balanceService;

	public BalanceController(UsuarioService usuarioService, EmpresaService empresaService, BalanceService balanceService) {
		super(usuarioService);
		this.empresaService = empresaService;
		this.balanceService = balanceService;
	}


	public String guardar() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Empresa empresa = empresaService.findEmpresaByCuit(datos.getString("cuit"));
			if (empresa == null) {
				throw new Exception("Empresa no existe");
			}

			Balance balance = balanceService.buscarBalance(empresa.getCuit(), sdf.parse(datos.getString("fechaComienzo")), sdf.parse(datos.getString("fechaFin")));
			if (balance != null) {
				throw new Exception("Ya existe un balance para este periodo.");
			}

			balance = new Balance(sdf.parse(datos.getString("fechaComienzo")), sdf.parse(datos.getString("fechaFin")));
			balance.setEmpresa(empresa);

			JSONArray cuentasJSON = datos.getJSONArray("cuentasBalance");
			for (int i = 0; i < cuentasJSON.length(); i++) {
				JSONObject cuenta = cuentasJSON.getJSONObject(i);
				Cuenta cuentaBalance = new Cuenta(cuenta.getString("nombre"), cuenta.getDouble("valor"));
				balance.agregarCuenta(cuentaBalance);
			}

			balanceService.guardar(balance);

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
			Empresa empresa = empresaService.findEmpresaByCuit(datos.getString("cuit"));
			if (empresa == null) {
				throw new Exception("Empresa no existe");
			}
			String periodoSeleccionado = datos.getString("periodoSeleccionado");
			Date fechaComienzo = Balance.getFechaComienzo(periodoSeleccionado);
			Date fechaFin = Balance.getFechaFin(periodoSeleccionado);
			Balance balance = balanceService.buscarBalance(empresa.getCuit(), fechaComienzo, fechaFin);
			if (balance == null) {
				throw new Exception("No existe el balance");
			}

			JSONArray cuentasJSON = datos.getJSONArray("cuentasBalance");
			for (int i = 0; i < cuentasJSON.length(); i++) {
				JSONObject cuenta = cuentasJSON.getJSONObject(i);
				Cuenta cuentaBalance = balance.buscarCuentaBalance(cuenta.getString("nombre"));
				if (cuenta.getBoolean("eliminar")) {
					if (cuentaBalance != null) {
						balance.eliminarCuenta(cuentaBalance);
					}
				} else {
					if (cuentaBalance == null) {
						cuentaBalance = new Cuenta(cuenta.getString("nombre"), cuenta.getDouble("valor"));
						balance.agregarCuenta(cuentaBalance);
					} else {
						cuentaBalance.setValor(cuenta.getDouble("valor"));
						balance.modificarCuenta(cuentaBalance);
					}
				}
			}

			balanceService.modificar(balance);
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
			Empresa empresa = empresaService.findEmpresaByCuit(datos.getString("cuit"));
			if (empresa == null) {
				throw new Exception("Empresa no existe");
			}

			String periodoSeleccionado = datos.getString("periodoSeleccionado");
			Date fechaComienzo = Balance.getFechaComienzo(periodoSeleccionado);
			Date fechaFin = Balance.getFechaFin(periodoSeleccionado);
			Balance balance = balanceService.buscarBalance(empresa.getCuit(), fechaComienzo, fechaFin);

			if (balance == null) {
				throw new Exception("No existe el balance");
			}

			balanceService.eliminar(balance);

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
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();

		try {
			Empresa empresa = empresaService.findEmpresaByCuit(datos.getString("cuit"));
			if (empresa == null) {
				throw new Exception("Empresa no existe");
			}
			String periodoSeleccionado = datos.getString("periodoSeleccionado");
			Date fechaComienzo = Balance.getFechaComienzo(periodoSeleccionado);
			Date fechaFin = Balance.getFechaFin(periodoSeleccionado);

			Balance balance = balanceService.buscarBalance(empresa.getCuit(), fechaComienzo, fechaFin);
			if (balance == null) {
				throw new Exception("No existe el balance");
			}

			result.put("result", "success");
			result.put("balance", gson.toJson(balance));

			this.resultado = StringUtils.replaceAll(result.toString(), '\\', "");
			return SUCCESS;

		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}
	}

	public String obtenerPeriodos() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();

		try {
			Empresa empresa = empresaService.findEmpresaByCuit(datos.getString("cuit"));
			if (empresa == null) {
				throw new Exception("Empresa no existe");
			}
			List<String> balance = balanceService.obtenerTodosLosPeriodosBalance(empresa.getCuit());
			JSONArray listaBalances = new JSONArray();
			for (String bal : balance) {
				listaBalances.put(bal);
			}
			result.put("result", "success");
			result.put("balance", listaBalances);
			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}
	}

	public String formCrearBalance() {
		JSONObject datos = new JSONObject(getInput());
		JSONObject result = new JSONObject();
		try {
			result.put("cuit", datos.getString("cuit"));
			
			this.resultado = result.toString();
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return SUCCESS;
		}
	}

	public void verMenuBalance() throws Exception {
		JSONObject datos = new JSONObject(getInput());
		String periodo = datos.getString("periodoSeleccionado");
		String cuit = datos.getString("cuit");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaComienzo = sdf.format(Balance.getFechaComienzo(periodo));
		String fechaCierre = sdf.format(Balance.getFechaFin(periodo));
		// new MenuBalance(fechaComienzo, fechaCierre, cuit);

	}

	public void formVerPeriodos() {
		JSONObject datos = new JSONObject(getInput());
		// new BuscarBalancesForm(datos.getString("cuit"));
	}

	public void formVerBalance() {
		JSONObject datos = new JSONObject(getInput());
		String periodo = datos.getString("periodoSeleccionado");
		String cuit = datos.getString("cuit");
		// new VerBalance(cuit, periodo);
	}

	public String obtenerTodasLasCuentas(){
		JSONObject result = new JSONObject();
		try {
			List<String> nombresCuentas = balanceService.obtenerListaCuentas();
			if (nombresCuentas.isEmpty()) {
				throw new Exception("No hay indicadores almacenados");
			}
			result.put("result", "success");
			result.put("indicadores",new JSONArray( gson.toJson(nombresCuentas)));
			this.resultado= StringUtils.replaceAll(result.toString(),'\\',"");
			return SUCCESS;
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado= result.toString();
			return SUCCESS;
		}
	}
	

}
