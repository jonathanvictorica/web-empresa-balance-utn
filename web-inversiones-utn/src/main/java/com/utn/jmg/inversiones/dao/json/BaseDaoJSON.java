package com.utn.jmg.inversiones.dao.json;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class BaseDaoJSON {
	protected String filePath;
	protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();

	protected void modificarArchivo(String json) {
		try {

			FileWriter file = new FileWriter(this.filePath);
			file.write(json);
			file.flush();
			file.close();

		} catch (IOException e) {
			// manejar error
		}

	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
