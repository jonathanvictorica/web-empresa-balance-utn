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
public class CuentaValorEntity implements java.io.Serializable, Comparable<CuentaValorEntity> {

	private Long idCuentaValor;
	private BalanceEntity balance;
	private CuentaEntity cuenta;
	private BigDecimal valor;

	public CuentaValorEntity() {
	}

	@Override
	public int compareTo(CuentaValorEntity o) {

		return o.getCuenta().getCodigoCuenta().compareTo(this.getCuenta().getCodigoCuenta());
	}

	public CuentaValorEntity(CuentaEntity cuentaContable, BalanceEntity balanceEntity, BigDecimal bigDecimal) {
		this.balance = balanceEntity;
		this.cuenta = cuentaContable;
		this.valor = bigDecimal;
	}

	public Long getIdCuentaValor() {
		return this.idCuentaValor;
	}

	public void setIdCuentaValor(Long idCuentaValor) {
		this.idCuentaValor = idCuentaValor;
	}

	public BalanceEntity getBalance() {
		return this.balance;
	}

	public void setBalance(BalanceEntity balance) {
		this.balance = balance;
	}

	public CuentaEntity getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(CuentaEntity cuenta) {
		this.cuenta = cuenta;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
