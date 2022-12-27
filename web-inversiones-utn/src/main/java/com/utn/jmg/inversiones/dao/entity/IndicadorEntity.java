package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Entity
//@Table
@Getter
@Setter
public class IndicadorEntity extends IndicadorEconomicoEntity  implements java.io.Serializable {


     private Long idIndicador;
    
     private UsuarioEntity usuario;
     private String formula;

    public IndicadorEntity() {
    }

  
    public IndicadorEntity(Long id, String nombre, String formula2) {
		this.idIndicador=id;
		this.idIndicadorEconomico=id;
		this.nombre=nombre;
		this.formula=formula2;
		
	}


	public Long getIdIndicador() {
        return this.idIndicador;
    }
    
    public void setIdIndicador(Long idIndicador) {
        this.idIndicador = idIndicador;
    }
  
    public UsuarioEntity getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    public String getFormula() {
        return this.formula;
    }
    
    public void setFormula(String formula) {
        this.formula = formula;
    }


	



}


