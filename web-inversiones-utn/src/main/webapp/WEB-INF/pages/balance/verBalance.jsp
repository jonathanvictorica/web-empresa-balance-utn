<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>


<link rel="stylesheet" href="<s:url value='/css/balance/balance.css' />"></link>
<div id="PantallaVerBalance" style="display: none;">

	<section>

		<br>

		<div>
			<table id="tablaBalance">
				<thead>
					<tr>
						<th>Nombre de cuenta</th>
						<th>Valor</th>
					</tr>
				</thead>
				<tbody class="agregarCuentasConValor">


				</tbody>
			</table>
		</div>
	</section>

	<!-- Button -->
	<div class="form-group">
		<label class="col-md-4 control-label" for="cmdGuardarBalance"></label>
		<div class="col-md-4">
			<button id="cmdGuardarBalance" name="cmdGuardarBalance" onclick="modificarBalance();" class="btn btn-success">Guardar Balance</button>
		</div>
	</div>
	<br>

	<s:form action="/indicador/aplicarForm.action">
	    <s:hidden id="datosRequestAplicarIndicador" name="resultado" ></s:hidden>	
		<!-- Button -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="cmdAplicarIndicador"></label>
			<div class="col-md-4">
				<s:submit id="cmdAplicarIndicador" name="cmdAplicarIndicador" cssClass="btn btn-success" value="Aplicar Indicador"></s:submit>
			</div>
		</div>

	</s:form>

	<br>


</div>