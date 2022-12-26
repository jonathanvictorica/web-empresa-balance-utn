package com.utn.jmg.inversiones.model.metodologia;

import java.util.List;

import com.utn.jmg.inversiones.model.dto.CondicionDto;

public class CondicionFactory {

	public CondicionMetodologia createCondicion(CondicionDto condicionDto, List<CondicionTaxativa> condicionesTaxativas) {
		if (condicionDto.getNombreCondicion().equalsIgnoreCase(TipoCondiciones.PRIORIZABLE.getTipo())) {
			return this.crearCondicionPriorizable(condicionDto, condicionesTaxativas);
		} else if (condicionDto.getNombreCondicion().equalsIgnoreCase(TipoCondiciones.TAXATIVAS_CON_INDICADOR.getTipo())) {
			return this.crearCondicionTaxativaConIndicador(condicionDto);
		} else if (condicionDto.getNombreCondicion().equalsIgnoreCase(TipoCondiciones.TAXATIVAS_CON_VALOR.getTipo())) {
			return this.crearCondicionTaxativaConValor(condicionDto);
		}
		return null;
	}

	public CondicionTaxativa createCondicionTaxativa(CondicionDto condicionDto) {
		if (condicionDto.getNombreCondicion().equalsIgnoreCase(TipoCondiciones.TAXATIVAS_CON_INDICADOR.getTipo())) {
			return this.crearCondicionTaxativaConIndicador(condicionDto);
		} else if (condicionDto.getNombreCondicion().equalsIgnoreCase(TipoCondiciones.TAXATIVAS_CON_VALOR.getTipo())) {
			return this.crearCondicionTaxativaConValor(condicionDto);
		}
		return null;
	}

	public CondicionPriorizable createCondicionPriorizable(CondicionDto condicionDto, List<CondicionTaxativa> condicionesTaxativas) {
		if (condicionDto.getNombreCondicion().equalsIgnoreCase(TipoCondiciones.PRIORIZABLE.getTipo())) {
			return this.crearCondicionPriorizable(condicionDto, condicionesTaxativas);
		}
		return null;
	}

	private CondicionTaxValor crearCondicionTaxativaConValor(CondicionDto condicionDto) {
		String comparador = condicionDto.getComparador();
		Double valor = condicionDto.getValor();
		CondicionTaxValor condicionTax = new CondicionTaxValor(condicionDto.gettIndicadorEconomico(), Comparador.get(comparador), valor, TipoCondiciones.TAXATIVAS_CON_VALOR,condicionDto.getDescripcionCondicion());
	
		return condicionTax;
	}

	private CondicionTaxIndEconomico crearCondicionTaxativaConIndicador(CondicionDto condicionDto) {
		String comparador = condicionDto.getComparador();
		CondicionTaxIndEconomico condicionTax = new CondicionTaxIndEconomico(condicionDto.gettIndicadorEconomico(), Comparador.get(comparador), condicionDto.gettIndicadorEconomicoComparar(), TipoCondiciones.TAXATIVAS_CON_INDICADOR,condicionDto.getDescripcionCondicion());
		
		return condicionTax;
	}

	private CondicionPriorizable crearCondicionPriorizable(CondicionDto condicionDto, List<CondicionTaxativa> condicionesTaxativas) {
		CondicionPriorizable condicion = new CondicionPriorizable();
		condicion.setDescripcion(condicionDto.getDescripcionCondicion());
		for (int i = 0; i < condicionesTaxativas.size(); i++) {
			for (int j = 0; j < condicionDto.getCondicionesTaxativas().size(); j++) {
				if (condicionesTaxativas.get(i).getDescripcion().equalsIgnoreCase(condicionDto.getCondicionesTaxativas().get(j))) {
					condicion.agregarCondicionTaxativa(condicionesTaxativas.get(i));
				}
			}
		}
		return condicion;
	}



	

}
