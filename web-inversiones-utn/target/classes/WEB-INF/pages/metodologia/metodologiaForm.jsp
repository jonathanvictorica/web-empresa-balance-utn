<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<link rel="stylesheet" href="<s:url value='/css/metodologias/crearMetod.css' />"></link>
<script src="<s:url value='/js/metodologias/crearMetod.js' />"></script>
<script src="<s:url value='/js/metodologias/metodologia.js' />"></script>
<script src="<s:url value='/js/condiciones/condicion.js' />"></script>
<form name="crearMetod" id="crearMetod" action="" method="POST"	class="form-horizontal">
	<input type="hidden" id="metodologiaDatos" ></input>
	<input type="hidden" id="condicionesTaxativasDatos" ></input>
	<input type="hidden" id="condicionesPriorizablesDatos" ></input>
	
	
	<div class="form-group row">
		<label class="col-md-4 col-md-offset-1">Nombre</label>
		<div class="col-md-6">
		<div class="input-group input-group-sm">
			<input type="text" id="txtNombreMetodologia" required name="nombre"
				class="form-control" value="" placeholder="Nombre" /> <span
				class="input-group-btn">
<!-- 				<button id="cmdGuardarMetodologia" onclick="guardarMetodologia();" type="button" class="btn btn-sm btn-success" data-toggle="tooltip" data-placement="right" title="Guardar el nombre"> -->
<!-- 					<span class="glyphicon glyphicon-ok"></span> -->
<!-- 				</button> -->
			</span>
		</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-4 col-md-offset-1">Condiciones Taxativas</label>
		<div class="col-md-6">
		<div class="input-group input-group-sm">
			<select required class="form-control" id="cmbCondicionTaxativa" name="cmbCondicionTaxativa">
			</select> 
			<span class="input-group-btn">
				<button id="btnVerCondicionTaxativa" type="button" class="btn btn-sm btn-info" data-toggle="tooltip" data-placement="right" title="Ver condición taxativa">
					<span class="glyphicon glyphicon-eye-open"></span>
				</button>
				<button id="btnCrearCondicionTaxativa" type="button" class="btn btn-sm btn-warning" data-toggle="tooltip" data-placement="right" title="Crear condición taxativa">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
			</span>
		</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-4 col-md-offset-1">Condiciones Priorizables</label>
		<div class="col-md-6">
		<div class="input-group input-group-sm">
			<select required class="form-control" id="cmbCondicionPriorizable" name="condPriorizables">
				
			</select> 
			<span class="input-group-btn">
				<button id="btnVerCondicionPriorizable"  type="button" class="btn btn-sm btn-info"
					data-toggle="tooltip" data-placement="right" title="Ver condición priorizable">
					<span class="glyphicon glyphicon-eye-open" onclick="habilitarFormularioCondicionPriorizableSelecionada();"></span>
				</button>
				<button id="btnCrearCondicionPriorizable" type="button" class="btn btn-sm btn-warning"
					data-toggle="tooltip" data-placement="right"
					title="Crear condición priorizable">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
			</span>
		</div>
		</div>
	</div>
		
		<div class="form-group botones">
			<div class="col-md-12">
				<button type="submit" id="btnSubmit" class="btn btn-success" onclick="guardarMetodologia();">Guardar
					Metodologia</button>
				<button type="button" class="btn btn-danger"  onclick="eliminarMetodologia();" >Eliminar
					Metodologia</button>
			</div>
		</div>
</form>

<jsp:include page="/WEB-INF/pages/condicionTaxativa/condicionTaxativaForm.jsp"></jsp:include>

<jsp:include page="/WEB-INF/pages/condicionPriorizable/condicionPriorizableForm.jsp"></jsp:include>
