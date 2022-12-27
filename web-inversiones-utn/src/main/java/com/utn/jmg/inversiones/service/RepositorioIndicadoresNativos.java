package com.utn.jmg.inversiones.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.utn.jmg.inversiones.model.Indicador;
import org.springframework.stereotype.Service;

@Service
public class RepositorioIndicadoresNativos {
	private List<Indicador> indicadoresNativos;

	public RepositorioIndicadoresNativos() {
		indicadoresNativos = new ArrayList<Indicador>();
		//indicadoresNativos.add(new Indicador("ROE", "(c_Resultado Ejercicio / c_Patrimonio Neto)"));
		indicadoresNativos.add(new Indicador("ROA", "(c_Utilidades o perdidas acumuladas / ( (c_Efectivo en Caja+ c_Fondos en Bancos+ c_Inversiones+ c_Cuentas por cobrar+ c_Deudores diversos+ c_Inventario de Mercancia + c_Impuesto pagados por anticipados) + (c_Terrenos + c_Edificios + c_Mobiliario y equipo de oficina + c_Equipo rodante + c_Herramientas y enseres ))) "));

		// Esto se agrega a medida que se creen mas indicadores nativos
	}

	public List<Indicador> getIndicadoresNativos() {
		return indicadoresNativos;

	}

	public Indicador findIndicadorByNombre(String nombre) {
		try {
			return indicadoresNativos.stream().filter(ind -> ind.getNombre().equals(nombre)).findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}

	public List<String> getIndicadoresNativosName() {
		try {
			return getIndicadoresNativos().stream().map(ind -> ind.getNombre()).collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}
}
