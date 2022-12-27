package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Entity
//@Table
@Getter
@Setter
@NoArgsConstructor
public class CuentaEntity extends IndicadorEconomicoEntity implements java.io.Serializable {

	private Long idCuenta;

	private String codigoCuenta;

	
	


	public CuentaEntity(Long id, String nombre, String codigo) {
		this.idCuenta = id;
		this.idIndicadorEconomico=id;
		this.nombre = nombre;
		this.codigoCuenta = codigo;
	}



}
