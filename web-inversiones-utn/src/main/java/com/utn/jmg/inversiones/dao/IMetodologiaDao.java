package com.utn.jmg.inversiones.dao;

import java.util.List;

import com.utn.jmg.inversiones.model.Metodologia;

public interface IMetodologiaDao<Entity> extends IBaseDao<Entity> {

	List<Metodologia> allByUsuario(String nickUsuario);

	Metodologia buscarMetodologia(String nombreMetodologia, String nickUsuario);

}
