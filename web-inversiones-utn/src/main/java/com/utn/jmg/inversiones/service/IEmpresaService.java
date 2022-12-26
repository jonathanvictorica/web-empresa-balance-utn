package com.utn.jmg.inversiones.service;

import com.utn.jmg.inversiones.model.Empresa;

public interface IEmpresaService {

	Empresa findEmpresaByCuit(String cuit);

	Empresa findEmpresaByRazonSocial(String razonSocial);

}
