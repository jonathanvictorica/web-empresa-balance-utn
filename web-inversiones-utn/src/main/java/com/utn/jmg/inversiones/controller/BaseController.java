package com.utn.jmg.inversiones.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utn.jmg.inversiones.service.IUsuarioService;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;
import com.opensymphony.xwork2.ActionSupport;

public class BaseController extends ActionSupport {

	private static final long serialVersionUID = 1L;
	protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	protected String resultado = null;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private IUsuarioService usuarioService;

	protected HttpSession obtenerSesionGlobal() throws Exception {
		if (request.getSession() == null) {
			throw new Exception("Se termino el tiempo de sesion, por favor volverse a loguear.");
		}
		return request.getSession();
	}

	protected UsuarioEntity obtenerUsuarioLogueado() throws Exception {
		String nick = (String) obtenerSesionGlobal().getAttribute("usuario");
		return usuarioService.findByNick(nick);

	}

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	protected Gson getGson() {
		return gson;
	}

	protected void setGson(Gson gson) {
		this.gson = gson;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	protected String getInput() {
		return request.getParameter("datos");
	}

}
