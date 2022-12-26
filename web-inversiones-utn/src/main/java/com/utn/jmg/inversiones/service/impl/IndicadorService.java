package com.utn.jmg.inversiones.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.utn.jmg.inversiones.model.Indicador;
import com.utn.jmg.inversiones.service.IIndicadorService;
import com.utn.jmg.inversiones.dao.IIndicadorDao;
import com.utn.jmg.inversiones.exception.DaoException;

public class IndicadorService implements IIndicadorService {
	private IIndicadorDao<Indicador> indicadorDao;
	private RepositorioIndicadoresNativos repositorioIndicadoresNativos;

	public IndicadorService() {

		repositorioIndicadoresNativos = new RepositorioIndicadoresNativos();
	}

	public IIndicadorDao<Indicador> getIndicadorDao() {
		return indicadorDao;
	}

	public void setIndicadorDao(IIndicadorDao<Indicador> indicadorDao) {
		this.indicadorDao = indicadorDao;
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

	@Override
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
