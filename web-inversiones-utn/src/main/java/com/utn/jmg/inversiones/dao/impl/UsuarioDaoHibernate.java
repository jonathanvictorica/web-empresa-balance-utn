package com.utn.jmg.inversiones.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.utn.jmg.inversiones.dao.IUsuarioDao;
import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;

public class UsuarioDaoHibernate extends BaseDaoHibernate<UsuarioEntity> implements IUsuarioDao {

	@Override
	public UsuarioEntity findByNick(String nickUsuario) {
		Session session = this.getOpenSession();
		Criteria crit = session.createCriteria(UsuarioEntity.class);
		crit.add(Restrictions.eq("nick", nickUsuario));
		UsuarioEntity usuario = (UsuarioEntity) crit.uniqueResult();
		session.close();
		return usuario;
	}

}
