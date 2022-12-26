<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<link rel="stylesheet" href="<s:url value='/css/bootstrap.min.css' />"></link>
<link rel="stylesheet"
	href="<s:url value='/css/bootstrap-theme.min.css' />"></link>
<link rel="stylesheet" href="<s:url value='/css/stickyFooter.css' />"></link>
<link rel="stylesheet" href="<s:url value='/css/styles.css' />"></link>
<title>Inversiones</title>
	<script language="javascript" src="<s:url value='/js/jquery-1.9.1.js' />"></script>
	<script language="javascript" src="<s:url value='/js/bootstrap.js' />"></script>
	<script language="javascript" src="<s:url value='/js/varios.js' />"></script>
	<script src="<s:url value='/js/home/home.js' />"></script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/WEB-INF/pages/menu/header.jsp" flush="true" /> 
		<div class="row">
			<jsp:include page="/WEB-INF/pages/menu/menu.jsp" flush="true" />
			<div id="contPrincipal" class="col-md-9 col-sm-9">
				<div class="panel panel-default ">
					<div class="panel-heading">INICIO</div>
					<div class="panel-body">
						<jsp:include page='<%=request.getParameter("sendRedirect")%>' flush="true" />
					</div>
					<jsp:include page="/WEB-INF/pages/menu/_pieEntrada.jsp" flush="true" />
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/pages/menu/footer.jsp" flush="true" />
	</div>

	
</body>
</html>