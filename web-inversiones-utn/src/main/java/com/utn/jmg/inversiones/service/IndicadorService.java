package com.utn.jmg.inversiones.service;

import java.util.ArrayList;
import java.util.List;

import com.utn.jmg.inversiones.dao.impl.IndicadorDaoHibernate;
import com.utn.jmg.inversiones.model.Indicador;
import com.utn.jmg.inversiones.exception.DaoException;
import org.springframework.stereotype.Service;

@Service
public class IndicadorService  {
	private final IndicadorDaoHibernate indicadorDao;
	private final RepositorioIndicadoresNativos repositorioIndicadoresNativos;

	public IndicadorService(IndicadorDaoHibernate indicadorDao, RepositorioIndicadoresNativos repositorioIndicadoresNativos) {
		this.indicadorDao = indicadorDao;
		this.repositorioIndicadoresNativos = repositorioIndicadoresNativos;
	}


	public Indicador buscarIndicador(String nombreIndicador, String nickUser) {
		Indicador indicador = repositorioIndicadoresNativos.findIndicadorByNombre(nombreIndicador);
		if (indicador != null) {
			return indicador;
		}
		return indicadorDao.buscarIndicador(nombreIndicador, nickUser);
	}

	public void guardar(Indicador indicador) throws DaoException {
		indicadorDao.guardar(indicador);

	}

	public void modificar(Indicador indicador) throws DaoException {
		indicadorDao.modificar(indicador);

	}

	public void eliminar(Indicador indicador) throws DaoException {
		indicadorDao.eliminar(indicador);

	}


	public List<Indicador> allIndicadoresByUser(String nickUser, boolean nativos) {
		List<Indicador> indicadores = new ArrayList<Indicador>();
		if (nativos) {
			indicadores.addAll(repositorioIndicadoresNativos.getIndicadoresNativos());
		}
		List<Indicador> indicadoresAlmacenados = indicadorDao.allByUser(nickUser);
		if (indicadoresAlmacenados != null) {
			indicadores.addAll(indicadoresAlmacenados);
		}
		return indicadores;
	}

}
