$(document)
		.ready(
				function() {
					var $ = jQuery.noConflict();
					/* *** manejo de la tabla de condiciones taxativas *** */
					$("#priorizBtnAgregarCondicionTaxativa")
							.click(
									function() {
										var condicionSeleccionada = $('#priorizCmbCondicionTax option:selected');
										var fila = '<tr id="'
												+ condicionSeleccionada.val()
												+ '">';
										fila += '<td class="textCondicion" name="condicionesSeleccionadas" value="'
												+ condicionSeleccionada.text()
												+ '">'
												+ condicionSeleccionada.text()
												+ '</td>';
										fila += '<td class="actions"><button type="button" onclick="priorizQuitarCondicionTax(\''
												+ condicionSeleccionada.val()
												+ '\',\''
												+ condicionSeleccionada.text()
												+ '\');" class="btn btn-xs btn-danger" data-toggle="tooltip" data-placement="right" title="Quitar"><span class="glyphicon glyphicon-remove"></span></button></td>';
										fila += '</tr>';
										$("#priorizTablaCondiciones").find(
												"tbody").append(fila);
										condicionSeleccionada.remove();
										$('[data-toggle="tooltip"]').tooltip();
									});

					$("#priorizBtnSubmit")
							.click(
									function() {
										var condiciones = new Array();

										var nombreCondicion = $(
												"#nombreCondicionPrioriz")
												.val();
										if (nombreCondicion == "") {
											alert("Se debe ingresar un nombre para la condición priorizable");
											return false;
										}
										if ($("#priorizTablaCondiciones tbody tr").length == 0) {
											alert("Se debe ingresar al menos una condición taxativa");
											return false;
										}
										$("#priorizTablaCondiciones tbody tr")
												.each(
														function() {
															var condic = new Array();
															var idCondicion = $(
																	this).attr(
																	'id');
															var textoCondicion = $(
																	this)
																	.find(
																			'.textCondicion')
																	.html();
															condic
																	.push(idCondicion);
															condic
																	.push(textoCondicion);
															condiciones
																	.push(condic);
														});
										
										
										
										
										if (nombreAntiguoCondicionPriorizable == "") {
											crearCondicionPriorizables(condiciones);
										} else {
											modificarCondicionPriorizables(condiciones);
										}
										cerrarCuadroPriorizables();
									});
					
					
					$("#priorizBtnEliminarPriorizables").click(function(){
						if(confirm("Está seguro que desea eliminar la condición priorizable seleccionada?")){
							var ind = buscarIndiceCondPriorizablePorNombre(nombreAntiguoCondicionPriorizable);
							condicionesPriorizables.splice(ind, 1);			
							nombreAntiguoCondicionPriorizable = "";
							refrescarComboCondicionesPriorizables();			
							cerrarCuadroPriorizables();
						}
						return false;
					});

				});

function priorizQuitarCondicionTax(condicionValue, condicionText) {
	var $ = jQuery.noConflict();
	$('#priorizCmbCondicionTax').append(
			'<option value="' + condicionValue + '">' + condicionText
					+ '</option>');
	$("#priorizTablaCondiciones").find("tbody").find("#" + condicionValue)
			.remove();
}

var condicionesPriorizables = [];

function eliminarCondicionPriorizables() {

}

function crearCondicionPriorizables(condiciones) {
	condicionesPriorizables[condicionesPriorizables.length] = new_CondicionPriorizable(condiciones);
	nombreAntiguoCondicionPriorizable = "";
	refrescarComboCondicionesPriorizables();

}

function modificarCondicionPriorizables(condiciones) {
	condicionesPriorizables[buscarIndiceCondPriorizablePorNombre(nombreAntiguoCondicionPriorizable)] = new_CondicionPriorizable(condiciones);
	nombreAntiguoCondicionPriorizable = "";
	refrescarComboCondicionesPriorizables();
}


function buscarIndiceCondPriorizablePorNombre(nombreCondicionPriorizable) {
	var tope = condicionesPriorizables.length;
	for (var i = 0; i < tope; i++) {
		if (condicionesPriorizables[i].descripcionCondicion == nombreCondicionPriorizable) {
			return i;
		}
	}
}


function refrescarComboCondicionesPriorizables(){
	var j = jQuery.noConflict();
	j("#cmbCondicionPriorizable").empty();
	completarComboCondicionesPriorizables(condicionesPriorizables);
}

var nombreAntiguoCondicionPriorizable = "";

function new_CondicionPriorizable(condiciones) {
	var $ = jQuery.noConflict();
	var condicion = new Object();
	var comboSeleccionados = [];
   for(var i=0;i<condiciones.length;i++){
	   comboSeleccionados[i]= condiciones[i][1];
   }
	
	condicion.nombreIndicador = ""
	condicion.tipoIndicador = ""
	condicion.comparador = ""
	condicion.valor = parseFloat(0.00 + "");
	condicion.nombreIndicadorComparar = "";
	condicion.tipoIndicadorComparar = "";
	condicion.descripcionCondicion = $("#nombreCondicionPrioriz").val();
	condicion.condicionesTaxativas = comboSeleccionados;

	condicion.nombreCondicion = "Priorizable";
	return condicion;

}

function buscarCondPriorizablePorNombre(nombreCondicionPriorizable) {
	var tope = condicionesPriorizables.length;
	for (var i = 0; i < tope; i++) {
		if (condicionesPriorizables[i].descripcionCondicion == nombreCondicionPriorizable) {
			return condicionesPriorizables[i];
		}
	}
}

function habilitarFormularioCondicionPriorizableSelecionada() {
	var $ = jQuery.noConflict();

	var condPriorizable = buscarCondPriorizablePorNombre($("#cmbCondicionPriorizable")
			.val());

	
	
	
	$("#nombreCondicionPrioriz").val(condPriorizable.descripcionCondicion);
	
	for(var i=0; i<condPriorizable.condicionesTaxativas.length;i++ ){
		
		var condicionSeleccionada = condPriorizable.condicionesTaxativas[i];
			var fila = '<tr id="'
				+ condicionSeleccionada
				+ '">';
		fila += '<td class="textCondicion" name="condicionesSeleccionadas" value="'
				+ condicionSeleccionada
				+ '">'
				+ condicionSeleccionada
				+ '</td>';
		fila += '<td class="actions"><button type="button" onclick="priorizQuitarCondicionTax(\''
				+ condicionSeleccionada
				+ '\',\''
				+ condicionSeleccionada
				+ '\');" class="btn btn-xs btn-danger" data-toggle="tooltip" data-placement="right" title="Quitar"><span class="glyphicon glyphicon-remove"></span></button></td>';
		fila += '</tr>';
		$("#priorizTablaCondiciones").find(
				"tbody").append(fila);


	}

	
	// Para modificarlo
	nombreAntiguoCondicionPriorizable = condPriorizable.descripcionCondicion;

}

/*
 * 
 * 
 * var condicionAAgregar = null, posicionOption = null, selectCondTaxPosibles =
 * document.getElementById("condicionesTaxPosibles");
 * 
 * 
 * selectCondTaxPosibles.addEventListener('click', function(){
 * 
 * posicionOption = selectCondTaxPosibles.selectedIndex; condicionAAgregar =
 * this.options[posicionOption].text; });
 * 
 * function agregarTaxativa(){
 * 
 * if (condicionAAgregar) {
 * 
 * var x = document.getElementById("condicionesTax"), option =
 * document.createElement("option");
 * 
 * option.text = condicionAAgregar; x.add(option);
 * selectCondTaxPosibles.remove(posicionOption); condicionAAgregar =null;
 * 
 * }};
 * 
 * function quitarTaxativa(unId) {
 * 
 * var x = document.getElementById(unId); var option =
 * document.createElement("option"); option.text =
 * x.options[x.selectedIndex].text; selectCondTaxPosibles.add(option);
 * x.remove(x.selectedIndex);
 *  };
 * 
 */

