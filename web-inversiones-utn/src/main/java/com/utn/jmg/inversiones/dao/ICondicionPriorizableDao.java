package com.utn.jmg.inversiones.dao;

import com.utn.jmg.inversiones.dao.IBaseDao;
import com.utn.jmg.inversiones.model.metodologia.CondicionPriorizable;

public interface ICondicionPriorizableDao extends IBaseDao<CondicionPriorizable> {

	CondicionPriorizable findByDescripcionByMetodologia(String nombreCondicion, Long id);

}
