package com.utn.jmg.inversiones.dao;

import com.utn.jmg.inversiones.dao.entity.ComparadorEntity;

public interface IComparadorDao {
        ComparadorEntity findComparadorByDescripcion(String descripcion);
        
        
}
