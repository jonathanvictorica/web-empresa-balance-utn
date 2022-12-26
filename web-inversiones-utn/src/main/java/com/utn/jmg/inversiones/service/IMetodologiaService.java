package com.utn.jmg.inversiones.service;

import java.util.List;

import com.utn.jmg.inversiones.model.Metodologia;
import com.utn.jmg.inversiones.exception.DaoException;

public interface IMetodologiaService {

	void guardar(Metodologia metodologia) throws DaoException;

	Metodologia buscarMetodologiaPorNombre(String nombreMetodologia, String nickUsuario);

	void eliminar(Metodologia metodologia) throws DaoException;

	List<Metodologia> allByUser(String nick);

}
