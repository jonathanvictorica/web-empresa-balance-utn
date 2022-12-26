package com.utn.jmg.inversiones.dao;

import com.utn.jmg.inversiones.model.Empresa;

public interface IEmpresaDao<Entity> extends IBaseDao<Entity> {

	Empresa findEmpresaByCuit(String cuit);

	Empresa findEmpresaByRazonSocial(String razonSocial);

}
