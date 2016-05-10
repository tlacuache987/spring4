package org.certificatic.spring.soba.mvc.controller.aspect;

import org.certificatic.spring.soba.aspect.web.security.exception.NoUserSessionFound;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(NoUserSessionFound.class)
	public String exception(NoUserSessionFound e) {

		return "redirect:";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleError404(NoHandlerFoundException e, Model model) {
		model.addAttribute("exception", e);
		return "error404";
	}
}
