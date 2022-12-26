function guardarIndicador() {
	if (bCrearIndicador == false) {
		modificarIndicador();
	} else {
		crearIndicador();
	}

}

function volverAlMenu(){
	
	location.href = "/InversionesWeb/home/bienvenido";
}

var datosIndicadorProcesar;

function crearIndicador() {
	
	var j = jQuery.noConflict();
	
	datosIndicadorProcesar = new Object();	
	datosIndicadorProcesar.nombre= j("#txtNombreIndicador").val();
	datosIndicadorProcesar.formula= j("#txtFormula").val();
	var datosIndicador = JSON.stringify(datosIndicadorProcesar);

	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosIndicador
		},
		url : '../indicador/guardar',
		dataType : 'json',
		success : function(resultado) {
			alert("Indicador Guardado con exito");
			location.href = "/InversionesWeb/home/bienvenido";

		},
		error : function(resultado) {

			alert("Indicador Guardado con error.");
		}

	});
}

function modificarIndicador() {
	var j = jQuery.noConflict();
	datosIndicadorProcesar.nombre= j("#txtNombreIndicador").val();
	datosIndicadorProcesar.formula= j("#txtFormula").val();
	var datosIndicador = JSON.stringify(datosIndicadorProcesar);

	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosIndicador
		},
		url : '../indicador/modificar',
		dataType : 'json',
		success : function(resultado) {
			alert("Indicador Modificado con exito")
			location.href = "/InversionesWeb/home/bienvenido";
		},
		error : function(resultado) {

			alert("Indicador Modificado con error.");
		}

	});
}

function eliminarIndicador() {
	if (confirm("¿Está seguro que desea eliminar el indicador?")) {
		
		var j = jQuery.noConflict();
		
		datosIndicadorProcesar = new Object();	
		datosIndicadorProcesar.nombre= j("#cmbIndicadores").val();		
		var datosIndicador = JSON.stringify(datosIndicadorProcesar);

		j.ajax({
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
 inicializarPaginaBuscarIndicador();
			},
			error : function(resultado) {

				alert("Indicador Eliminado con error.")
			}

		});
		
	}
}

function completarComboCuentas(comboCuenta) {
	var j = jQuery.noConflict();
	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		url : '../pc/obtenerTodasLasCuentas',
		dataType : 'json',
		success : function(resultado) {

			resultado = resultado.replace(/&quot;/g, '"').replace("\"{", '{')
					.replace("}\"", '}')

			var resultObject = JSON.parse(resultado);

			resultObject = resultObject.indicadores;

			for (var i = 0; i < resultObject.length; i++) {
				var option = document.createElement("option");
				option.text = resultObject[i];
				comboCuenta.add(option);

			}

		}
	});
}

function completarComboIndicador(comboIndicador,nativos) {
	var j = jQuery.noConflict();
	var datosBusqueda = new Object();
	datosBusqueda.nativos= nativos;
	var datosIndicador = JSON.stringify(datosBusqueda);
	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		url : '../indicador/obtenerTodosLosIndicadores',
		data : {
			datos : datosIndicador
		},
		dataType : 'json',
		success : function(resultado) {

			resultado = resultado.replace(/&quot;/g, '"').replace("\"{", '{')
					.replace("}\"", '}')

			var resultObject = JSON.parse(resultado);

			resultObject = resultObject.indicadores;

			for (var i = 0; i < resultObject.length; i++) {
				var option = document.createElement("option");
				option.text = resultObject[i].nombre;
			
				comboIndicador.add(option);

			}

		}
	});
}

function cargarDatosInicialesIndicadores() {
	var j = jQuery.noConflict();
	indicadorForm = indicadorForm.replace(/&quot;/g, '"').replace("\"{", '{')
			.replace("}\"", '}')
	var resultObject = JSON.parse(indicadorForm);
	
	datosIndicadorProcesar = new Object();
	datosIndicadorProcesar.nombreAntiguo = resultObject.indicador.nombre;
	j("#txtNombreIndicador").val(resultObject.indicador.nombre);
	j("#txtFormula").val(resultObject.indicador.formula);
}

function inicializarPaginaBuscarIndicador() {
	var j = jQuery.noConflict();
	j("#cmbIndicadores").empty();
	completarComboIndicador(document.getElementById("cmbIndicadores"),false);
	cambiarValorIndicadorModificarComboSeleccionado();
}

function inicializarPaginaIndicadorForm() {

	if (bCrearIndicador == false) {
		cargarDatosInicialesIndicadores();

	}

	completarComboIndicador(document.getElementById("cmbIndicadores"),true);
	completarComboCuentas(document.getElementById("cmbCuentas"));

}

function cambiarValorIndicadorModificarComboSeleccionado() {

	var datosRequestModificarIndicador = new Object();
	datosRequestModificarIndicador.nombre = document
			.getElementById("cmbIndicadores").value;

	document.getElementById("datosRequestModificarIndicador").value = JSON
			.stringify(datosRequestModificarIndicador);
}

//--------------------------------------------------------------------------------
var estadoFormula = [];

function agregarCaracter(caracter){
	var text = document.getElementById('txtFormula').value;
	text = text + caracter;
	document.getElementById('txtFormula').value = text;
	estadoFormula.push(caracter);
}

function borrarFormula(){
	estadoFormula = [];
	document.getElementById('txtFormula').value = "";
}

function deshacer(){
	estadoFormula.pop();
	var formulaAnterior = estadoFormula.join("");
	if (formulaAnterior != null){
		document.getElementById('txtFormula').value = formulaAnterior;
	}
	else{
		borrarFormula();
	}
}

function agregarCuenta(){
	var selectCuentas = document.getElementById('cmbCuentas');
	var cuentaSeleccionada = 'c_'+selectCuentas.options[selectCuentas.selectedIndex].value;
	agregarCaracter(cuentaSeleccionada);
}

function agregarIndicador(){
	var selectIndicadores = document.getElementById('cmbIndicadores');
	var indicadorSeleccionado = 'i_'+selectIndicadores.options[selectIndicadores.selectedIndex].value;
	agregarCaracter(indicadorSeleccionado);
}