package org.certificatic.spring.soba.mvc.aspect;

import org.certificatic.spring.soba.aspect.web.security.exception.NoUserSessionFound;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(NoUserSessionFound.class)
	public String exception(NoUserSessionFound e) {

		return "redirect:/";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleError404(NoHandlerFoundException e, Model model) {
		model.addAttribute("exception", e);
		return "error404";
	}

	@ExceptionHandler(HttpSessionRequiredException.class)
	public String handleRequiredSession(HttpSessionRequiredException e, Model model) {
		model.addAttribute("exception", e);
		return "redirect:/";
	}
}