var metodologia = new Object();
var condicionesPriorizables = [];

function guardarMetodologia() {
	
	var j = jQuery.noConflict();
	
	if(j("#metodologiaDatos").val()==""){
	   metodologia = new Object();
	
	}else{
		metodologia = JSON.parse(j("#metodologiaDatos").val());
		
	}
	metodologia.nombreMetodologia =  j("#txtNombreMetodologia").val();
	metodologia.condicionesTaxativas =JSON.parse( j("#condicionesTaxativasDatos").val());
    metodologia.condicionesPriorizables= JSON.parse(j("#condicionesPriorizablesDatos").val());

	
	if (bCrearMetodologia == false) {		
		modificarMetodologia();
	} else {
		crearMetodologia();
	}
}

function crearMetodologia() {
	var datosMetodologia = JSON.stringify(metodologia);
	var j = jQuery.noConflict();
	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosMetodologia
		},
		url : '../metodologia/guardar',
		dataType : 'json',
		success : function(resultado) {
			alert("Metodologia creada con exito");
			location.href = "/InversionesWeb/home/bienvenido";
		},
		error : function(resultado) {

			alert("Metodologia creada con error.");
			
		}

	});
}

function modificarMetodologia() {
	var datosMetodologia = JSON.stringify(metodologia);
	var j = jQuery.noConflict();
	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosMetodologia
		},
		url : '../metodologia/modificar',
		dataType : 'json',
		success : function(resultado) {
			alert("Metodologia modificada con exito");
			location.href = "/InversionesWeb/home/bienvenido";
		},
		error : function(resultado) {

			alert("Metodologia modificada con error.");
			
		}

	});
}

function eliminarMetodologia() {

	if (confirm("¿Está seguro que desea eliminar el indicador?")) {

		var datosMetodologia = JSON.stringify(metodologia);
		var j = jQuery.noConflict();
		j.ajax({
			type : 'GET',
			cache : false,
			async : false,
			data : {
				datos : datosMetodologia
			},
			url : '../metodologia/eliminar',
			dataType : 'json',
			success : function(resultado) {
				alert("Metodologia eliminada con exito");
				location.href = "/InversionesWeb/metodologia/buscarForm";
               
			},
			error : function(resultado) {

				alert("Metodologia eliminada con error.");
				location.href = "/InversionesWeb/metodologia/buscarForm";
			}

		});

	}

}

function completarComboCondicionesTaxativas(condicionesTaxativasMod) {
	var combo = document.getElementById("cmbCondicionTaxativa");

	for (var i = 0; i < condicionesTaxativasMod.length; i++) {
		var option = document.createElement("option");
		option.text = condicionesTaxativasMod[i].descripcionCondicion;
		combo.add(option);

	}
	var j = jQuery.noConflict();
	j("#condicionesTaxativasDatos").val( JSON.stringify(condicionesTaxativasMod));
	condicionesTaxativas = condicionesTaxativasMod;
}

function completarComboCondicionesPriorizables(condicionesPriorizablesMod) {
	var combo = document.getElementById("cmbCondicionPriorizable");
	for (var i = 0; i < condicionesPriorizablesMod.length; i++) {
		var option = document.createElement("option");
		option.text = condicionesPriorizablesMod[i].descripcionCondicion;
		combo.add(option);

	}
	var j = jQuery.noConflict();
	j("#condicionesPriorizablesDatos").val( JSON.stringify(condicionesPriorizablesMod));
	condicionesPriorizables = condicionesPriorizablesMod;
}

function inicializarPaginaMetodologiaForm() {
	var j = jQuery.noConflict();
	if (bCrearMetodologia == false) {

		metodologiaForm = metodologiaForm.replace(/&quot;/g, '"').replace(
				"\"{", '{').replace("}\"", '}')
		var resultObject = JSON.parse(metodologiaForm);

		metodologia = new Object();
		metodologia.nombreModificadoMetodologia = resultObject.metodologia.nombre;

		
		j("#metodologiaDatos").val( JSON.stringify(metodologia));
		
		completarComboCondicionesTaxativas(resultObject.condicionesTaxativas);
		completarComboCondicionesPriorizables(resultObject.condicionesPriorizables);
		j("#txtNombreMetodologia").val(resultObject.metodologia.nombre);
	}else{
		j("#condicionesTaxativasDatos").val( "[]");
		j("#condicionesPriorizablesDatos").val( "[]");
		
	}
}