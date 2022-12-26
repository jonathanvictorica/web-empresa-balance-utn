package com.utn.jmg.inversiones.model.metodologia;

public enum TipoCondiciones {
	
	TAXATIVAS_CON_VALOR("TaxativasConValor"), TAXATIVAS_CON_INDICADOR("TaxativasConIndicador"), PRIORIZABLE("Priorizable");

	private String tipo;

	private TipoCondiciones(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static TipoCondiciones get(String descripcion) {
		for (TipoCondiciones tipo : TipoCondiciones.values()) {
			if (tipo.getTipo().equals(descripcion)) {
				return tipo;
			}
		}
		return null;
	}

}
