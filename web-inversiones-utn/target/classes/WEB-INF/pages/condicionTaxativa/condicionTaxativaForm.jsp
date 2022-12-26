<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
 
<script src="<s:url value='/js/indicador/indicador.js' />"></script>
<script src="<s:url value='/js/condiciones/condicionTaxativa.js' />"></script>
 

<form id="formCondTax" name="formCondTax" action="" class="form-horizontal col-md-10 col-md-offset-1" method="POST">
	<div class="panel panel-warning" >
		<div class="panel-heading">
			<h3 class="panel-title">CONDICION TAXATIVA</h3>
		</div>
		<div class="panel-body">
			<div class="form-group">
				<div class="col-md-8 col-md-offset-2">
					<!-- <input type="text" id="nombreCondicionTax" required name="nombreCondicionTax" class="form-control" value="" placeholder="Nombre de la Condición" /> -->
					<input type="text" id="txtNombreCondicionTaxativa" required name="nombreCondicion" class="form-control" value="" placeholder="Nombre de la Condición" /> 
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-8 col-md-offset-2">
					<select required class="form-control" id="cmbTipoIndicadorTax" name="tipoIndicador" onchange="completarComboIndicadorEconomico('cmbTipoIndicadorTax','cmbIndicadorTax');">
				    		<option>Seleccione</option>
				    	<option>Indicador</option>
						<option>Cuenta</option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-md-8 col-md-offset-2">
					<select required class="form-control" id="cmbIndicadorTax" name="tipoIndicador">
						
					</select>
				</div>
			</div>
			
			
			
			<div class="form-group">
				<div class="col-md-8 col-md-offset-2">
					<select required class="form-control" id="cmbComparador" name="comparador">
						<option value="MAYOR">></option>
						<option value="MENOR"><</option>						
						<option value="IGUAL">=</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-8 col-md-offset-2">
					<select required class="form-control" id="cmbTipoIndicadorCompararTax" name="tipoIndicador" onchange="completarComboIndicadorEconomicoCompara('cmbTipoIndicadorCompararTax','cmbIndicadorCompararTax','txtValorFijoTax');">
						<option>Seleccione</option>
						<option>Indicador</option>
						<option>Cuenta</option>
						<option>Valor Fijo</option>
					</select>
				</div>
			</div>
			
			
			<div class="form-group">
				<div class="col-md-8 col-md-offset-2">
					<select required class="form-control" id="cmbIndicadorCompararTax" name="tipoIndicador">
						
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-8 col-md-offset-2">
					<input type="text" id="txtValorFijoTax" required name="nombreCondicion" class="form-control" value="" placeholder="0.00" /> 
				</div>
			</div>
			
			<div class="form-group botones">
				<div class="col-md-12">
					<button type="button" id="taxativasBtnGuardarTaxativas" class="btn btn-warning">Guardar</button>
					<button type="button" id="taxativasBtnEliminarTaxativas" class="btn btn-danger">Eliminar</button>
					<button type="button" id="taxativasBtnCerrarTaxativas" class="btn btn-default">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
</form>
