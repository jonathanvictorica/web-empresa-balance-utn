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
public class CondicionesTaxativasRelacionadasEntity  implements java.io.Serializable {


     private Long idCondicionTaxativaRelacionada;
     private CondicionPriorizableEntity condicionPriorizable;
     private CondicionTaxativaEntity condicionTaxativa;

    public CondicionesTaxativasRelacionadasEntity() {
    }

    public CondicionesTaxativasRelacionadasEntity(CondicionTaxativaEntity condicionTaxativa, CondicionPriorizableEntity condicionPriorizable) {
       this.condicionPriorizable = condicionPriorizable;
       this.condicionTaxativa = condicionTaxativa;
    }
   
    public Long getIdCondicionTaxativaRelacionada() {
        return this.idCondicionTaxativaRelacionada;
    }
    
    public void setIdCondicionTaxativaRelacionada(Long idCondicionTaxativaRelacionada) {
        this.idCondicionTaxativaRelacionada = idCondicionTaxativaRelacionada;
    }
    public CondicionPriorizableEntity getCondicionPriorizable() {
        return this.condicionPriorizable;
    }
    
    public void setCondicionPriorizable(CondicionPriorizableEntity condicionPriorizable) {
        this.condicionPriorizable = condicionPriorizable;
    }
    public CondicionTaxativaEntity getCondicionTaxativa() {
        return this.condicionTaxativa;
    }
    
    public void setCondicionTaxativa(CondicionTaxativaEntity condicionTaxativa) {
        this.condicionTaxativa = condicionTaxativa;
    }




}


