function guardarCondicionTaxativa_BD(condicionTaxativa){

var datosEnviar = JSON.stringify(condicionTaxativa);
	var j = jQuery.noConflict();
	
	
	//j("#metodologiaDatos")
	
	
	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosEnviar
		},
		url : '../condicion/guardarCondicionTaxativa',
		dataType : 'json',
		success : function(resultado) {
			alert("Condicion creada con exito");
			return true;
		},
		error : function(resultado) {
			alert(resultado.resultado);
			return false;
		}
	});
}

function modificarCondicionTaxativa_BD(condicionTaxativa,nombreAntiguoCondicionTaxativa){
	var datosEnviar = JSON.stringify(condicionTaxativa);
	var j = jQuery.noConflict();
	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosEnviar
		},
		url : '../condicion/modificarCondicionTaxativa',
		dataType : 'json',
		success : function(resultado) {
			alert("Condicion modificada con exito");
			return true;
		},
		error : function(resultado) {
			alert(resultado.resultado);
			return false;
		}
	});
}

function eliminarCondicionTaxativa_BD(condicionTaxativa){
	var datosEnviar = JSON.stringify(condicionTaxativa);
	var j = jQuery.noConflict();
	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosEnviar
		},
		url : '../condicion/eliminarCondicionTaxativa',
		dataType : 'json',
		success : function(resultado) {
			alert("Condicion eliminada con exito");
			return true;
		},
		error : function(resultado) {
			alert(resultado.resultado);
			return false;
		}
	});
}



function guardarCondicionPriorizable_BD(condicionPriorizable){
	var datosEnviar = JSON.stringify(condicionPriorizable);
	var j = jQuery.noConflict();
	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosEnviar
		},
		url : '../condicion/guardarCondicionPriorizable',
		dataType : 'json',
		success : function(resultado) {
			alert("Condicion guardada con exito");
			return true;
		},
		error : function(resultado) {
			alert(resultado.resultado);
			return false;
		}
	});
}

function modificarCondicionPriorizable_BD(condicionPriorizable,nombreAntiguoCondicionPriorizable){
	var datosEnviar = JSON.stringify(condicionPriorizable);
	var j = jQuery.noConflict();
	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosEnviar
		},
		url : '../condicion/modificarCondicionPriorizable',
		dataType : 'json',
		success : function(resultado) {
			alert("Condicion modificada con exito");
			return true;
		},
		error : function(resultado) {
			alert(resultado.resultado);
			return false;
		}
	});
}

function eliminarCondicionPriorizable_BD(condicionPriorizable){
	var datosEnviar = JSON.stringify(condicionPriorizable);
	var j = jQuery.noConflict();
	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosEnviar
		},
		url : '../condicion/eliminarCondicionPriorizable',
		dataType : 'json',
		success : function(resultado) {
			alert("Condicion eliminada con exito");
			return true;
		},
		error : function(resultado) {
			alert(resultado.resultado);
			return false;
		}
	});
}