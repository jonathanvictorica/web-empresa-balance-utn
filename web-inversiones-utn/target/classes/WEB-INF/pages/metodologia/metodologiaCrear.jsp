<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script src="<s:url value='/js/metodologias/metodologia.js' />"></script>
<script type="text/javascript">
      var bCrearMetodologia = true; 
</script>

<body onload="inicializarPaginaMetodologiaForm();">
	<jsp:include page="/WEB-INF/pages/metodologia/metodologiaForm.jsp"></jsp:include>
</body>