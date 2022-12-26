package com.utn.jmg.inversiones.dao;

import com.utn.jmg.inversiones.dao.IBaseDao;
import com.utn.jmg.inversiones.model.metodologia.CondicionTaxativa;

public interface ICondicionTaxativaDao extends IBaseDao<CondicionTaxativa> {

	CondicionTaxativa findByDescripcionByMetodologia(String nombreCondicion, Long idMetodologia);

}
