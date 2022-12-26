package com.utn.jmg.inversiones.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.utn.jmg.inversiones.dao.IBalanceDao;
import com.utn.jmg.inversiones.dao.ICuentaValorDao;
import com.utn.jmg.inversiones.dao.entity.BalanceEntity;
import com.utn.jmg.inversiones.dao.entity.CuentaEntity;
import com.utn.jmg.inversiones.dao.entity.CuentaValorEntity;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Balance;

public class BalanceDaoHibernate extends BaseDaoHibernate<BalanceEntity> implements IBalanceDao<Balance> {

	private ICuentaValorDao<CuentaValorEntity> cuentaValorDao;

	@Override
	public List<Balance> allBalance(String cuit) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(BalanceEntity.class);
		crit.createAlias("empresa", "emp", CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("cuentas", "cuent", CriteriaSpecification.INNER_JOIN);
		crit.createAlias("cuent.cuenta", "cuenta", CriteriaSpecification.INNER_JOIN);
		crit.add(Restrictions.eq("emp.cuit", cuit));
		@SuppressWarnings("unchecked")
		List<BalanceEntity> balances = (List<BalanceEntity>) crit.list();
		List<Balance> balancesResult = balances.stream().map(b -> this.factoryModel.createBalance(b)).collect(Collectors.toList());
		session.close();
		return balancesResult;

	}

	@Override
	public Balance buscarBalance(String cuit, Date fechaComienzo, Date fechaFin) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(BalanceEntity.class);
		crit.createAlias("empresa", "emp", CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("cuentas", "cuent", CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("cuent.cuenta", "cuenta", CriteriaSpecification.LEFT_JOIN);
		crit.add(Restrictions.eq("emp.cuit", cuit));
		crit.add(Restrictions.eq("fechaComienzo", fechaComienzo));
		crit.add(Restrictions.eq("fechaCierre", fechaFin));
		crit.addOrder(Order.asc("cuenta.codigoCuenta"));
		BalanceEntity balance = (BalanceEntity) crit.uniqueResult();

		Balance balanceBuscado = factoryModel.createBalance(balance);
		session.close();
		return balanceBuscado;
	}

	public void guardar(Balance entity) throws DaoException {
		BalanceEntity balance = entityFactory.createBalanceEntity(entity);
		this.guardarEntidad(balance);
		balance.getCuentas().stream().forEach(cuent -> {
			try {
				cuent.setBalance(balance);
				this.cuentaValorDao.guardar(cuent);
			} catch (Exception e) {
			}
		});
	}

	public void modificar(Balance entity) throws DaoException {
		BalanceEntity balance = entityFactory.createBalanceEntity(entity);
		this.modificarEntidad(balance);
		balance.getCuentas().stream().forEach(cuent -> {
			try {
				cuent.setBalance(balance);
				if (cuent.getIdCuentaValor() == null) {
					this.cuentaValorDao.guardar(cuent);
				} else {
					this.cuentaValorDao.modificar(cuent);
				}
			} catch (Exception e) {
			}
		});
	}

	public void eliminar(Balance entity) throws DaoException {
		this.eliminarEntidad(entityFactory.createBalanceEntity(entity));
	}

	public ICuentaValorDao<CuentaValorEntity> getCuentaValorDao() {
		return cuentaValorDao;
	}

	public void setCuentaValorDao(ICuentaValorDao<CuentaValorEntity> cuentaValorDao) {
		this.cuentaValorDao = cuentaValorDao;
	}

	@Override
	public List<String> obtenerTodosLosPeriodosBalance(String cuit) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(BalanceEntity.class);
		crit.createAlias("empresa", "emp", CriteriaSpecification.INNER_JOIN);
		crit.add(Restrictions.eq("emp.cuit", cuit));
		crit.setProjection(Projections.projectionList().add(Projections.property("periodo")));
		@SuppressWarnings("unchecked")
		List<String> periodos = crit.list();
		session.close();
		return periodos;
	}

	@Override
	public List<String> obtenerListaCuentas() {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(CuentaEntity.class);
		crit.setProjection(Projections.projectionList().add(Projections.property("nombre")));
		crit.addOrder(Order.asc("nombre"));
		@SuppressWarnings("unchecked")
		List<String> cuentas = crit.list();
		session.close();
		return cuentas;
	}

}
