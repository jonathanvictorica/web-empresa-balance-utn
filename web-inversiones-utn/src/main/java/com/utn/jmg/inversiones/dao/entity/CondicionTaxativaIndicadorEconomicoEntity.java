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
public class CondicionTaxativaIndicadorEconomicoEntity extends CondicionTaxativaEntity  implements java.io.Serializable {


    
     private IndicadorEconomicoEntity indicadorEconomicoComparar;

    public CondicionTaxativaIndicadorEconomicoEntity() {
    }

    
    public IndicadorEconomicoEntity getIndicadorEconomicoComparar() {
        return this.indicadorEconomicoComparar;
    }
    
    public void setIndicadorEconomicoComparar(IndicadorEconomicoEntity indicadorEconomico) {
        this.indicadorEconomicoComparar = indicadorEconomico;
    }




}


