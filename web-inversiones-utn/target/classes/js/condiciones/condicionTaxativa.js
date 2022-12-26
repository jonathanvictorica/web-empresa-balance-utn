$(document).ready(function(){
	var $ = jQuery.noConflict();
	
	$("#taxativasBtnGuardarTaxativas").click(function(){
		if (nombreAntiguoCondicionTaxativa == "") {
			crearCondicionTaxativa();
		} else {
			modificarCondicionTaxativa();
		}
		cerrarCuadroTaxativas();
	});
	
	$("#taxativasBtnCerrarTaxativas").click(function(){
		cerrarCuadroTaxativas();
	});
	$("#taxativasBtnEliminarTaxativas").click(function(){
		if(confirm("Está seguro que desea eliminar la condición seleccionada?")){
			var ind = buscarIndiceCondTaxativaPorNombre(nombreAntiguoCondicionTaxativa);
			condicionesTaxativas.splice(ind, 1);			
			nombreAntiguoCondicionTaxativa = "";
			refrescarComboCondicionesTaxativas();				
			cerrarCuadroTaxativas();
		}
		return false;
	});
	
});
var condicionesTaxativas = [];


function completarComboIndicadorEconomico(cmbTipoIndicador, cmbIndicador) {
	var j = jQuery.noConflict();
	j("#" + cmbIndicador).empty();
	if (j("#" + cmbTipoIndicador).val() == 'Cuenta') {

		completarComboCuentas(document.getElementById(cmbIndicador));
	} else if (j("#" + cmbTipoIndicador).val() == 'Indicador') {

		completarComboIndicador(document.getElementById(cmbIndicador), false);
	}
}

function completarComboIndicadorEconomicoCompara(cmbTipoIndicadorComparar,
		cmbIndicadorComparar, txtValorFijo) {

	var j = jQuery.noConflict();
	if ((j("#" + cmbTipoIndicadorComparar).val() == 'Cuenta')
			|| (j("#" + cmbTipoIndicadorComparar).val() == 'Indicador')) {
		j("#" + cmbIndicadorComparar).show();
		j("#" + txtValorFijo).hide();
		completarComboIndicadorEconomico(cmbTipoIndicadorComparar,
				cmbIndicadorComparar);
	} else if (j("#" + cmbTipoIndicadorComparar).val() == 'Valor Fijo') {
		j("#" + cmbIndicadorComparar).hide();
		j("#" + txtValorFijo).show();
	}

}

function buscarCondTaxativaPorNombre(nombreCondicionTaxativa) {
	var tope = condicionesTaxativas.length;
	for (var i = 0; i < tope; i++) {
		if (condicionesTaxativas[i].descripcionCondicion == nombreCondicionTaxativa) {
			return condicionesTaxativas[i];
		}
	}
}

function buscarIndiceCondTaxativaPorNombre(nombreCondicionTaxativa) {
	var tope = condicionesTaxativas.length;
	for (var i = 0; i < tope; i++) {
		if (condicionesTaxativas[i].descripcionCondicion == nombreCondicionTaxativa) {
			return i;
		}
	}
}

function refrescarComboCondicionesTaxativas(){
	var j = jQuery.noConflict();
	j("#cmbCondicionTaxativa").empty();
	completarComboCondicionesTaxativas(condicionesTaxativas);
}


function crearCondicionTaxativa() {
	//if(guardarCondicionTaxativa_BD(new_CondicionTaxativa())){
		condicionesTaxativas[condicionesTaxativas.length] = new_CondicionTaxativa();	
		nombreAntiguoCondicionTaxativa = "";
		refrescarComboCondicionesTaxativas();
	//}
}

function modificarCondicionTaxativa() {
	//if(modificarCondicionTaxativa_BD(new_CondicionTaxativa(),nombreAntiguoCondicionTaxativa)){
		condicionesTaxativas[buscarIndiceCondTaxativaPorNombre(nombreAntiguoCondicionTaxativa)] = new_CondicionTaxativa();
		
		nombreAntiguoCondicionTaxativa = "";
		refrescarComboCondicionesTaxativas();
//	}
}

function new_CondicionTaxativa() {
	var $ = jQuery.noConflict();
	var condicion = new Object();

	condicion.nombreIndicador = $("#cmbIndicadorTax").val();
	condicion.tipoIndicador = $("#cmbTipoIndicadorTax").val();
	condicion.comparador = $("#cmbComparador").val();
	condicion.valor = parseFloat($("#txtValorFijoTax").val() + "");
	condicion.nombreIndicadorComparar = $("#cmbIndicadorCompararTax").val();
	condicion.tipoIndicadorComparar = $("#cmbTipoIndicadorCompararTax").val();
	condicion.descripcionCondicion = $("#txtNombreCondicionTaxativa").val();
	condicion.condicionesTaxativas = [];

	condicion.nombreCondicion = condicion.tipoIndicadorComparar == "Valor Fijo" ? "TaxativasConValor"
			: "TaxativasConIndicador";
	return condicion;

}

//function eliminarCondicionTaxativa() {
//       if(eliminarCondicionTaxativa_BD(new_CondicionTaxativa())){
	   
//       }
//}

function habilitarFormularioCondicionTaxativa() {
	var $ = jQuery.noConflict();

	// Para modificacion borrar ya que esto es crear
	nombreAntiguoCondicionTaxativa = "";

	$("#cmbTipoIndicadorTax").val("Seleccione");
	$("#cmbTipoIndicadorCompararTax").val("Seleccione");
	$("#txtValorFijoTax").val("");
	$("#txtNombreCondicionTaxativa").val("");
}

var nombreAntiguoCondicionTaxativa = "";

function habilitarFormularioCondicionTaxativaSelecionada() {
	var $ = jQuery.noConflict();

	var condTaxativa = buscarCondTaxativaPorNombre($("#cmbCondicionTaxativa")
			.val());

	$("#cmbTipoIndicadorTax").val(condTaxativa.tipoIndicador);
	$("#cmbTipoIndicadorTax").change();
	$("#cmbTipoIndicadorCompararTax").val(condTaxativa.tipoIndicadorComparar == "" ? "Valor Fijo" :condTaxativa.tipoIndicadorComparar  );
	$("#cmbTipoIndicadorCompararTax").change();
	$("#cmbComparador").val(condTaxativa.comparador);
	$("#txtValorFijoTax").val(condTaxativa.valor);
	$("#txtNombreCondicionTaxativa").val(condTaxativa.descripcionCondicion);

	$("#cmbIndicadorTax").val(condTaxativa.nombreIndicador);
	$("#cmbIndicadorCompararTax").val(condTaxativa.nombreIndicadorComparar);
	// Para modificarlo
	nombreAntiguoCondicionTaxativa = condTaxativa.descripcionCondicion;

}