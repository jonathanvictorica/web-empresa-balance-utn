$(document).ready(function () {
	var $ = jQuery.noConflict();
	cerrarCuadroTaxativas();
	cerrarCuadroPriorizables();
	
	$("#guardarNombre").click(function () {
        if ($("#nombre").val() == "") {
            alert("Debe ingresar un nombre");
            return false;
        }
        if (confirm("¿Está seguro que desea guardar el nombre?")) {
            alert($("#nombre").val());
            guardarMetodologia();
            return true;
        }
        return false;
    });

	/* *** botones para cuadro de condiciones taxativas *** */
	$("#btnCrearCondicionTaxativa").click(function(){
		cerrarCuadroPriorizables();
		cerrarCuadroTaxativas();
		//habilitarFormularioCondicionTaxativa();
		mostrarCuadroTaxativas();
	});
	$("#btnVerCondicionTaxativa").click(function(){
		cerrarCuadroPriorizables();
		cerrarCuadroTaxativas();		
		habilitarFormularioCondicionTaxativaSelecionada();
		mostrarCuadroTaxativas();
	});
	
	
	
	/* *** botones para cuadro de condiciones priorizables *** */
	$("#btnCrearCondicionPriorizable").click(function(){
		cerrarCuadroTaxativas();
		cerrarCuadroPriorizables();
		var options = $("#cmbCondicionTaxativa > option").clone();
		$('#priorizCmbCondicionTax').append(options);
		mostrarCuadroPriorizables();
	});
	$("#btnVerCondicionPriorizable").click(function(){
		cerrarCuadroTaxativas();
		cerrarCuadroPriorizables();
		mostrarCuadroPriorizables();
		habilitarFormularioCondicionPriorizableSelecionada();
	});
	
	$("#priorizBtnCerrarPriorizables").click(function(){
		cerrarCuadroPriorizables();
	});
	$("#priorizBtnEliminarPriorizables").click(function(){
		if(confirm("Está seguro que desea eliminar la condición seleccionada?")){
			alert("Falta hacer esto");
			cerrarCuadroPriorizables();
		}
		return false;
	});
	
	


});
function mostrarCuadroTaxativas(){
	var $ = jQuery.noConflict();
	$("#formCondTax").show();
}
function cerrarCuadroTaxativas(){
	var $ = jQuery.noConflict();
	$("#formCondTax").hide();
}

function mostrarCuadroPriorizables(){
	var $ = jQuery.noConflict();
	$("#formCondPrioriz").show();
}
function cerrarCuadroPriorizables(){
	var $ = jQuery.noConflict();
	$("#priorizTablaCondiciones").find('tbody').find('tr').remove();
	$("#nombreCondicionPrioriz").val('');
	$('#priorizCmbCondicionTax').find('option').remove();
	$("#formCondPrioriz").hide();
}

