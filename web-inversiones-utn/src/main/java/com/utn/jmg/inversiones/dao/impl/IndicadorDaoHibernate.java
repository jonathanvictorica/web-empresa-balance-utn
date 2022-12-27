package com.utn.jmg.inversiones.dao.impl;

import com.utn.jmg.inversiones.dao.EntityFactory;
import com.utn.jmg.inversiones.dao.entity.IndicadorEntity;
import com.utn.jmg.inversiones.dao.repo.IIndicadorRepository;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Indicador;
import com.utn.jmg.inversiones.model.factory.ModelFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IndicadorDaoHibernate {

	private final IIndicadorRepository indicadorRepository;

	public IndicadorDaoHibernate( IIndicadorRepository indicadorRepository) {

		this.indicadorRepository = indicadorRepository;
	}


	public Indicador buscarIndicador(String nombre, String nickUsuario) {

		Indicador indicadorBuscado = createIndicador(indicadorRepository.findTop1ByNombreAndUsuario_nick(nombre, nickUsuario));

		return indicadorBuscado;
	}
	private Indicador createIndicador(IndicadorEntity indicador) {
		if (indicador == null)
			return null;
		Indicador indicadorFactory = new Indicador(indicador.getNombre(), indicador.getFormula());
		indicadorFactory.setId(indicador.getIdIndicadorEconomico());
		indicadorFactory.setUsuario(indicador.getUsuario());
		return indicadorFactory;
	}

	public List<Indicador> allByUser(String nickUsuario) {

		List<IndicadorEntity> indicadores = indicadorRepository.findAllByUsuario_nick(nickUsuario);

		List<Indicador> indicadorResult = indicadores.stream().map(b -> this.createIndicador(b)).collect(Collectors.toList());
		return indicadorResult;
	}


	public void guardar(Indicador entity) throws DaoException {
		indicadorRepository.save(this.createIndicadorEntity(entity));

	}


	public void modificar(Indicador entity) throws DaoException {
		indicadorRepository.save(this.createIndicadorEntity(entity));

	}


	public void eliminar(Indicador entity) throws DaoException {
		indicadorRepository.delete(entity.getId());

	}

	private IndicadorEntity createIndicadorEntity(Indicador indicador) {
		IndicadorEntity indicadorEntity = new IndicadorEntity(indicador.getId(), indicador.getNombre(), indicador.getFormula());
		indicadorEntity.setUsuario(indicador.getUsuario());
		return indicadorEntity;
	}



}
