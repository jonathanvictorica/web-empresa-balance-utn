var j = jQuery.noConflict();

function cargarFormularioBusqueda(){
	var formularioBusqueda = document.getElementById('formularioBusqueda');
	if(formularioBusqueda != null){
		var idAMostrar =j('input[name=radiosBusqueda]:checked').attr('class');
		esconderTextBoxs();
		mostrarSolo(idAMostrar);
	}
	else{
		setDivTextBoxVisible('txtCuit', 'block');
		setDivTextBoxVisible('txtRazonSocial', 'block');
	}
}

function setDivTextBoxVisible(unId, valor){
	document.getElementById(unId).parentNode.parentNode.style.display = valor;
}

function esconderTextBoxs(){
	setDivTextBoxVisible('txtCuit', 'none');
	setDivTextBoxVisible('txtRazonSocial', 'none');
}

function mostrarSolo(idAMostrar){
	esconderTextBoxs();
	setDivTextBoxVisible(idAMostrar, 'block');
}

function buscarEmpresaConDatos() {
	var tipoBusqueda = document.getElementById("radiosBusquedaCuit").checked ? "CUIT"
			: "Razon Social";

	var datosBusqueda = new Object();
	datosBusqueda.FiltroBusqueda = tipoBusqueda;

	if (tipoBusqueda == "CUIT") {
		datosBusqueda.cuit = document.getElementById("txtCuit").value;
	} else if (tipoBusqueda == "Razon Social") {
		datosBusqueda.razonSocial = document.getElementById("txtRazonSocial").value;
	}

	document.getElementById("datosEnviar").value = JSON
			.stringify(datosBusqueda);

}
