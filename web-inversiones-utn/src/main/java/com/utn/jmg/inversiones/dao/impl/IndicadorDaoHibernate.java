package com.utn.jmg.inversiones.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.utn.jmg.inversiones.dao.IIndicadorDao;
import com.utn.jmg.inversiones.dao.entity.IndicadorEntity;
import com.utn.jmg.inversiones.exception.DaoException;
import com.utn.jmg.inversiones.model.Indicador;

public class IndicadorDaoHibernate extends BaseDaoHibernate<IndicadorEntity> implements IIndicadorDao<Indicador> {

	@Override
	public Indicador buscarIndicador(String nombre, String nickUsuario) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(IndicadorEntity.class);
		crit.add(Restrictions.eq("nombre", nombre));
		crit.add(Restrictions.eq("usr.nick", nickUsuario));
		crit.createAlias("usuario", "usr", CriteriaSpecification.INNER_JOIN);
		IndicadorEntity indicador = (IndicadorEntity) crit.uniqueResult();

		Indicador indicadorBuscado = factoryModel.createIndicador(indicador);
		session.close();
		return indicadorBuscado;
	}

	@Override
	public List<Indicador> allByUser(String nickUsuario) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(IndicadorEntity.class);
		crit.createAlias("usuario", "usr", CriteriaSpecification.INNER_JOIN);
		crit.add(Restrictions.eq("usr.nick", nickUsuario));
		@SuppressWarnings("unchecked")
		List<IndicadorEntity> indicadores = (List<IndicadorEntity>) crit.list();

		List<Indicador> indicadorResult = indicadores.stream().map(b -> this.factoryModel.createIndicador(b)).collect(Collectors.toList());
		session.close();
		return indicadorResult;
	}

	@Override
	public void guardar(Indicador entity) throws DaoException {
		this.guardarEntidad(this.entityFactory.createIndicadorEntity(entity));

	}

	@Override
	public void modificar(Indicador entity) throws DaoException {
		this.modificarEntidad(this.entityFactory.createIndicadorEntity(entity));

	}

	@Override
	public void eliminar(Indicador entity) throws DaoException {
		this.eliminarEntidad(this.entityFactory.createIndicadorEntity(entity));

	}

}
