package org.certificatic.spring.mvcsecurity.practica32.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@RequestMapping(value = { "", "/", "/login" }, method = RequestMethod.GET)
	public String showLoginPage(Model model, @RequestParam(required = false, value = "error") boolean error) {

		log.info("show login page ------------------");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.info("auth: {}", auth);

		if (error) {
			log.info("login has error ------------------");

			model.addAttribute("errorMessage", "Wrong user or password");
		}

		model.addAttribute("currentSecc", "login");

		return "login/view_login";
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public String showAccessDeniedPage() {

		log.info("show access-denied page ------------------");

		return "login/view_access_denied";
	}

}
