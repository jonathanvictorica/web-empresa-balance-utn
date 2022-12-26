package com.utn.jmg.inversiones.dao;

import java.util.List;

import com.utn.jmg.inversiones.model.Indicador;

public interface IIndicadorDao<Entity> extends IBaseDao<Entity> {

	Indicador buscarIndicador(String nombreIndicador, String nickUsuario);

	List<Indicador> allByUser(String nickUsuario);

}
