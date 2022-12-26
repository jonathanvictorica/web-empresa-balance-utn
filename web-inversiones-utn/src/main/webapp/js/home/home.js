function BuscarEmpresa(){
	location.href = "/InversionesWeb/empresa/buscarForm";
}

function CrearMetodologia(){
	location.href = "/InversionesWeb/metodologia/crearForm";
}

function BuscarMetodologia(){
	location.href = "/InversionesWeb/metodologia/buscarForm";
}

function CrearIndicador(){
	location.href = "/InversionesWeb/indicador/crearForm";
}

function BuscarIndicador(){
	location.href = "/InversionesWeb/indicador/buscarForm";
}

function cargarFoto(nombreSeleccionado){
	
	
	var imagen = document.getElementById("header-usuario-imagen");	

	if(nombreSeleccionado == "Jonathan" ){
		imagen.src = imagenJonathan;
	}else if( nombreSeleccionado == "Tomas" ){
		imagen.src = imagenTomas;
	}
}