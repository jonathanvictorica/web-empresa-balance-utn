package com.utn.jmg.inversiones.dao.entity;
// Generated 26-sep-2017 12:39:56 by Hibernate Tools 4.3.1


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class CondicionTaxativaEntity  implements java.io.Serializable {


     protected Long idCondicionTaxativa;
     protected ComparadorEntity comparador;
     protected IndicadorEconomicoEntity indicadorEconomico;
     protected MetodologiaEntity metodologia;
     protected String nombreCondicion;
     protected String discriminador;
     
     

    public String getDiscriminador() {
		return discriminador;
	}


	public void setDiscriminador(String discriminador) {
		this.discriminador = discriminador;
	}


	public CondicionTaxativaEntity() {
    }

	
    public CondicionTaxativaEntity(ComparadorEntity comparador, IndicadorEconomicoEntity indicadorEconomico, MetodologiaEntity metodologia, String nombreCondicion) {
        this.comparador = comparador;
        this.indicadorEconomico = indicadorEconomico;
        this.metodologia = metodologia;
        this.nombreCondicion = nombreCondicion;
    }
    
   
    public Long getIdCondicionTaxativa() {
        return this.idCondicionTaxativa;
    }
    
    public void setIdCondicionTaxativa(Long idCondicionTaxativa) {
        this.idCondicionTaxativa = idCondicionTaxativa;
    }
    public ComparadorEntity getComparador() {
        return this.comparador;
    }
    
    public void setComparador(ComparadorEntity comparador) {
        this.comparador = comparador;
    }
    public IndicadorEconomicoEntity getIndicadorEconomico() {
        return this.indicadorEconomico;
    }
    
    public void setIndicadorEconomico(IndicadorEconomicoEntity indicadorEconomico) {
        this.indicadorEconomico = indicadorEconomico;
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
   




}


