
var j = jQuery.noConflict();

function cargarDatosEmpresa() {
	var str = datosResultado;
	str = str.replace(/&quot;/g, '"').replace("\"{", '{').replace("}\"", '}');
	var empresa = JSON.parse(str);
	empresa = empresa.empresa;
	document.getElementById("txtCuit").value = empresa.cuit;
	document.getElementById("txtRazonSocial").value = empresa.razonSocial;
	document.getElementById("txtActividad").value = empresa.descripActividad;
	buscarBalances(empresa.cuit,document.getElementById("cmbBalances"));
}

function buscarBalances(cuitIdentifcador,cmbBalances) {
	balanceModificando= new Object();
	balanceModificando.topeCuentas=0;
	
	var requestDatos = new Object();
	requestDatos.cuit = cuitIdentifcador;

	cuitIdentifcador = JSON.stringify(requestDatos);

	j.ajax({
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

function modificarBalance(){

	for(var i=1;i<=balanceModificando.topeCuentas;i++){
		
		balanceModificando.cuentasBalance[i-1].valor = document.getElementById("cuenta"+i).value;
	}
	var datosModificarBalance = JSON.stringify(balanceModificando);
	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosModificarBalance
		},
		url : '../balance/modificar',
		dataType : 'json',
		success : function(resultado) {
			alert("Balance Guardado con exito")
			 buscarBalance();
		},
		error : function(resultado) {
			
			
			alert("Balance no fue guardado");
		}
		
	});
	
	
}

var balanceModificando ;

function buscarBalance() {
  for(var i=1;i<=balanceModificando.topeCuentas;i++){
		// document.getElementById("tablaBalance tbody").deleteRow(i-1);
	  j("#fila"+i).remove();
	}
	
	var requestDatos = new Object();
	requestDatos.cuit = document.getElementById("txtCuit").value;
	requestDatos.periodoSeleccionado = document.getElementById("cmbBalances").value;
	datosEnviar = JSON.stringify(requestDatos);

	j.ajax({
		type : 'GET',
		cache : false,
		async : false,
		data : {
			datos : datosEnviar
		},
		url : '../balance/buscar',
		dataType : 'json',
		success : function(resultado) {

			
			
			resultado = resultado.replace(/&quot;/g, '"').replace("\"{", '{')
					.replace("}\"", '}')
			
			var resultObject = JSON.parse(resultado);
			
			balanceModificando= new Object();
			balanceModificando.cuit = requestDatos.cuit;
			balanceModificando.periodoSeleccionado = requestDatos.periodoSeleccionado;
			balanceModificando.cuentasBalance=[];
			
			
			resultObject = resultObject.balance.cuentas;
			balanceModificando.topeCuentas = resultObject.length;
			for (var i = 0; i < resultObject.length; i++) {
				addCuenta(resultObject[i].nombre, resultObject[i].valor,(i+1));
				var cuentaUnitaria = new Object();
				cuentaUnitaria.nombre=resultObject[i].nombre;
				cuentaUnitaria.eliminar=false;
				cuentaUnitaria.valor=resultObject[i].valor;
				balanceModificando.cuentasBalance[i]=cuentaUnitaria;
			}
			document.getElementById("PantallaVerBalance").style.display = "";

			
			//Completamos tambien buscar indicador
			var requestAplicarIndicador = new Object();
			requestAplicarIndicador.periodoSeleccionado=requestDatos.periodoSeleccionado;
			requestAplicarIndicador.cuit=requestDatos.cuit;
			document.getElementById("datosRequestAplicarIndicador").value= JSON.stringify(requestAplicarIndicador);
			
			
		}
	});

}


function addCuenta(nombreCuenta, valor,idCuenta) {

	j('#tablaBalance tbody').after(
			'<tr id='+'fila'+idCuenta+' ><td>' + nombreCuenta + '</td><td><input id="cuenta'+idCuenta+'" type="text" value="' + valor + '"></input></td></tr>');
}


