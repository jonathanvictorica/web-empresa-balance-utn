package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class BalanceEntity  implements java.io.Serializable {


     private Long idBalance;
     private EmpresaEntity empresa;
     private Date fechaComienzo;
     private Date fechaCierre;
     private Set cuentas = new HashSet(0);

    public BalanceEntity() {
    }

    private String periodo;

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
    public BalanceEntity(EmpresaEntity empresa, Date fechaComienzo, Date fechaCierre) {
        this.empresa = empresa;
        this.fechaComienzo = fechaComienzo;
        this.fechaCierre = fechaCierre;
    }
    public BalanceEntity(EmpresaEntity empresa, Date fechaComienzo, Date fechaCierre, Set cuentaValors) {
       this.empresa = empresa;
       this.fechaComienzo = fechaComienzo;
       this.fechaCierre = fechaCierre;
       this.cuentas = cuentaValors;
    }
   
    public Long getIdBalance() {
        return this.idBalance;
    }
    
    public void setIdBalance(Long idBalance) {
        this.idBalance = idBalance;
    }
    public EmpresaEntity getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(EmpresaEntity empresa) {
        this.empresa = empresa;
    }
    public Date getFechaComienzo() {
        return this.fechaComienzo;
    }
    
    public void setFechaComienzo(Date fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }
    public Date getFechaCierre() {
        return this.fechaCierre;
    }
    
    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

	public Set<CuentaValorEntity> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<CuentaValorEntity> cuentas) {
		this.cuentas = cuentas;
	}
  



}


