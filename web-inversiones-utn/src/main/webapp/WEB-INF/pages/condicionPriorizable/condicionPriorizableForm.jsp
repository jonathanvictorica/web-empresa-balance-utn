<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<script src="<s:url value='/js/condiciones/condicionPriorizable.js' />"></script>

<form id="formCondPrioriz" name="formCondPrioriz" action="" class="form-horizontal col-md-10 col-md-offset-1" method="POST">
	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">CREAR CONDICION PRIORIZABLE</h3>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<label class="col-md-4 col-md-offset-1 ">Nombre de la condición</label>
				<div class="col-md-6">
					<input type="text" id="nombreCondicionPrioriz" required name="nombreCondicionPrioriz" class="form-control input-sm" /> 
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 col-md-offset-1 ">Condiciones Taxativas</label>
				<div class="col-md-6">
					<div class="input-group input-group-sm">
						<select required class="form-control" id="priorizCmbCondicionTax" name="priorizCmbCondicionTax">
						</select> 
						<span class="input-group-btn">
							<button id="priorizBtnAgregarCondicionTaxativa" type="button" class="btn btn-sm btn-primary"
								data-toggle="tooltip" data-placement="right" title="Agregar condición a la lista">
								<span class="glyphicon glyphicon-plus"></span>
							</button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-8 col-md-offset-2">
					<table class="table table-hover table-sm " id="priorizTablaCondiciones">
						<thead>
							<tr>
								<th colspan="2">Condiciones</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="form-group botones">
				<div class="col-md-12">
					<button type="button" id="priorizBtnSubmit" class="btn btn-info">Guardar</button>
					<button type="button" id="priorizBtnEliminarPriorizables"  class="btn btn-danger">Eliminar</button>
					<button type="button" id="priorizBtnCerrarPriorizables"  class="btn btn-default">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
</form>