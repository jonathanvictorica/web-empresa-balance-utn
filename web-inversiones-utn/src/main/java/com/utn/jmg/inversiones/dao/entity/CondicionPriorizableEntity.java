package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

//@Entity
//@Table
@Getter
@Setter
public class CondicionPriorizableEntity  implements java.io.Serializable {


     private Long idCondicionPriorizable;
     private MetodologiaEntity metodologia;
     private String nombreCondicion;
     private Set<CondicionesTaxativasRelacionadasEntity> condicionesTaxativasRelacionadases = new HashSet(0);

    public CondicionPriorizableEntity() {
    }

	
    public CondicionPriorizableEntity(MetodologiaEntity metodologia, String nombreCondicion) {
        this.metodologia = metodologia;
        this.nombreCondicion = nombreCondicion;
    }
    public CondicionPriorizableEntity(MetodologiaEntity metodologia, String nombreCondicion, Set condicionesTaxativasRelacionadases) {
       this.metodologia = metodologia;
       this.nombreCondicion = nombreCondicion;
       this.condicionesTaxativasRelacionadases = condicionesTaxativasRelacionadases;
    }
   
    public Long getIdCondicionPriorizable() {
        return this.idCondicionPriorizable;
    }
    
    public void setIdCondicionPriorizable(Long idCondicionPriorizable) {
        this.idCondicionPriorizable = idCondicionPriorizable;
    }
    public MetodologiaEntity getMetodologia() {
        return this.metodologia;
    }
    
    public void setMetodologia(MetodologiaEntity metodologia) {
        this.metodologia = metodologia;
    }
    public String getNombreCondicion() {
        return this.nombreCondicion;
    }
    
    public void setNombreCondicion(String nombreCondicion) {
        this.nombreCondicion = nombreCondicion;
    }
    public Set<CondicionesTaxativasRelacionadasEntity> getCondicionesTaxativasRelacionadases() {
        return this.condicionesTaxativasRelacionadases;
    }
    
    public void setCondicionesTaxativasRelacionadases(Set<CondicionesTaxativasRelacionadasEntity> condicionesTaxativasRelacionadases) {
        this.condicionesTaxativasRelacionadases = condicionesTaxativasRelacionadases;
    }




}


