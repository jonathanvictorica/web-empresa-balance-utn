package com.utn.jmg.inversiones.dao;

import com.utn.jmg.inversiones.model.Cuenta;

public interface ICuentaDao<Entity> extends IBaseDao<Entity> {
	public Cuenta findCuentaByNombre(String nombre);
}
