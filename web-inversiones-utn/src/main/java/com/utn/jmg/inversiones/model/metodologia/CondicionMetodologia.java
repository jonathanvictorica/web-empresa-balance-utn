package com.utn.jmg.inversiones.model.metodologia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utn.jmg.inversiones.model.Metodologia;
import com.utn.jmg.inversiones.model.dto.CondicionDto;

public abstract class CondicionMetodologia {
	protected String descripcion;
	
	protected Long id;

	private Metodologia metodologia;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String toJSON() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(getCondicionDto());
	}

	public abstract CondicionDto getCondicionDto();

	public abstract String getFormulaCondicion();

	public Metodologia getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
