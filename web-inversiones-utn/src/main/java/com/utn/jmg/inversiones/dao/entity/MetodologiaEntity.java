package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class MetodologiaEntity  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


	public Long getIdMetodologia() {
        return this.idMetodologia;
    }
    
    public void setIdMetodologia(Long idMetodologia) {
        this.idMetodologia = idMetodologia;
    }
    public UsuarioEntity getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set<CondicionPriorizableEntity> getCondicionPriorizables() {
        return this.condicionPriorizables;
    }
    
    public void setCondicionPriorizables(Set<CondicionPriorizableEntity> condicionPriorizables) {
        this.condicionPriorizables = condicionPriorizables;
    }
    public Set<CondicionTaxativaEntity> getCondicionTaxativas() {
        return this.condicionTaxativas;
    }
    
    public void setCondicionTaxativas(Set<CondicionTaxativaEntity> condicionTaxativas) {
        this.condicionTaxativas = condicionTaxativas;
    }




}


