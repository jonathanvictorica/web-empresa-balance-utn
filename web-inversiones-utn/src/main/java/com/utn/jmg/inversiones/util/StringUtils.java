package com.utn.jmg.inversiones.util;

import java.security.MessageDigest;

public class StringUtils {

	public static String getEncryptedPassword(String clearTextPassword) {

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(clearTextPassword.getBytes("UTF-8"));

			StringBuilder hexString = new StringBuilder();
			for (int i : hash) {
				hexString.append(Integer.toHexString(0XFF & i));
			}
			return hexString.toString();
		} catch (Exception e) {

		}
		return "";
	}
	
	public static String replaceAll(String cadena,char caracterViejo, String caracterNuevo){
		String cadenaAuxiliar = "";
		for(int i=0;i<cadena.length();i++){
			if(cadena.charAt(i) == caracterViejo){
				cadenaAuxiliar +=caracterNuevo;
			}else{
				cadenaAuxiliar +=cadena.charAt(i)+"";
			}
		}
		return cadenaAuxiliar;
	}
}