package com.utn.jmg.inversiones.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.utn.jmg.inversiones.dao.entity.UsuarioEntity;
import com.utn.jmg.inversiones.model.Cuenta;
import com.utn.jmg.inversiones.model.Indicador;
import com.utn.jmg.inversiones.model.IndicadorEconomico;
import com.utn.jmg.inversiones.service.IndicadorService;
import org.springframework.stereotype.Component;

@Component
public class FormulaUtils {

	private static IndicadorService indicadorService = null;

	public FormulaUtils(IndicadorService indicadorService) {
		this.indicadorService = indicadorService;
	}

	public static void validarFormula(String formula) {
		// try {
		// BufferedWriter out = new BufferedWriter(new
		// FileWriter("documentacion/archivosParser/inputIndicador.txt"));
		// // out.write("aString\nthis is a\nttest"); //Replace with the string
		// out.write(formula.toLowerCase()); // you are trying to write
		// out.close();
		// } catch (IOException e) {
		// return false;
		// }
		// String[] argumentos = new String[4];
		// argumentos[0] = "gramaticas.Indicador";
		// argumentos[1] = "expresion";
		// argumentos[2] = "documentacion/archivosParser/inputIndicador.txt";
		// argumentos[3] = "-tree";
		//
		// TestRig testRig;
		// try {
		// testRig = new TestRig(argumentos);
		// if (argumentos.length >= 2) {
		// testRig.process();
		// }
		// } catch (Exception e) {
		//
		// return false;
		// }
		//
		// return true;

	}

	public static Double calcular(HashMap<String, Double> valores, String formula) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();

		ScriptEngine engine = manager.getEngineByName("JavaScript");
		manager.getEngineFactories();
		for (Map.Entry<String, Double> entry : valores.entrySet()) {
			engine.put(entry.getKey().replace(" ", ""), entry.getValue());
		}

		try {
			Object operation = engine.eval(formula.replace(" ", ""));
			if(operation.toString().equalsIgnoreCase("nan")){
				throw new ScriptException("Error. No se puede dividir por 0");
			}
			return (new BigDecimal(operation.toString())).setScale(2, 2).doubleValue();
		} catch (ScriptException e) {
			throw e;
		}
	}

	public static boolean calcularCondicion(HashMap<String, Double> valores, String formula) {
		ScriptEngineManager manager = new ScriptEngineManager();

		ScriptEngine engine = manager.getEngineByName("JavaScript");
		manager.getEngineFactories();
		for (Map.Entry<String, Double> entry : valores.entrySet()) {
			engine.put(entry.getKey().replace(" ", ""), entry.getValue());
		}

		try {
			Object operation = engine.eval(formula.replace(" ", ""));
			return (boolean) operation;
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<IndicadorEconomico> obtenerElementosFormula(String formula, String nickUsuario) {

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setNick(nickUsuario);
		
		List<IndicadorEconomico> elementosFormulas = new ArrayList<IndicadorEconomico>();
		// formula = formula.trim();

		String elementoUnitario = "";
		for (int i = 0; i < formula.length(); i++) {
			// if (formula.charAt(i) == ' ')
			// continue;
			if (esElemento(formula.charAt(i))) {
				if (elementoUnitario.isEmpty() && (formula.charAt(i) == ' '))
					continue;

				elementoUnitario += formula.charAt(i);
			} else {
				if (elementoUnitario.isEmpty())
					continue;

				if (elementoUnitario.charAt(elementoUnitario.length() - 1) == ' ') {
					elementoUnitario = elementoUnitario.substring(0, elementoUnitario.length() - 1);
				}

				if (elementoUnitario.charAt(0) == 'c') {
					Cuenta cuenta = new Cuenta(elementoUnitario.substring(2));
					elementosFormulas.add(cuenta);
				} else if (elementoUnitario.charAt(0) == 'i') {
					Indicador indicador = indicadorService.buscarIndicador(elementoUnitario.substring(2),nickUsuario);
					if(indicador.getUsuario()==null)
						indicador.setUsuario(usuario);
					elementosFormulas.add(indicador);
				}

				elementoUnitario = "";
			}
		}
		if (elementoUnitario.isEmpty() == false) {

			if (elementoUnitario.charAt(elementoUnitario.length() - 1) == ' ') {
				elementoUnitario = elementoUnitario.substring(0, elementoUnitario.length() - 1);
			}

			if (elementoUnitario.charAt(0) == 'c') {
				Cuenta cuenta = new Cuenta(elementoUnitario.substring(2));
				elementosFormulas.add(cuenta);
			} else if (elementoUnitario.charAt(0) == 'i') {
				Indicador indicador = indicadorService.buscarIndicador(elementoUnitario.substring(2),nickUsuario);
				if(indicador.getUsuario()==null)
					indicador.setUsuario(usuario);
				
				elementosFormulas.add(indicador);
			}

		}

		return elementosFormulas;
	}

	private static boolean esElemento(char caracter) {

		return !("+-/*()").contains(caracter + "");
	}
}
