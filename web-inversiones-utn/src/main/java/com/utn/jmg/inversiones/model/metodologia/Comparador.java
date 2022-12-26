package com.utn.jmg.inversiones.model.metodologia;

public enum Comparador {

	MAYOR("Mayor", ">"), MENOR("MENOR", "<"), IGUAL("IGUAL", "=="), MAYOR_IGUAL("MAYOR_IGUAL", ">="), MENOR_IGUAL("MENOR_IGUAL", "<=");

	private String nombre;
	private String signo;

	private Comparador(String nombre, String signo) {
		this.nombre = nombre;
		this.signo = signo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSigno() {
		return signo;
	}

	public void setSigno(String signo) {
		this.signo = signo;
	}

	public static Comparador get(String descripcion) {
		for (Comparador tipo : Comparador.values()) {
			if (tipo.getNombre().equalsIgnoreCase(descripcion)) {
				return tipo;
			}
		}
		return null;
	}

}
