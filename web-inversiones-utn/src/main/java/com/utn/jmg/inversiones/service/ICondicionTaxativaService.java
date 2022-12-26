package com.utn.jmg.inversiones.service;

import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;
import com.utn.jmg.inversiones.exception.DaoException;

public interface ICondicionTaxativaService {
	CondicionTaxativa findByDescripcionByMetodologia(String nombreCondicion, Long idMetodologia);
	
	void guardar(CondicionTaxativa entity) throws DaoException;
	
	void modificar(CondicionTaxativa entity)throws DaoException;
	
	void eliminar(CondicionTaxativa entity)throws DaoException;
}
