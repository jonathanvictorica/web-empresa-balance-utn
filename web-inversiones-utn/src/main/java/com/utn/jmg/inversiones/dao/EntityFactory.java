package com.utn.jmg.inversiones.dao;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.utn.jmg.inversiones.dao.entity.BalanceEntity;
import com.utn.jmg.inversiones.dao.entity.ComparadorEntity;
import com.utn.jmg.inversiones.dao.entity.CondicionPriorizableEntity;
import com.utn.jmg.inversiones.dao.entity.CondicionTaxativaEntity;
import com.utn.jmg.inversiones.dao.entity.CondicionTaxativaIndicadorEconomicoEntity;
import com.utn.jmg.inversiones.dao.entity.CondicionTaxativaValorEntity;
import com.utn.jmg.inversiones.dao.entity.CondicionesTaxativasRelacionadasEntity;
import com.utn.jmg.inversiones.dao.entity.CuentaEntity;
import com.utn.jmg.inversiones.dao.entity.CuentaValorEntity;
import com.utn.jmg.inversiones.dao.entity.EmpresaEntity;
import com.utn.jmg.inversiones.dao.entity.IndicadorEconomicoEntity;
import com.utn.jmg.inversiones.dao.entity.IndicadorEntity;
import com.utn.jmg.inversiones.dao.entity.MetodologiaEntity;
import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;
import com.utn.jmg.inversiones.dao.impl.ComparadorDaoHibernate;
import com.utn.jmg.inversiones.dao.impl.CuentaDaoHibernate;
import com.utn.jmg.inversiones.dao.impl.IndicadorDaoHibernate;
import com.utn.jmg.inversiones.model.Balance;
import com.utn.jmg.inversiones.model.Cuenta;
import com.utn.jmg.inversiones.model.Empresa;
import com.utn.jmg.inversiones.model.Indicador;
import com.utn.jmg.inversiones.model.IndicadorEconomico;
import com.utn.jmg.inversiones.model.Metodologia;
import com.utn.jmg.inversiones.model.metodologia.Comparador;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxIndEconomico;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxValor;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;
import com.utn.jmg.inversiones.model.metodologia.TipoCondiciones;
import org.springframework.stereotype.Component;

@Component
public class EntityFactory {
	private final CuentaDaoHibernate cuentaDao;
	private final IndicadorDaoHibernate indicadorDao;
	private final ComparadorDaoHibernate comparadorDao;

	public EntityFactory(CuentaDaoHibernate cuentaDao, IndicadorDaoHibernate indicadorDao, ComparadorDaoHibernate comparadorDao) {
		this.cuentaDao = cuentaDao;
		this.indicadorDao = indicadorDao;
		this.comparadorDao = comparadorDao;
	}

	public BalanceEntity createBalanceEntity(Balance balance) {
		EmpresaEntity empresaEntity = this.createEmpresaEntity(balance.getEmpresa());

		BalanceEntity balanceEntity = new BalanceEntity(empresaEntity, balance.getFechaComienzoPeriodo(), balance.getFechaFinPeriodo());
		balanceEntity.setIdBalance(balance.getId());
		Set<CuentaValorEntity> cuentaValor = new HashSet(0);
		for (Cuenta cuenta : balance.getCuentas()) {
			CuentaValorEntity cuentaBalance = createCuentaValorEntity(cuenta, balanceEntity);
			cuentaValor.add(cuentaBalance);
		}
		balanceEntity.setCuentas(cuentaValor);
		return balanceEntity;
	}

	public CuentaEntity createCuentaEntity(String nombreCuenta) {
		Cuenta cuenta = cuentaDao.findCuentaByNombre(nombreCuenta);
		return this.createCuentaEntity(cuenta);
	}

	private CuentaValorEntity createCuentaValorEntity(Cuenta cuenta, BalanceEntity balanceEntity) {
		CuentaEntity cuentaContable = this.createCuentaEntity(cuenta.getNombreCuenta());
		CuentaValorEntity cuentaBalance = new CuentaValorEntity(cuentaContable, balanceEntity, new BigDecimal(cuenta.getValor()));
		cuentaBalance.setIdCuentaValor(cuenta.getId());

		return cuentaBalance;
	}

	private CondicionesTaxativasRelacionadasEntity createCondicionTaxativRelacionadEntity(CondicionTaxativa cond, CondicionPriorizableEntity condicionPriorizableEntity, MetodologiaEntity metodologia) {
		CondicionesTaxativasRelacionadasEntity condicionRelacionada = new CondicionesTaxativasRelacionadasEntity(this.createCondicionTaxativaEntity(cond, metodologia), condicionPriorizableEntity);
		return condicionRelacionada;
	}

	public CondicionPriorizableEntity createCondicionPriorizableEntity(CondicionPriorizable condicionPriorizable, MetodologiaEntity metodologia) {
		// MetodologiaEntity metodologiaEntity =
		// this.createMetodologiaEntity(condicionPriorizable.getMetodologia());
		CondicionPriorizableEntity condicionPriorizableEntity = new CondicionPriorizableEntity(metodologia, condicionPriorizable.getDescripcion());
		Set<CondicionesTaxativasRelacionadasEntity> condicionesTaxativasRelacionadasEntity = condicionPriorizable.getCondicionTaxativa().stream().map(cond -> this.createCondicionTaxativRelacionadEntity(cond, condicionPriorizableEntity, metodologia)).collect(Collectors.toSet());
		condicionPriorizableEntity.setCondicionesTaxativasRelacionadases(condicionesTaxativasRelacionadasEntity);
		return condicionPriorizableEntity;
	}

	public CondicionTaxativaEntity createCondicionTaxativaEntity(CondicionTaxativa CondicionTaxativa, MetodologiaEntity metodologia) {
		if (CondicionTaxativa.getTipoCondicion().name().equalsIgnoreCase(TipoCondiciones.TAXATIVAS_CON_VALOR.name())) {
			return this.createCondicionTaxativaEntity((CondicionTaxValor) CondicionTaxativa, metodologia);
		} else if (CondicionTaxativa.getTipoCondicion().name().equalsIgnoreCase(TipoCondiciones.TAXATIVAS_CON_INDICADOR.name())) {
			return this.createCondicionTaxativaEntity((CondicionTaxIndEconomico) CondicionTaxativa, metodologia);
		}
		return null;
	}

	private CondicionTaxativaEntity createCondicionTaxativaEntity(CondicionTaxValor condicionTaxativa, MetodologiaEntity metodologia) {
		// MetodologiaEntity metodologia =
		// this.createMetodologiaEntity(condicionTaxativa.getMetodologia());
		CondicionTaxativaEntity condicionTaxativaEntity = new CondicionTaxativaValorEntity();
		condicionTaxativaEntity.setComparador(this.createComparador(condicionTaxativa.getComparador()));
		condicionTaxativaEntity.setIdCondicionTaxativa(condicionTaxativa.getId());
		condicionTaxativaEntity.setIndicadorEconomico(this.createIndicadorEconomico(condicionTaxativa.getIndicadorEconomico(),metodologia.getUsuario()));
		condicionTaxativaEntity.setMetodologia(metodologia);
		condicionTaxativaEntity.setNombreCondicion(condicionTaxativa.getDescripcion());
		((CondicionTaxativaValorEntity) condicionTaxativaEntity).setIdCondicionTaxativaValor(condicionTaxativa.getId());
		((CondicionTaxativaValorEntity) condicionTaxativaEntity).setValor(new BigDecimal(condicionTaxativa.getValor()));
		return condicionTaxativaEntity;
	}

	private CondicionTaxativaEntity createCondicionTaxativaEntity(CondicionTaxIndEconomico condicionTaxativa, MetodologiaEntity metodologia) {
		// MetodologiaEntity metodologia =
		// this.createMetodologiaEntity(condicionTaxativa.getMetodologia());
		CondicionTaxativaEntity condicionTaxativaEntity = new CondicionTaxativaIndicadorEconomicoEntity();
		condicionTaxativaEntity.setComparador(this.createComparador(condicionTaxativa.getComparador()));
		condicionTaxativaEntity.setIdCondicionTaxativa(condicionTaxativa.getId());
		condicionTaxativaEntity.setIndicadorEconomico(this.createIndicadorEconomico(condicionTaxativa.getIndicadorEconomico(),metodologia.getUsuario()));
		// condicionTaxativaEntity.setMetodologia(this.createMetodologiaEntity(condicionTaxativa.getMetodologia()));
		condicionTaxativaEntity.setMetodologia(metodologia);
		condicionTaxativaEntity.setNombreCondicion(condicionTaxativa.getDescripcion());
		// ((CondicionTaxativaIndicadorEconomicoEntity)
		// condicionTaxativaEntity).set
		((CondicionTaxativaIndicadorEconomicoEntity) condicionTaxativaEntity).setIndicadorEconomicoComparar(this.createIndicadorEconomico(condicionTaxativa.getIndicadorEconomicoComparar(),metodologia.getUsuario()));
		return condicionTaxativaEntity;
	}

	private IndicadorEconomicoEntity createIndicadorEconomico(IndicadorEconomico indicadorEconomico,UsuarioEntity usuario) {
		if(indicadorEconomico==null){
			return null;
		}
		
		if (indicadorEconomico.tipoIndicador().equalsIgnoreCase("Cuenta")) {
			return createCuentaEntity(((Cuenta) indicadorEconomico).getNombreCuenta());
		} else if (indicadorEconomico.tipoIndicador().equalsIgnoreCase("Indicador")) {
			return createIndicadorEntity(((Indicador) indicadorEconomico).getNombre(),usuario);
		}
		return null;
	}

	private ComparadorEntity createComparador(Comparador comparador) {
		ComparadorEntity comparadorEntity = this.comparadorDao.findComparadorByDescripcion(comparador.getNombre());
		return comparadorEntity;
	}

	public CuentaEntity createCuentaEntity(Cuenta cuenta) {
		CuentaEntity cuentaEntity = new CuentaEntity(cuenta.getId(), cuenta.getNombre(), cuenta.getCodigo());
		return cuentaEntity;
	}

	public IndicadorEntity createIndicadorEntity(Indicador indicador) {
		IndicadorEntity indicadorEntity = new IndicadorEntity(indicador.getId(), indicador.getNombre(), indicador.getFormula());
		indicadorEntity.setUsuario(indicador.getUsuario());
		return indicadorEntity;
	}

	public IndicadorEntity createIndicadorEntity(String nombre, UsuarioEntity usuario) {
		Indicador indicador = indicadorDao.buscarIndicador(nombre, usuario.getNick());
		return createIndicadorEntity(indicador);
	}

	public EmpresaEntity createEmpresaEntity(Empresa empresa) {
		if (empresa == null)
			return null;
		EmpresaEntity empresaEntity = new EmpresaEntity(empresa.getId(), empresa.getCuit(), empresa.getRazonSocial(), empresa.getDescripActividad(), empresa.getFechaAlta());
		return empresaEntity;
	}

	public MetodologiaEntity createMetodologiaEntity(Metodologia metodologia) {
		if (metodologia == null)
			return null;
		MetodologiaEntity metodologiaEntity = new MetodologiaEntity(metodologia.getId(), metodologia.getNombre());
		metodologiaEntity.setUsuario(metodologia.getUsuario());
		Set<CondicionTaxativaEntity> condicionesTaxativas = null;
		Set<CondicionPriorizableEntity> condicionesPriorizables = null;
		try {
			condicionesTaxativas = metodologia.getCondicionesTaxativas().stream().map(cond -> this.createCondicionTaxativaEntity(cond, metodologiaEntity)).collect(Collectors.toSet());
		} catch (Exception e) {
		}
		try {
			condicionesPriorizables = metodologia.getCondicionesPriorizables().stream().map(cond -> this.createCondicionPriorizableEntity(cond, metodologiaEntity)).collect(Collectors.toSet());
		} catch (Exception e) {
		}
		metodologiaEntity.setCondicionPriorizables(condicionesPriorizables);
		metodologiaEntity.setCondicionTaxativas(condicionesTaxativas);
		return metodologiaEntity;
	}



}
