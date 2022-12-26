<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
     var datosResultado = '<s:property  value="resultado"/>';

</script>
<script src="<s:url value='/js/empresa/empresaBuscar.js' />"></script>

<body onload="cargarDatosEmpresa();"></body>



<!-- Text input-->
<div class="form-group">
	<label class="col-md-4 control-label" for="txtCuit">Cuit</label>
	<div class="col-md-4">
		<input readonly="readonly" id="txtCuit" name="txtCuit" type="text" placeholder="" class="form-control input-md">

	</div>
</div>
<br>
<!-- Text input-->
<div class="form-group">
	<label class="col-md-4 control-label" for="txtRazonSocial">Razon Social</label>
	<div class="col-md-5">
		<input readonly="readonly" id="txtRazonSocial" name="txtRazonSocial" type="text" placeholder="" class="form-control input-md">

	</div>
</div>
<br>
<!-- Text input-->
<div class="form-group">
	<label class="col-md-4 control-label" for="txtActividad">Actividad</label>
	<div class="col-md-6">
		<input readonly="readonly" id="txtActividad" name="txtActividad" type="text" placeholder="" class="form-control input-md">

	</div>
</div>
<br>
<!-- Select Basic -->
<div class="form-group">
	<label class="col-md-4 control-label" for="cmbBalances">Balances</label>
	<div class="col-md-5">
		<select id="cmbBalances" name="cmbBalances" class="form-control">
		</select>
	</div>
</div>

<br>



<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="cmdVerCuentas"></label>
  <div class="col-md-4">
    <button id="cmdVerCuentas" onclick="buscarBalance();" name="cmdVerCuentas" class="btn btn-warning">Ver Cuentas</button>
  </div>
</div>
<br>


<jsp:include page="/WEB-INF/pages/balance/verBalance.jsp"></jsp:include>