package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class EmpresaEntity implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEmpresa;
	private String cuit;
	private String razonSocial;
	private String descripActividad;
	private Date fechaAlta;

	public EmpresaEntity() {
	}

	public EmpresaEntity(String cuit, String razonSocial, String descripActividad, Date fechaAlta) {
		this.cuit = cuit;
		this.razonSocial = razonSocial;
		this.descripActividad = descripActividad;
		this.fechaAlta = fechaAlta;
	}

	public EmpresaEntity(Long id, String cuit, String razonSocial, String descripActividad, Date fechaAlta) {
		this.cuit = cuit;
		this.razonSocial = razonSocial;
		this.descripActividad = descripActividad;
		this.fechaAlta = fechaAlta;
		this.idEmpresa = id;
	}

	public Long getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getCuit() {
		return this.cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDescripActividad() {
		return this.descripActividad;
	}

	public void setDescripActividad(String descripActividad) {
		this.descripActividad = descripActividad;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

}
