$(document).ready(function(){
	//jQuery("#cmbIndicadores").empty();
	var $ = jQuery.noConflict();
	completarComboIndicador(false);

	$("#btnEliminar").click(function(){
		eliminarIndicador();
	});
	
	$("#btnModificar").click(function(){
		if($("#cmbIndicadores option:selected").val()!=""){
			var datosIndicador = new Object();
			datosIndicador.valor = $("#cmbIndicadores option:selected").val();
			datosIndicador.nombre = $("#cmbIndicadores option:selected").text();
			datosIndicador = JSON.stringify(datosIndicador);
			location.href="/InversionesWeb/indicador/modificarForm.action?resultado="+datosIndicador;
		}
		
	});
});


function eliminarIndicador() {
	if (confirm("¿Está seguro que desea eliminar el indicador?")) {
		datosIndicadorProcesar = new Object();	
		datosIndicadorProcesar.nombre= jQuery("#cmbIndicadores option:selected").text();		
		var datosIndicador = JSON.stringify(datosIndicadorProcesar);
		jQuery.ajax({
			type : 'GET',
			cache : false,
			async : false,
			data : {
				datos : datosIndicador
			},
			url : '../indicador/eliminar',
			dataType : 'json',
			success : function(resultado) {
				alert("Indicador Eliminado con exito")
				location.href = "/InversionesWeb/indicador/buscarForm";
			},
			error : function(resultado) {
				alert("Indicador Eliminado con error.")
			}

		});
	}
}

function completarComboIndicador(nativos) {
	var datosBusqueda = new Object();
	datosBusqueda.nativos= nativos;
	var datosIndicador = JSON.stringify(datosBusqueda);
	jQuery.ajax({
		type : 'GET',
		cache : false,
		async : false,
		url : '../indicador/obtenerTodosLosIndicadores',
		data : {
			datos : datosIndicador
		},
		dataType : 'json',
		success : function(resultado) {
			resultado = resultado.replace(/&quot;/g, '"').replace("\"{", '{').replace("}\"", '}')
			var resultObject = JSON.parse(resultado);
			resultObject = resultObject.indicadores;
			for (var i = 0; i < resultObject.length; i++) {
				var option = document.createElement("option");
				option.value = resultObject[i].id;
				option.text = resultObject[i].nombre;
				jQuery("#cmbIndicadores").append(option);
			}

		}
	});
}
