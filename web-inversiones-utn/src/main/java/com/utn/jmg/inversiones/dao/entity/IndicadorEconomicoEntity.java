package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class IndicadorEconomicoEntity implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long idIndicadorEconomico;
	protected String nombre;

	protected String discriminador;

	public String getDiscriminador() {
		return discriminador;
	}

	public void setDiscriminador(String discriminador) {
		this.discriminador = discriminador;
	}

	public IndicadorEconomicoEntity() {
	}

	public IndicadorEconomicoEntity(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdIndicadorEconomico() {
		return this.idIndicadorEconomico;
	}

	public void setIdIndicadorEconomico(Long idIndicadorEconomico) {
		this.idIndicadorEconomico = idIndicadorEconomico;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
