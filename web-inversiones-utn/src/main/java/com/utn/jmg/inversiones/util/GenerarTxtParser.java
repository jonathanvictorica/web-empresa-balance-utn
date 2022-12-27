package com.utn.jmg.inversiones.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerarTxtParser {
	
	public static void main(String[] args){
		//Add this to write a string to a file
		//
		try {
		    BufferedWriter out = new BufferedWriter(new FileWriter("documentacion/archivosParser/inputIndicador.txt"));
		    //out.write("aString\nthis is a\nttest");  //Replace with the string 
		    out.write("(i_indicador1 + c_cuenta2) * 85");                                         //you are trying to write  
		    out.close();
		}
		catch (IOException e)
		{
		    System.out.println("Exception ");
		    //e.printStackTrace();
		}

	}
	


}

/*
String[] args;

args[0] = "gramaticas.Indicador"; //package y nombre de la gram�tica
args[1] = "expresion"; 				//elemento con el que empieza la gram�tica
args[2] ="documentacion/archivosParser/inputIndicador.txt"; //archivo de entrada con la expresi�n
args[3] = "-tree"; 		//-gui: abre ventanita con el �rbol;-tree: imprime el �rbol en la consola
//parserAntlr(args);
try {
	org.antlr.v4.gui.TestRig.main(args);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
*/