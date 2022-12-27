package com.utn.jmg.inversiones.service;

import java.util.List;

import com.utn.jmg.inversiones.dao.impl.MetodologiaDaoHibernate;
import com.utn.jmg.inversiones.model.Metodologia;
import com.utn.jmg.inversiones.exception.DaoException;
import org.springframework.stereotype.Service;

@Service
public class MetodologiaService  {
	private final MetodologiaDaoHibernate metodologiaDao;

	public MetodologiaService(MetodologiaDaoHibernate metodologiaDao) {
		this.metodologiaDao = metodologiaDao;
	}



	public void guardar(Metodologia metodologia) throws DaoException {
		metodologiaDao.guardar(metodologia);

	}


	public Metodologia buscarMetodologiaPorNombre(String nombreMetodologia, String nickUsuario) {

		return metodologiaDao.buscarMetodologia(nombreMetodologia,nickUsuario);
	}


	public void eliminar(Metodologia metodologia) throws DaoException {
		metodologiaDao.eliminar(metodologia);

	}


	public List<Metodologia> allByUser(String nick) {
		return metodologiaDao.allByUsuario(nick);
	}

}
