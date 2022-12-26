<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="<s:url value='/js/indicador/indicador.js' />"></script>
<script src="<s:url value='/js/indicador/aplicarIndicador.js' />"></script>
<script type="text/javascript">
      var bCrearIndicador = true; 
</script>

<body onload="inicializarPaginaIndicadorForm();">
	<jsp:include page="/WEB-INF/pages/indicador/indicadorForm.jsp"></jsp:include>
</body>