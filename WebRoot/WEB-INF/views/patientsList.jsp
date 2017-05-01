<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>EPILEN PATEINT REPOSITORY</title>
</head>
<body>
<h1>List Patients</h1>
<h3><a href="add.html">Add More Patients</a></h3>

<c:if test="${!empty patients}">
	<table align="left" border="1">
		<tr>
			<th>Patient ID</th>
			<th>Patient Name</th>
			<th>Patient Age</th>
			<th>Patient Disease</th>
			<th>Patient Address</th>
		</tr>

		<c:forEach items="${patients}" var="patient">
			<tr>
				<td><c:out value="${patient.id}"/></td>
				<td><c:out value="${patient.name}"/></td>
				<td><c:out value="${patient.age}"/></td>
				<td><c:out value="${patient.disease}"/></td>
				<td><c:out value="${patient.address}"/></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>