

function inicializarPaginaIndicadorAplicar() {
	completarComboIndicador(document.getElementById("cmbIndicadores"), true);
}
var topeCuentas = 0;
function aplicarIndicador() {
	var j = jQuery.noConflict();
	for (var i = 1; i <= topeCuentas; i++) {
		// document.getElementById("tablaBalance tbody").deleteRow(i-1);
		j("#fila" + i).remove();
	}

	var empresaBalanceProcesarForm = empresaBalanceProcesar.replace(/&quot;/g,
			'"').replace("\"{", '{').replace("}\"", '}');

	empresaBalanceProcesarForm = JSON.parse(empresaBalanceProcesarForm);

	var requestDatos = new Object();
	requestDatos.nombre = document.getElementById("cmbIndicadores").value;
	requestDatos.cuit = empresaBalanceProcesarForm.cuit;
	requestDatos.periodoSeleccionado = empresaBalanceProcesarForm.periodoSeleccionado;
	var datosEnviar = JSON.stringify(requestDatos);

	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosEnviar
		},
		url : '../indicador/aplicarIndicador',
		dataType : 'json',
		success : function(resultado) {

			resultado = resultado.replace(/&quot;/g, '"').replace("\"{", '{')
					.replace("}\"", '}')

			var resultObject = JSON.parse(resultado);
			
			if(resultObject.result == 'success'){
			
	
				j("#txtResultadoIndicador").val(resultObject.resultadoIndicador);
				var cuentasIterator = resultObject.cuentas;
				topeCuentas = cuentasIterator.length;
				for (var i = 0; i < cuentasIterator.length; i++) {
					var cuentaUnir = cuentasIterator[i];
					addCuenta(cuentaUnir.nombre, cuentaUnir.valor, (i + 1));
	
				}
			}else if(resultObject.result == 'error'){
				alert(resultObject.resultado);
			}
			

		}
	});
}

function addCuenta(nombreCuenta, valor, idCuenta) {
	var j = jQuery.noConflict();
	j('#tablaCuentas tbody').append(
			'<tr id=' + 'fila' + idCuenta + ' >'+
				'<td>' + nombreCuenta + '</td>'+
				'<td><label>'+ valor + '</label></td>'+
			'</tr>');
}
