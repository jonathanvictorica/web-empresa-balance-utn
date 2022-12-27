package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

//@Entity
//@Table
@Getter
@Setter
public class CondicionTaxativaValorEntity extends CondicionTaxativaEntity implements java.io.Serializable {


     private Long idCondicionTaxativaValor;   
     private BigDecimal valor;

    public CondicionTaxativaValorEntity() {
    }

   
   
    public Long getIdCondicionTaxativaValor() {
        return this.idCondicionTaxativaValor;
    }
    
    public void setIdCondicionTaxativaValor(Long idCondicionTaxativaValor) {
        this.idCondicionTaxativaValor = idCondicionTaxativaValor;
    }
   
    public BigDecimal getValor() {
        return this.valor;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }




}


