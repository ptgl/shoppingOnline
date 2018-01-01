<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- 	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <jsp:include page="../lib.jsp" /> --%>
<title>Pet Stores</title>
</head>
<body>
	<%@ include file="../lib.jsp"%>
	<table>
		<thead>
			<th>Name</th>
			<th>Price</th>
			<th>Amount</th>
			<th></th>
		</thead>
		<tbody>
			<c:forEach var="item" items="${petList}">
				<tr>
					<td><c:out value="${item}" /></td>
					<td>100k</td>
					<td><input type="text"></td>
					<td><button>Buy</button></td>
				</tr>

			</c:forEach>

		</tbody>
	</table>

<a class="btn btn-default" href="addProduct" role="button">Add product</a>
	

</body>
</html>