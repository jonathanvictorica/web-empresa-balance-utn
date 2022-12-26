package com.utn.jmg.inversiones.dao;

import com.utn.jmg.inversiones.exception.DaoException;

public interface IBaseDao<Entity> {
	public void guardar(Entity entity) throws DaoException;

	public void modificar(Entity entity) throws DaoException;

	public void eliminar(Entity entity) throws DaoException;
}
