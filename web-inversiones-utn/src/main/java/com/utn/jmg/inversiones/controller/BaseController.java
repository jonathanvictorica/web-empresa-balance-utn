package com.utn.jmg.inversiones.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;
import com.utn.jmg.inversiones.service.UsuarioService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.*;

@Setter
@Getter
public class BaseController extends ActionSupport {

	private static final long serialVersionUID = 1L;
	protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	protected String resultado = null;


	private final  UsuarioService usuarioService;

	public BaseController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	protected HashMap<String,Object> obtenerSesionGlobal() throws Exception {
//		if (request.getSession() == null) {
//			request.changeSessionId();
//			//throw new Exception("Se termino el tiempo de sesion, por favor volverse a loguear.");
//		}
//		return request.getSession();
		HashMap re= new HashMap();
		re.put("usuario","Jonathan");
		return re;
	}

	protected UsuarioEntity obtenerUsuarioLogueado() throws Exception {
		String nick = (String) obtenerSesionGlobal().get("usuario");
		return usuarioService.findByNick(nick);

	}


	protected String getInput() {
		return request.getParameter("datos");
	}

}
