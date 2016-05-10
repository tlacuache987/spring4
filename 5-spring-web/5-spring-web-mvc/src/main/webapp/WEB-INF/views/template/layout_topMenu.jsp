<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- %@ taglib uri="http://www.springframework.org/security/tags" prefix="security" % -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="topmenu">
	<ul>
		<c:set var="topMenuRendered" value="false"></c:set>


		<li><a href="welcome" data-seccion="welcome">Welcome
				${userSession.username}</a></li>
		<li><a href="dashboard" data-seccion="dashboard">Dashboard</a></li>
		<li><a href="manageCustomers" data-seccion="manageCustomers">Manage
				Customers</a></li>
		<li><a href="manageUsers" data-seccion="manageUsers">Manage
				Users</a></li>


		<li><a href="logout" data-seccion="logout">Logout</a></li>

	</ul>
</div>
