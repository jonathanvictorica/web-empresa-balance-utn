package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Entity
//@Table
@Getter
@Setter
@NoArgsConstructor
public class BalanceEntity  implements java.io.Serializable {


     private Long idBalance;
     private EmpresaEntity empresa;
     private Date fechaComienzo;
     private Date fechaCierre;
     private Set<CuentaValorEntity> cuentas = new HashSet(0);

private String periodo;
	
    public BalanceEntity(EmpresaEntity empresa, Date fechaComienzo, Date fechaCierre) {
        this.empresa = empresa;
        this.fechaComienzo = fechaComienzo;
        this.fechaCierre = fechaCierre;
    }

  



}


