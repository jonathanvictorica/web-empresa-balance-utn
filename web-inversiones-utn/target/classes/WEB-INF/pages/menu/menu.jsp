<%@page import="org.apache.commons.collections.IterableMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>


<%@taglib uri="/struts-tags" prefix="s"%>


<div id="menuPrincipal" class="col-md-3 col-sm-3">
	<%
	  
	
	
		List<List<String>> menu = (List<List<String>>) request.getSession().getAttribute("menu");

		for (int i = 0; i < menu.size(); i++) {
	%>
	<div class="panel panel-default" id="<%=menu.get(i).get(0).trim().replace(" ", "")%>"   >
		<div class="panel-heading"><%=menu.get(i).get(0)%></div>
		<div class="panel-body">
			<ul class="col-md-12 nav nav-pills nav-stacked">
				<%
					for (int j = 1; j < menu.get(i).size(); j++) {
				%>
				<li role="presentation"><a href="#" onclick="<%=menu.get(i).get(j).trim().replace(" ", "")%>();"><%=menu.get(i).get(j)%></a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
	<%
		}
	%>
</div>