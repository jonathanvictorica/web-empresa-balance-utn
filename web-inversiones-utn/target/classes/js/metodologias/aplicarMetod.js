var cuitGlobalSeleccionado = "";

function agregarBalanceAnalizar(periodoSeleccionado) {
	var emp = buscarEmpresaPorCuit(cuitGlobalSeleccionado);
	emp.balancesSeleccionados[emp.balancesSeleccionados.length] = periodoSeleccionado;
	var index = emp.balancesEmpresa.indexOf(periodoSeleccionado);
	if (index > -1) {
		emp.balancesEmpresa.splice(index, 1);
	}

	var indiceRemplazar = buscarIndiceCuit(cuitGlobalSeleccionado);
	empresasSeleccionadas[indiceRemplazar] = emp;

}

function agregarBalanceNormal(periodoSeleccionado) {
	var emp = buscarEmpresaPorCuit(cuitGlobalSeleccionado);
	emp.balancesEmpresa[emp.balancesEmpresa.length] = periodoSeleccionado;
	var index = emp.balancesSeleccionados.indexOf(periodoSeleccionado);
	if (index > -1) {
		emp.balancesSeleccionados.splice(index, 1);
	}

	var indiceRemplazar = buscarIndiceCuit(cuitGlobalSeleccionado);
	empresasSeleccionadas[indiceRemplazar] = emp;
}

function completarComboCondiciones() {
	var resultado = datosMetodologiaAplicar.replace(/&quot;/g, '"').replace(
			"\"{", '{').replace("}\"", '}')
	var resultado = JSON.parse(resultado);

	for (var i = 0; i < resultado.condicionesPriorizables.length; i++) {
		var nombre = resultado.condicionesPriorizables[i].descripcionCondicion;
		var option = document.createElement("option");
		option.text = nombre;
		document.getElementById("cmbCondicionesElegir").add(option);
	}
}

$(document)
		.ready(
				function() {

					completarComboCondiciones();

					$("#btnAgregarBalance")
							.click(
									function() {
										if ($("#cmbBalances option:selected")
												.text() != "") {
											$("#cmbBalancesAnalizar")
													.append(
															'<option>'
																	+ $(
																			"#cmbBalances option:selected")
																			.text()
																	+ '</option>')
											agregarBalanceAnalizar($(
													"#cmbBalances option:selected")
													.text());
											$("#cmbBalances option:selected")
													.remove();
										}
									});

					$("#btnQuitarBalance")
							.click(
									function() {
										if ($(
												"#cmbBalancesAnalizar option:selected")
												.text() != "") {
											$("#cmbBalances")
													.append(
															'<option>'
																	+ $(
																			"#cmbBalancesAnalizar option:selected")
																			.text()
																	+ '</option>')
											agregarBalanceNormal($(
													"#cmbBalancesAnalizar option:selected")
													.text());
											$(
													"#cmbBalancesAnalizar option:selected")
													.remove();
										}
									});

					$("#btnAgregarEmpresa").click(function() {
						validarEmpresa($("#txtCuit"), $("#tablaEmpresas"));
					});

					$("#btnAplicarCondicion").click(function() {
						aplicarMetodologia();
					});

				});

function verBalancesEmpresa(cuit) {
	$("#cmbBalancesAnalizar").empty();
	$("#cmbBalances").empty();
	var empresaSeleccionada = buscarEmpresaPorCuit(cuit);
	if (empresaSeleccionada.balancesSeleccionados.length == 0) {
		buscarBalances(empresaSeleccionada.cuit, document
				.getElementById("cmbBalances"));
	} else {
		completarDatosCombo(document.getElementById("cmbBalancesAnalizar"),
				empresaSeleccionada.balancesSeleccionados);
		completarDatosCombo(document.getElementById("cmbBalances"),
				empresaSeleccionada.balancesEmpresa);
	}
	cuitGlobalSeleccionado = cuit;
}

function completarDatosCombo(combo, datosCombo) {

	for (var i = 0; i < datosCombo.length; i++) {
		var option = document.createElement("option");
		option.text = datosCombo[i];
		combo.add(option);

	}
}

function quitarEmpresa(cuit) {
	$("#empresasCargadas").find('tbody').find('tr#' + cuit).remove();
	empresasSeleccionadas.splice(buscarIndiceCuit(cuit), 1);

}

function buscarIndiceCuit(cuit) {
	for (var i = 0; i < empresasSeleccionadas.length; i++) {
		if (empresasSeleccionadas[i].cuit == cuit) {
			return i;
		}

	}
	return -1;
}

function buscarEmpresaPorCuit(cuit) {
	for (var i = 0; i < empresasSeleccionadas.length; i++) {
		if (empresasSeleccionadas[i].cuit = cuit) {
			return empresasSeleccionadas[i];
		}

	}
	return null;
}

var empresasSeleccionadas = [];

function aplicarMetodologia() {

	var datosRequest = new Object();
	var resultado = datosMetodologiaAplicar.replace(/&quot;/g, '"').replace(
			"\"{", '{').replace("}\"", '}')
	var resultado = JSON.parse(resultado);

	datosRequest.nombreMetodologia = resultado.metodologia.nombre;
	datosRequest.empresasSeleccionadas = empresasSeleccionadas;

	var condicionSeleccionada = $("#cmbCondicionesElegir").val();
	if (condicionSeleccionada == 'taxativas') {
		aplicarCondicionesTaxativas(datosRequest);
	} else {
		aplicarCondicionPriorizable(datosRequest);
	}

}

function generarCodigoHTMLTabla(camposCabeceras, valoresFilas, isTaxativas) {
	var codigoHTML = "<table id='tablaResultadosAplicarMetodologia' class='col-md-11 estiloTabla' ><tr>";
	for (var ic = 0; ic < camposCabeceras.length; ic++) {

		codigoHTML += "<th >" + camposCabeceras[ic] + "</th>";
	}

	codigoHTML += "</tr>";
	for (var filas = 0; filas < valoresFilas.length; filas++) {
		var objetoDato = valoresFilas[filas];
		codigoHTML += "<tr>"
		codigoHTML += "<td>" + objetoDato.cuit + "</td>";

		if (isTaxativas == true) {
			codigoHTML += "<td>" + objetoDato.resultado + "</td>";

		} else {
			for (var datosResultado = 0; datosResultado < objetoDato.condiciones.length; datosResultado++) {
				codigoHTML += "<td>" + objetoDato.condiciones[datosResultado]
						+ "</td>";
			}

		}
		codigoHTML += "</tr>";
	}
	codigoHTML += "</table>";
	
	$("#tablaResultadosAplicarMetodologia").remove();
    $("#resultadoOnline").html(codigoHTML);

}

function aplicarCondicionesTaxativas(datosRequest) {
	var datosRequest = JSON.stringify(datosRequest);

	$.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosRequest
		},
		url : '../metodologia/aplicarCondicionesTaxativas',
		dataType : 'json',
		success : function(resultado) {

			resultado = resultado.replace(/&quot;/g, '"').replace("\"{", '{')
					.replace("}\"", '}')

			var resultObject = JSON.parse(resultado);
		generarCodigoHTMLTabla(["CUIT","RESULTADO"], resultObject.resultadoCondicion, true);
		
			
			
			
		}
	});

}

function aplicarCondicionPriorizable(datosRequest) {
	datosRequest.condicionPriorizable = $("#cmbCondicionesElegir").val();

	var datosRequest = JSON.stringify(datosRequest);

	$.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosRequest
		},
		url : '../metodologia/aplicarCondicionPriorizable',
		dataType : 'json',
		success : function(resultado) {

			resultado = resultado.replace(/&quot;/g, '"').replace("\"{", '{')
					.replace("}\"", '}')

			var resultObject = JSON.parse(resultado);
			 generarCodigoHTMLTabla(resultObject.cabeceras, resultObject.resultadoCondicion, false);
		
		}
	});
}

function validarEmpresa(txtCuit, lstEmpresa) {

	if (buscarIndiceCuit(txtCuit.val()) != -1) {
		alert("La empresa ya existe en la tabla");
		return;
	}

	var requestDatos = new Object();
	requestDatos.cuit = txtCuit.val();
	requestDatos.FiltroBusqueda = "CUIT";
	cuitIdentifcador = JSON.stringify(requestDatos);

	$
			.ajax({
				type : 'GET',
				cache : false,
				async : false,
				data : {
					datos : cuitIdentifcador
				},
				url : '../empresa/validarEmpresa',
				dataType : 'json',
				success : function(resultado) {

					resultado = resultado.replace(/&quot;/g, '"').replace(
							"\"{", '{').replace("}\"", '}')

					var resultObject = JSON.parse(resultado);

					if (resultObject.result == 'success') {
						// AGREGAR a la lista
						var fila = '<tr id="' + resultObject.empresa.cuit
								+ '"><td>' + resultObject.empresa.razonSocial
								+ '</td>';
						fila += '<td>' + resultObject.empresa.cuit + '</td>';
						fila += '<td class="actions"><button type="button" onclick="quitarEmpresa(\''
								+ resultObject.empresa.cuit
								+ '\');" class="btn btn-xs btn-danger" data-toggle="tooltip" data-placement="right" title="Quitar"><span class="glyphicon glyphicon-remove"></span></button><button type="button" onclick="verBalancesEmpresa(\''
								+ resultObject.empresa.cuit
								+ '\');" class="btn btn-xs btn-info" data-toggle="tooltip" data-placement="right" title="Ver Balances"><span class="glyphicon glyphicon-eye-open"></span></button></td>';
						fila += '</tr>';
						$("#empresasCargadas").find("tbody").append(fila);

						var empresaSel = new Object();
						empresaSel.cuit = resultObject.empresa.cuit;
						empresaSel.balancesSeleccionados = [];
						empresaSel.balancesEmpresa = [];

						empresasSeleccionadas[empresasSeleccionadas.length] = empresaSel;

					} else {
						alert(resultObject.resultado);
					}

				}
			});
}

function buscarBalances(cuitIdentifcador, cmbBalances) {
	balanceModificando = new Object();
	balanceModificando.topeCuentas = 0;

	var requestDatos = new Object();
	requestDatos.cuit = cuitIdentifcador;

	cuitIdentifcador = JSON.stringify(requestDatos);

	$.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : cuitIdentifcador
		},
		url : '../balance/obtenerPeriodos',
		dataType : 'json',
		success : function(resultado) {

			resultado = resultado.replace(/&quot;/g, '"').replace("\"{", '{')
					.replace("}\"", '}')

			var resultObject = JSON.parse(resultado);

			resultObject = resultObject.balance;

			for (var i = 0; i < resultObject.length; i++) {
				var option = document.createElement("option");
				option.text = resultObject[i];
				cmbBalances.add(option);

			}

		}
	});

}
