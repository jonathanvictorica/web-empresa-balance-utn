$(document).ready(function () {
	//var $ = jQuery.noConflict();
	completarComboMetodologias();
	
	$("#btnEliminar").click(function () {
		if($("#nombreMetodologia option:selected").val()!=""){
			
			
			metodologia = new Object();
			metodologia.nombreModificadoMetodologia = $("#nombreMetodologia option:selected").text();
			if (confirm("¿Está seguro que desea eliminar la metodologia?")) {

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
        return false;
    });
	
	$("#btnAplicar").click(function(){
		if($("#nombreMetodologia option:selected").val()!=""){
			var datosMetodologia = new Object();
			datosMetodologia.valorMetodologia = $("#nombreMetodologia option:selected").val();
			datosMetodologia.nombreMetodologia = $("#nombreMetodologia option:selected").text();
			datosMetodologia = JSON.stringify(datosMetodologia);
			location.href="/InversionesWeb/metodologia/formAplicarMetodologia?resultado="+datosMetodologia;
		}
	});
	
	$("#btnModificar").click(function(){
		if($("#nombreMetodologia option:selected").val()!=""){
			var datosMetodologia = new Object();
			datosMetodologia.valorMetodologia = $("#nombreMetodologia option:selected").val();
			datosMetodologia.nombreMetodologia = $("#nombreMetodologia option:selected").text();
			datosMetodologia = JSON.stringify(datosMetodologia);
			location.href="/InversionesWeb/metodologia/modificarForm?resultado="+datosMetodologia;
		}
	});
	/*$('#nombreMetodologia').on('change', function() {
		var datosMetodologia = new Object();
		datosMetodologia.valorMetodologia = $(this).val();
		datosMetodologia.nombreMetodologia = $(this).val();
		datosMetodologia = JSON.stringify(datosMetodologia);
		$("#datosAplicarMetodologia").val(datosMetodologia);
		$("#datosModificarMetodologia").val(datosMetodologia);

		//cambiarMetodologiaAplicar();
	});*/

});


function completarComboMetodologias(){
	//var $ = jQuery.noConflict();
	$.ajax({
		type : 'GET',
		cache : false,
		async : false,
		url : '../metodologia/todas',
		dataType : 'json',
		success : function(resultado) {
			resultado = resultado.replace(/&quot;/g, '"').replace("\"{", '{').replace("}\"", '}')
			var resultObject = JSON.parse(resultado);
			resultObject = resultObject.metodologias;
			for (var i = 0; i < resultObject.length; i++) {
				var option = document.createElement("option");
				option.value = resultObject[i].id;
				option.text = resultObject[i].nombre;
				$("#nombreMetodologia").append(option);
			}
		}
	});
}
/*
function cambiarMetodologiaAplicar(){
	
	var datosMetodologia = new Object();
	datosMetodologia.nombreMetodologia = $("#nombreMetodologia").val();
	
	datosMetodologia = JSON.stringify(datosMetodologia);
	$("#datosAplicarMetodologia").val(datosMetodologia);
	$("#datosModificarMetodologia").val(datosMetodologia);
	

}
*/