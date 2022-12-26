package com.utn.jmg.inversiones.model.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.utn.jmg.inversiones.model.Cuenta;
import com.utn.jmg.inversiones.model.Empresa;
import com.utn.jmg.inversiones.model.Indicador;
import com.utn.jmg.inversiones.model.Metodologia;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxIndEconomico;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxValor;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;
import com.utn.jmg.inversiones.model.metodologia.TipoCondiciones;
import com.utn.jmg.inversiones.dao.ICuentaDao;
import com.utn.jmg.inversiones.dao.IIndicadorDao;
import com.utn.jmg.inversiones.dao.entity.BalanceEntity;
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
import com.utn.jmg.inversiones.model.Balance;
import com.utn.jmg.inversiones.model.IndicadorEconomico;
import com.utn.jmg.inversiones.model.metodologia.Comparador;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;
import com.utn.jmg.inversiones.service.impl.RepositorioIndicadoresNativos;

public class ModelFactory {

	private ICuentaDao<Cuenta> cuentaDao;
	private IIndicadorDao<Indicador> indicadorDao;
	private RepositorioIndicadoresNativos repositorioIndicadoresNativos;

	public Balance createBalance(BalanceEntity balance) {
		if (balance == null)
			return null;
		Balance balanceFactory = new Balance(balance.getFechaComienzo(), balance.getFechaCierre());
		balanceFactory.setEmpresa(this.createEmpresa(balance.getEmpresa()));
		balanceFactory.setId(balance.getIdBalance());

		try {
			List<CuentaValorEntity> values = new ArrayList<CuentaValorEntity>(balance.getCuentas());
			Collections.sort(values);
			for (CuentaValorEntity cuentaValor : values) {
				balanceFactory.agregarCuenta(createCuenta(cuentaValor));
			}
		} catch (Exception e) {
		}
		return balanceFactory;
	}

	public Cuenta createCuenta(CuentaValorEntity cuentaValor) {
		try {
			Cuenta cuentaFactory = new Cuenta(cuentaValor.getCuenta().getNombre(), cuentaValor.getValor().doubleValue());
			cuentaFactory.setId(cuentaValor.getIdCuentaValor());
			return cuentaFactory;
		} catch (Exception e) {
			return null;
		}

	}

	public Cuenta createCuenta(CuentaEntity cuenta) {
		try {
			Cuenta cuentaFactory = new Cuenta(cuenta.getNombre());
			cuentaFactory.setId(cuenta.getIdIndicadorEconomico());
			return cuentaFactory;
		} catch (Exception e) {
			return null;
		}

	}

	public Empresa createEmpresa(EmpresaEntity empresa) {
		if (empresa == null)
			return null;
		Empresa empresaFactory = new Empresa(empresa.getIdEmpresa(), empresa.getCuit(), empresa.getRazonSocial(), empresa.getDescripActividad(), empresa.getFechaAlta());

		return empresaFactory;

	}

	public Metodologia createMetodologia(MetodologiaEntity metodologia) {
		if (metodologia == null)
			return null;
		Metodologia metodologiaFactory = new Metodologia(metodologia.getIdMetodologia(), metodologia.getNombre());
		metodologiaFactory.setUsuario(metodologia.getUsuario());
		try {
			for (CondicionTaxativaEntity condicion : metodologia.getCondicionTaxativas()) {
				metodologiaFactory.agregarCondicionTaxativa(createCondicionTaxativa(condicion, metodologia.getUsuario().getNick()));
			}
		} catch (Exception e) {

		}
		try {
			for (CondicionPriorizableEntity condicion : metodologia.getCondicionPriorizables()) {
				metodologiaFactory.agregarCondicionPriorizable(createCondicionPriorizable(condicion, metodologia.getUsuario().getNick()));
			}
		} catch (Exception e) {

		}
		return metodologiaFactory;
	}

	private CondicionPriorizable createCondicionPriorizable(CondicionPriorizableEntity condicion, String nickUsuario) {
		CondicionPriorizable condicionPriorizableFactory = new CondicionPriorizable();
		condicionPriorizableFactory.setDescripcion(condicion.getNombreCondicion());
		try {
			for (CondicionesTaxativasRelacionadasEntity cond : condicion.getCondicionesTaxativasRelacionadases()) {
				condicionPriorizableFactory.agregarCondicionTaxativa(this.createCondicionTaxativa(cond.getCondicionTaxativa(), nickUsuario));

			}
		} catch (Exception e) {

		}
		condicionPriorizableFactory.setId(condicion.getIdCondicionPriorizable());
		return condicionPriorizableFactory;
	}

	public CondicionTaxativa createCondicionTaxativa(CondicionTaxativaEntity condicion, String nickUsuario) {
		if (condicion.getDiscriminador().equalsIgnoreCase("taxValor")) {
			return this.createCondicionTaxativaConValor(condicion, nickUsuario);
		} else if (condicion.getDiscriminador().equalsIgnoreCase("taxIndicador")) {
			return this.createCondicionTaxativaConIndicador(condicion, nickUsuario);
		}
		return null;
	}

	private CondicionTaxativa createCondicionTaxativaConIndicador(CondicionTaxativaEntity condicion, String nickUsuario) {
		IndicadorEconomico indEconomico = this.createIndicadorEconomico(condicion.getIndicadorEconomico(), nickUsuario);
		IndicadorEconomico indEconomicoComparar = this.createIndicadorEconomico(((CondicionTaxativaIndicadorEconomicoEntity) condicion).getIndicadorEconomicoComparar(), nickUsuario);
		Comparador comparador = Comparador.get(condicion.getComparador().getDescripcion());
		CondicionTaxIndEconomico condTaxInd = new CondicionTaxIndEconomico(indEconomico, comparador, indEconomicoComparar, TipoCondiciones.TAXATIVAS_CON_INDICADOR, condicion.getNombreCondicion());
		condTaxInd.setId(condicion.getIdCondicionTaxativa());
		return condTaxInd;
	}

	private CondicionTaxativa createCondicionTaxativaConValor(CondicionTaxativaEntity condicion, String nickUsuario) {
		IndicadorEconomico indEconomico = this.createIndicadorEconomico(condicion.getIndicadorEconomico(), nickUsuario);
		Comparador comparador = Comparador.get(condicion.getComparador().getDescripcion());
		CondicionTaxValor condTaxVal = new CondicionTaxValor(indEconomico, comparador, ((CondicionTaxativaValorEntity) condicion).getValor().doubleValue(), TipoCondiciones.TAXATIVAS_CON_VALOR, condicion.getNombreCondicion());
		condTaxVal.setId(condicion.getIdCondicionTaxativa());
		return condTaxVal;
	}

	public IndicadorEconomico createIndicadorEconomico(IndicadorEconomicoEntity indicadorEc, String nickUsuario) {
		if (indicadorEc.getDiscriminador().equalsIgnoreCase("Cuenta")) {
			return cuentaDao.findCuentaByNombre(indicadorEc.getNombre());
		} else if (indicadorEc.getDiscriminador().equalsIgnoreCase("Indicador")) {
			return this.createIndicador(indicadorEc.getNombre(), nickUsuario);
		}
		return null;
	}

	private Indicador createIndicador(String nombreIndicador, String nickUsuario) {
		Indicador ind = repositorioIndicadoresNativos.findIndicadorByNombre(nombreIndicador);
		if (ind == null) {
			ind = indicadorDao.buscarIndicador(nombreIndicador, nickUsuario);
		}
		return ind;
	}

	public Indicador createIndicador(IndicadorEntity indicador) {
		if (indicador == null)
			return null;
		Indicador indicadorFactory = new Indicador(indicador.getNombre(), indicador.getFormula());
		indicadorFactory.setId(indicador.getIdIndicadorEconomico());
		indicadorFactory.setUsuario(indicador.getUsuario());
		return indicadorFactory;
	}

	public ICuentaDao<Cuenta> getCuentaDao() {
		return cuentaDao;
	}

	public void setCuentaDao(ICuentaDao<Cuenta> cuentaDao) {
		this.cuentaDao = cuentaDao;
	}

	public IIndicadorDao<Indicador> getIndicadorDao() {
		return indicadorDao;
	}

	public void setIndicadorDao(IIndicadorDao<Indicador> indicadorDao) {
		this.indicadorDao = indicadorDao;
	}

	public RepositorioIndicadoresNativos getRepositorioIndicadoresNativos() {
		return repositorioIndicadoresNativos;
	}

	public void setRepositorioIndicadoresNativos(RepositorioIndicadoresNativos repositorioIndicadoresNativos) {
		this.repositorioIndicadoresNativos = repositorioIndicadoresNativos;
	}

}
