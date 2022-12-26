package com.utn.jmg.inversiones.model;

import java.util.ArrayList;
import java.util.List;

import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;
import com.utn.jmg.inversiones.model.dto.EmpresaCondicion;
import com.utn.jmg.inversiones.model.dto.EmpresaResultado;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;

public class Metodologia {
	private Long id;
	private String nombre;

	private List<CondicionTaxativa> condicionesTaxativas;
	private List<CondicionPriorizable> condicionesPriorizables;
	private UsuarioEntity usuario;

	public Metodologia() {
		condicionesTaxativas = new ArrayList<CondicionTaxativa>();
		condicionesPriorizables = new ArrayList<CondicionPriorizable>();
	}

	public Metodologia(String nombre) {
		this.nombre = nombre;
	}

	public Metodologia(Long id, String nombre) {
		this.nombre = nombre;
		this.id = id;

		condicionesTaxativas = new ArrayList<CondicionTaxativa>();
		condicionesPriorizables = new ArrayList<CondicionPriorizable>();
	}

	public boolean compareTo(Metodologia metodologia) {
		return getNombre().equals(metodologia.getNombre());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void agregarCondicionTaxativa(CondicionTaxativa condicionTaxativa) {
		this.getCondicionesTaxativas().add(condicionTaxativa);

	}

	public void agregarCondicionPriorizable(CondicionPriorizable condicionPriorizable) {
		this.getCondicionesPriorizables().add(condicionPriorizable);

	}

	public void inicializarMetodologia() {
		// MetodologiaDto met = new MetodologiaDto();
		// met.setNombreMetodologia(this.getNombre());
		// this.getCondicionesTaxativas()
		// .forEach(condicionTaxativa ->
		// getCondicioneses().add(condicionTaxativa.getCondicionDto()));
		// this.getCondicionesPriorizables()
		// .forEach(condicionPriorizable ->
		// getCondicioneses().add(condicionPriorizable.getCondicionDto()));

	}

	public List<CondicionTaxativa> getCondicionesTaxativas() {
		return condicionesTaxativas;
	}

	public void setCondicionesTaxativas(List<CondicionTaxativa> condicionesTaxativas) {
		this.condicionesTaxativas = condicionesTaxativas;
	}

	public List<CondicionPriorizable> getCondicionesPriorizables() {
		return condicionesPriorizables;
	}

	public void setCondicionesPriorizables(List<CondicionPriorizable> condicionesPriorizables) {
		this.condicionesPriorizables = condicionesPriorizables;
	}

	public CondicionPriorizable buscarCondicionPriorizable(String nombre) {
		try {
			return this.getCondicionesPriorizables().stream().filter(cond -> cond.getDescripcion().equalsIgnoreCase(nombre)).findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}

	public CondicionTaxativa buscarCondicionTaxativa(String nombre) {
		try {
			return this.getCondicionesTaxativas().stream().filter(cond -> cond.getDescripcion().equalsIgnoreCase(nombre)).findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}

	public List<EmpresaResultado> aplicarCondicionesTaxativas(List<EmpresaCondicion> empresasCondicion) {
		List<EmpresaResultado> empresasResultado = new ArrayList<EmpresaResultado>();
		empresasCondicion.forEach(empresaCondicion -> agregarDescripcionDeCondicionA(empresaCondicion, empresasResultado));
		return empresasResultado;
	}

	public void agregarDescripcionDeCondicionA(EmpresaCondicion empresaCondicion, List<EmpresaResultado> empresasResultado) {
		EmpresaResultado empresaResultado = new EmpresaResultado();
		empresaResultado.setEmpresa(empresaCondicion.getEmpresa());
		Boolean control = false;
		this.condicionesTaxativas.forEach(condicionTaxativa -> agregarDependiendoSiCumple(condicionTaxativa, empresaResultado, empresaCondicion, control));
		// empresaResultado.getCumpleCondicionesTaxativas()
		// if (control.equals(false))
		// empresaResultado.setCumpleCondicionesTaxativas(true);

		empresasResultado.add(empresaResultado);
	}

	public void agregarDependiendoSiCumple(CondicionTaxativa condicionTaxativa, EmpresaResultado empresaResultado, EmpresaCondicion empresaCondicion, Boolean control) {
		if (condicionTaxativa.empresaCumple(empresaCondicion).equals(false)) {
			empresaResultado.setCumpleCondicionesTaxativas(false);
			empresaResultado.getCumpleCondicion().add(false);
			empresaResultado.getNombreCondicion().add(condicionTaxativa.getDescripcion());
			control = true;
		} else {
			empresaResultado.getCumpleCondicion().add(true);
			empresaResultado.getNombreCondicion().add(condicionTaxativa.getDescripcion());
		}
	}

	public List<EmpresaResultado> aplicarCondicionPriorizable(List<EmpresaCondicion> empresasCondicion, String nombreCondicionPriorizable) {
		CondicionPriorizable condicionPriorizable = this.buscarCondicionPriorizable(nombreCondicionPriorizable);
		return condicionPriorizable.cualConvieneInvertir(empresasCondicion);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

}
