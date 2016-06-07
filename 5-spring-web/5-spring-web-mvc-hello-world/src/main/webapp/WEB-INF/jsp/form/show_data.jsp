<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact Form</title>
</head>
<body>
	<b>${formData.name}</b>,
	<br />
	<br />
	Your request has been processed,
	<br />
	<br />
	At
	<b>${formData.email}</b> we will send your voucher enrollment for the courses:
	<br />
	<c:forEach var="course" items="${formData.courses}">
		<b><c:out value="${course}" /></b>
		<br />
	</c:forEach>
	<br />
	${formData.tutor} will be your tutor.
	<br />
	<br />
	Request id: ${formData.hiddenMessage}
	<br />
	<br />
	Confirmation id: ${confirmationId}
</body>
</html>