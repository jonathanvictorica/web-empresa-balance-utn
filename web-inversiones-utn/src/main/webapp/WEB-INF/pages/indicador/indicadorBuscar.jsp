<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="<s:url value='/css/indicadores/indicadorBuscar.css' />"></link>
<script src="<s:url value='/js/indicador/indicadorBuscar.js' />"></script>

<div class="form-horizontal col-md-6 col-md-offset-3">
	<div class="form-group">
		<label class="col-md-4">Indicador</label>
		<!-- Son hardcodeadas las opciones o las saco de la base? -->
		<div class="col-md-8">
			<select id="cmbIndicadores" required class="form-control input-sm">
			<option value="">Seleccionar...</option>
			</select>
		</div>
	</div>
	<div class="form-group botones">
		<button type="button" class="btn btn-success" id="btnModificar">Modificar</button>
		<button type="button" class="btn btn-danger" id="btnEliminar">Eliminar</button>
		<!--<s:form action="/indicador/modificarForm.action">
			<s:hidden id="datosRequestModificarIndicador" name="resultado"></s:hidden>

			<s:submit id="modificarIndicador" name="modificarIndicador" cssClass="btn btn-success" value="Modificar Indicador"></s:submit>
		</s:form>-->
	</div>
</div>

	