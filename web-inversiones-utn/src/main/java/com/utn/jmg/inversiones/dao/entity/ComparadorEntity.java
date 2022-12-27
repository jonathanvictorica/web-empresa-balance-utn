package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table
@Getter
@Setter
public class ComparadorEntity  implements java.io.Serializable {


     private Integer idComparador;
     private String descripcion;
     private String signo;
   

    public ComparadorEntity() {
    }

	
    public ComparadorEntity(String descripcion, String signo) {
        this.descripcion = descripcion;
        this.signo = signo;
    }
    
   
    public Integer getIdComparador() {
        return this.idComparador;
    }
    
    public void setIdComparador(Integer idComparador) {
        this.idComparador = idComparador;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getSigno() {
        return this.signo;
    }
    
    public void setSigno(String signo) {
        this.signo = signo;
    }
   




}


