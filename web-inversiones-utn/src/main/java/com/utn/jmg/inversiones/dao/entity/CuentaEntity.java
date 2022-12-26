package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class CuentaEntity extends IndicadorEconomicoEntity implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCuenta;

	private String codigoCuenta;

	
	
	public CuentaEntity() {
		super();
	}

	public CuentaEntity(Long id, String nombre, String codigo) {
		this.idCuenta = id;
		this.idIndicadorEconomico=id;
		this.nombre = nombre;
		this.codigoCuenta = codigo;
	}

	public Long getIdCuenta() {
		return this.idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getCodigoCuenta() {
		return this.codigoCuenta;
	}

	public void setCodigoCuenta(String codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}

}
