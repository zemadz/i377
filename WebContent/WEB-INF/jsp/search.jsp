<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">



<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Part 3</title>

<style type="text/css">
<!--
@import url("${pageContext.request.contextPath}/static/style.css");
-->
</style>
</head>




<body>


	<jsp:include page="menu.jsp" />

	<br />
	<br />
	<br />



	<form method="get" action="Search">
		<input name="searchString" id="searchStringBox" value="${param.searchString}" /> <input
			type="submit" id="filterButton" value="Filtreeri" /> <br /> <br />
		<table class="listTable" id="listTable">
			<thead>
				<tr>
					<th scope="col">Nimi</th>
					<th scope="col">Kood</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>


				<c:forEach items="${sessionScope.units}" var="each"
					varStatus="status">

					<tr>
						<td>
							<div id="row_${each.getCode()}">${each.getName()}</div>
						</td>
						<td>${each.getCode()}</td>
						<td><a href="Search?do=delete&id=${each.getId()}"
							id="delete_${each.getCode()}">Kustuta</a></td>
					</tr>
				</c:forEach>


			</tbody>
		</table>
	</form>
</body>
</html>
