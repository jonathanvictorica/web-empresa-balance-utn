<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
       var imagenTomas ='<s:url value="/img/users/tomas.jpg" />';
       var imagenJonathan = '<s:url value="/img/users/jonathan.jpg" />';
</script>
<div id="header-usuario"  >
	<img  onClick="cargarFoto('<%=request.getSession().getAttribute("nombre")%>');"  id="header-usuario-imagen" src="<s:url value='/img/perfilgenerico.jpg' />" class="img-responsive col-md-5 col-xs-4" alt="usuario">
	<div id="header-usuario-datos" class="col-md-7 col-xs-8" id="datosUsuario">
		<p id="header-usuario-datos-apellido" class="row">
			<Strong><%=request.getSession().getAttribute("apellido")%></Strong>
		</p>
		<p id="header-usuario-datos-nombre" class="row"><%=request.getSession().getAttribute("nombre")%>
		</p>
	</div>
</div>