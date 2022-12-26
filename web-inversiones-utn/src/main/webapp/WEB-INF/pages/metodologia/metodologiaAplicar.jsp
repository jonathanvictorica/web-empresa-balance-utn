<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" href="<s:url value='/css/metodologias/aplicarMetod.css' />"></link>
<script src="<s:url value='/js/metodologias/aplicarMetod.js' />"></script>
<script type="text/javascript">
	var datosMetodologiaAplicar = '<s:property  value="resultado"/>';
</script>

<h3>Metodología </h3>

	<div class="row">
		<div class="col-md-6">
			<label class="col-md-4 col-sm-4 col-xs-4">CUIT</label>
			<div class="col-md-8 col-sm-8 col-xs-8">
				<div class="input-group input-group-sm">
					<input type="text" id="txtCuit" required name="cuit" class="form-control" value="" placeholder="Cuit" /> <span class="input-group-btn">
						<button id="btnAgregarEmpresa" type="button" class="btn btn-sm btn-success" data-toggle="tooltip" data-placement="right" title="Agregar empresa">
							<span class="glyphicon glyphicon-plus"></span>
						</button>
					</span>
				</div>
			</div>
			<div class="col-md-12">
				<div class="panel panel-success" id="empresasCargadas">
					<div class="panel-heading">EMPRESAS CARGADAS</div>
					<table class="table table-hover table-sm" id="tablaEmpresas">
						<thead>
							<tr>
								<th>Empresa</th>
								<th>CUIT</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-md-6" id="balances">
			<div class="col-md-12">
				<div class="form-group">
					<label class="col-md-5 control-label">Balances</label>
					<div class="col-md-7">
						<div class="input-group input-group-sm">
							<select required class="form-control" id="cmbBalances" name="cmbBalances">

							</select> <span class="input-group-btn">
								<button id="btnAgregarBalance" type="button" class="btn btn-sm btn-info" data-toggle="tooltip" data-placement="top" title="Agregar balance">
									<span class="glyphicon glyphicon-arrow-down"></span>
								</button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="form-group">
					<label class="col-md-5 control-label">Balances a analizar</label>
					<div class="col-md-7">
						<div class="input-group input-group-sm">
							<select required class="form-control" id="cmbBalancesAnalizar" name="cmbBalancesAnalizar">
							</select> <span class="input-group-btn">
								<button id="btnQuitarBalance" type="button" class="btn btn-sm btn-warning" data-toggle="tooltip" data-placement="bottom"
									title="Quitar balance">
									<span class="glyphicon glyphicon-arrow-up"></span>
								</button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr />
	<div class="row">
		<div class="col-md-6 col-sm-8 col-md-offset-3 col-sm-offset-2">
			<div class="form-group">
				<label class="col-md-4 control-label">Condición</label>
				<div class="col-md-8">
					<div class="input-group input-group-sm">
						<select required class="form-control" id="cmbCondicionesElegir" name="cmbCondicionesElegir">
							<option value="taxativas">Todas condiciones Taxativas</option>

						</select> <span class="input-group-btn">
							<button id="btnAplicarCondicion" type="button" class="btn btn-sm btn-info" data-toggle="tooltip" data-placement="top" title="Aplicar condición">
								<span class="glyphicon glyphicon-ok"></span>
							</button>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-12" id="resultadoOnline">
			
		</div>
	</div>
