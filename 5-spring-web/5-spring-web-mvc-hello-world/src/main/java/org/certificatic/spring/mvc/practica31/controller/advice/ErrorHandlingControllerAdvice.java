package org.certificatic.spring.mvc.practica31.controller.advice;

import org.certificatic.spring.mvc.practica31.controller.PersonsController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = { PersonsController.class })
public class ErrorHandlingControllerAdvice {

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public RestResponseError onException(RuntimeException re) {
		return new RestResponseError(HttpStatus.INTERNAL_SERVER_ERROR, re.getMessage(),
				re.getClass().getSimpleName());
	}

}
