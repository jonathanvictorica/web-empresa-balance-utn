package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1


import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

//@Entity
//@Table
@Getter
@Setter
public class MetodologiaEntity implements java.io.Serializable {


    private Long idMetodologia;
    private UsuarioEntity usuario;
    private String nombre;
    private Set<CondicionPriorizableEntity> condicionPriorizables = new HashSet(0);
    private Set<CondicionTaxativaEntity> condicionTaxativas = new HashSet(0);

    public MetodologiaEntity() {
    }

	
    public MetodologiaEntity(UsuarioEntity usuario, String nombre) {
        this.usuario = usuario;
        this.nombre = nombre;
    }
    public MetodologiaEntity(UsuarioEntity usuario, String nombre, Set condicionPriorizables, Set condicionTaxativas) {
       this.usuario = usuario;
       this.nombre = nombre;
       this.condicionPriorizables = condicionPriorizables;
       this.condicionTaxativas = condicionTaxativas;
    }
   
    public MetodologiaEntity(Long id, String nombre2) {
		this.idMetodologia=id;
		this.nombre=nombre2;
	}


}


