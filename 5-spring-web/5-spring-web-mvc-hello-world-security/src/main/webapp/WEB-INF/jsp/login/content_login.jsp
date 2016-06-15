<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<security:authorize access="isAnonymous()">
	<div id="login-error" class="error">${errorMessage}</div>

	<form id="form" action="j_spring_security_check" method="post">
		<fieldset id="personal">
			<legend>Login</legend>
			<label for="j_username">Username:</label> <input id="j_username"
				name="j_username" type="text" /> <br /> <label for="j_password">Password:</label>
			<input id="j_password" name="j_password" type="password" /> <br />
			<br />
		</fieldset>

		<div align="center">
			<input class="button" type="reset" value="Clear" /> <input
				class="button" type="submit" value="Login" />
		</div>
	</form>
</security:authorize>

<security:authorize access="isAuthenticated()">
	<h2>
		¡ User '<security:authentication property="principal.username" />' you're already authenticated !
	</h2>

	<security:authentication property="principal.authorities" />
</security:authorize>