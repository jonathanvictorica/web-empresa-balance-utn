package com.utn.jmg.inversiones.service;

import java.util.List;

import com.utn.jmg.inversiones.model.Indicador;
import com.utn.jmg.inversiones.exception.DaoException;

public interface IIndicadorService {

	Indicador buscarIndicador(String nombreIndicador, String nickUser);

	void guardar(Indicador indicador) throws DaoException;

	void modificar(Indicador indicador) throws DaoException;

	void eliminar(Indicador indicador) throws DaoException;

	List<Indicador> allIndicadoresByUser(String nickUser, boolean nativos);

}
