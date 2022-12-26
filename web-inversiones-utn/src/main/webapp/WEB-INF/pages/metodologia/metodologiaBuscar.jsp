<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="<s:url value='/css/metodologias/buscarMetod.css' />"></link>
<script src="<s:url value='/js/metodologias/buscarMetod.js' />"></script>

<div class="form-horizontal col-md-6 col-md-offset-3">
	<div class="form-group">
		<label class="col-md-4">Metodología</label>
		<div class="col-md-8">
			<select required class="form-control input-sm" id="nombreMetodologia" name="nombreMetodologia">
				<option value="">Seleccionar...</option>
				
			</select>
		</div>
	</div>
	<div class="form-group botones">
		<!-- 
		<s:form name="aplicarMetodologia" id="aplicarMetodologia" action="/metodologia/formAplicarMetodologia" method="GET" class="">
			<s:hidden id="datosAplicarMetodologia" name="resultado"></s:hidden>
			<button type="submit" id="btnAplicar" class="btn btn-success">Aplicar</button>
		</s:form>

		<s:form name="aplicarMetodologia" id="aplicarMetodologia" action="/metodologia/modificarForm" method="GET" class="">
			<s:hidden id="datosModificarMetodologia" name="resultado"></s:hidden>
			<button type="submit" id="btnModificar" class="btn btn-info">Modificar</button>
		</s:form>
		 -->
		<button type="button" id="btnAplicar" class="btn btn-success">Aplicar</button>
		<button type="button" id="btnModificar" class="btn btn-info">Modificar</button>
		<button type="button" id="btnEliminar" class="btn btn-danger">Eliminar</button>
	</div>

</div>
