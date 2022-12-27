package com.utn.jmg.inversiones.controller;

import java.util.ArrayList;
import java.util.List;

import com.utn.jmg.inversiones.service.UsuarioService;
import org.json.JSONObject;

import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;

public class HomeController extends BaseController {
	private static final long serialVersionUID = 1L;
	private final UsuarioService usuarioService;

	public HomeController(UsuarioService usuarioService) {
		super(usuarioService);
		this.usuarioService = usuarioService;
	}


	public String verLogin() throws Exception {
		obtenerSesionGlobal().put("usuario", null);
		return SUCCESS;
	}

	public String validarUsuario() {
		JSONObject datos = new JSONObject(this.resultado);
		JSONObject result = new JSONObject();
		try {
			String nickUsuario = datos.getString("nickUsuario");
			String pass = datos.getString("pass");
			usuarioService.validarUsuario(nickUsuario, pass);
			obtenerSesionGlobal().put("usuario", nickUsuario);
			return SUCCESS;
			// redireccionar el action a bienvenido
		} catch (Exception e) {
			result.put("result", "error");
			result.put("resultado", e.getMessage());
			this.resultado = result.toString();
			return INPUT;
		}

	}

	public String bienvenido() throws Exception {
		// new PantallaPrincipal();
		List<List<String>> menuPrincipal = new ArrayList<List<String>>();

//		List<String> menuBalances = new ArrayList<String>();
//		menuBalances.add("BALANCES");
//		menuBalances.add("Ver Balances");
//		menuPrincipal.add(menuBalances);

		List<String> menuEmpresa = new ArrayList<String>();
		menuEmpresa.add("EMPRESA");
		menuEmpresa.add("Buscar Empresa");
		menuPrincipal.add(menuEmpresa);

		List<String> menuMetodologia = new ArrayList<String>();
		menuMetodologia.add("METODOLOGIA");
		menuMetodologia.add("Crear Metodologia");
		menuMetodologia.add("Buscar Metodologia");
		menuPrincipal.add(menuMetodologia);

		List<String> menuIndicador = new ArrayList<String>();
		menuIndicador.add("INDICADOR");
		menuIndicador.add("Crear Indicador");
		menuIndicador.add("Buscar Indicador");
		menuPrincipal.add(menuIndicador);

		UsuarioEntity usuario = usuarioService.findByNick((String) obtenerSesionGlobal().get("usuario"));

		obtenerSesionGlobal().put("menu", menuPrincipal);
		obtenerSesionGlobal().put("nombre", usuario.getNombre());
		obtenerSesionGlobal().put("apellido", usuario.getApellido());
		
		
		String pathFoto = "'/img/users/"+usuario.getNombre().toLowerCase()+".jpg'";
		obtenerSesionGlobal().put("pathFoto", pathFoto);
		// obtenerSesionGlobal().setAttribute("rol", usuario.get);
		return SUCCESS;
	}



}
