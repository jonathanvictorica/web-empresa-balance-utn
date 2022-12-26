package com.utn.jmg.inversiones.service;

import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;
import com.utn.jmg.inversiones.exception.DaoException;

public interface ICondicionPriorizableService {

	void guardar(CondicionPriorizable entity) throws DaoException;

	void modificar(CondicionPriorizable entity) throws DaoException;

	void eliminar(CondicionPriorizable entity) throws DaoException;

	CondicionPriorizable findByDescripcionByMetodologia(String nombreCondicion, Long id);

}
