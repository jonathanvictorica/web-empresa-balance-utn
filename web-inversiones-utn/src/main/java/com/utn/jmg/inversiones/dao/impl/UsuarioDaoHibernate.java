package com.utn.jmg.inversiones.dao.impl;

import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;
import com.utn.jmg.inversiones.dao.repo.IUsuarioRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class UsuarioDaoHibernate {


	private final IUsuarioRepository usuarioRepository;

	public UsuarioDaoHibernate(IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public UsuarioEntity findByNick(String nickUsuario) {
		return usuarioRepository.findTop1ByNick(nickUsuario);
	}


}
