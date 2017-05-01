<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>EPILEN PATEINT REPOSITORY</title>
	</head>
	<body>
		<h2>Add Patient Data</h2>
		<form:form method="POST" action="/sdnext/save.html">
	   		<table>
			    <tr>
			        <td><form:label path="id">Patient ID:</form:label></td>
			        <td><form:input path="id" value="${patient.id}" readonly="true"/></td>
			    </tr>
			    <tr>
			        <td><form:label path="name">Patient Name:</form:label></td>
			        <td><form:input path="name" value="${patient.name}"/></td>
			    </tr>
			    <tr>
			        <td><form:label path="age">Patient Age:</form:label></td>
			        <td><form:input path="age" value="${patient.age}"/></td>
			    </tr>
			    <tr>
			        <td><form:label path="disease">Patient Disease:</form:label></td>
			        <td><form:input path="disease" value="${patient.disease}"/></td>
			    </tr>
			    
			    <tr>
			        <td><form:label path="address">Patient Address:</form:label></td>
                    <td><form:input path="address" value="${patient.address}"/></td>
			    </tr>
			    <tr>
			      <td colspan="2"><input type="submit" value="Submit"/></td>
		      </tr>
			</table> 
		</form:form>
		
  <c:if test="${!empty patients}">
		<h2>List Patients</h2>
	<table align="left" border="1">
		<tr>
			<th>Patient ID</th>
			<th>Patient Name</th>
			<th>Patient Age</th>
			<th>Patient Disease</th>
			<th>Patient Address</th>
			<th>Actions on Row</th>
		</tr>

		<c:forEach items="${patients}" var="patient">
			<tr>
				<td><c:out value="${patient.id}"/></td>
				<td><c:out value="${patient.name}"/></td>
				<td><c:out value="${patient.age}"/></td>
				<td><c:out value="${patient.disease}"/></td>
				<td><c:out value="${patient.address}"/></td>
				<td align="center"><a href="edit.html?id=${patient.id}">Edit</a> | <a href="delete.html?id=${patient.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
	</body>
</html>