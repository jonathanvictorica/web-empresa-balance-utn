
function ingresarAplicacion() {

	var nombre;
	var pass;
	nombre = document.getElementById("txtUser").value;
	pass = document.getElementById("txtPassword").value;
	if (nombre == "") {
		alert("Falta el Nombre");
		return;
	}

	if (pass == "") {
		alert("Falta el password");
		return;
	}
	document.getElementById("datosEnviar").value = "{'nickUsuario':'" + nombre
			+ "','pass':'" + pass + "'}";

	document.getElementById("formularioLogin").submit();
}
