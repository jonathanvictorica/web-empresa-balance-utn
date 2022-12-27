package com.utn.jmg.inversiones.dao.impl;

import com.utn.jmg.inversiones.dao.EntityFactory;
import com.utn.jmg.inversiones.dao.entity.BalanceEntity;
import com.utn.jmg.inversiones.dao.entity.CuentaEntity;
import com.utn.jmg.inversiones.dao.repo.CuentaValorDaoHibernate;
import com.utn.jmg.inversiones.dao.repo.IBalanceRepository;
import com.utn.jmg.inversiones.dao.repo.ICuentaRepository;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Balance;
import com.utn.jmg.inversiones.model.factory.ModelFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BalanceDaoHibernate {

	private final CuentaValorDaoHibernate cuentaValorDao;

	protected final ModelFactory factoryModel;
	protected final EntityFactory entityFactory;

	private final IBalanceRepository balanceRepository;
	private final ICuentaRepository cuentaRepository;


	public BalanceDaoHibernate(CuentaValorDaoHibernate cuentaValorDao, ModelFactory factoryModel, EntityFactory entityFactory, IBalanceRepository balanceRepository, ICuentaRepository cuentaRepository) {
		this.cuentaValorDao = cuentaValorDao;
		this.factoryModel = factoryModel;
		this.entityFactory = entityFactory;
		this.balanceRepository = balanceRepository;
		this.cuentaRepository = cuentaRepository;
	}


	public List<Balance> allBalance(String cuit) {

		List<BalanceEntity> balances = balanceRepository.findAllByEmpresa_cuit(cuit);
		List<Balance> balancesResult = balances.stream().map(b -> this.factoryModel.createBalance(b)).collect(Collectors.toList());

		return balancesResult;

	}


	public Balance buscarBalance(String cuit, Date fechaComienzo, Date fechaFin) {


		Balance balanceBuscado = factoryModel.createBalance(balanceRepository.findTop1ByEmpresa_cuitAndFechaComienzoAndFechaCierre(cuit, fechaComienzo, fechaFin));

		return balanceBuscado;
	}

	public void guardar(Balance entity) throws DaoException {
		BalanceEntity balance = entityFactory.createBalanceEntity(entity);
		balanceRepository.save(balance);
		balance.getCuentas().stream().forEach(cuent -> {
			try {
				cuent.setBalance(balance);
				this.cuentaValorDao.save(cuent);
			} catch (Exception e) {
			}
		});
	}

	public void modificar(Balance entity) throws DaoException {
		BalanceEntity balance = entityFactory.createBalanceEntity(entity);
		balanceRepository.save(balance);
		balance.getCuentas().stream().forEach(cuent -> {
			try {
				cuent.setBalance(balance);
				this.cuentaValorDao.save(cuent);

			} catch (Exception e) {
			}
		});
	}

	public void eliminar(Balance entity) throws DaoException {
		balanceRepository.delete(entity.getId());
	}


	public List<String> obtenerTodosLosPeriodosBalance(String cuit) {

		return balanceRepository.findAllByEmpresa_cuit(cuit).stream().map(BalanceEntity::getPeriodo).collect(Collectors.toList());
	}


	public List<String> obtenerListaCuentas() {
		return cuentaRepository.findAll().stream().map(CuentaEntity::getNombre).collect(Collectors.toList());
	}



}
