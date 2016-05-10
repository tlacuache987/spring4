package org.certificatic.spring.soba.mvc.controller;

import java.util.Locale;

import org.certificatic.spring.soba.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes("userSession")
public class LoginController {

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String showLoginPage(Locale locale, Model model) {

		model.addAttribute("command", new User());

		return "login";
	}

	@RequestMapping(value = { "/login", "" }, method = RequestMethod.POST)
	public String login(Locale locale, Model model, @ModelAttribute User user) {

		log.info("loggear a {}", user);

		model.addAttribute("userSession", user);

		return "redirect:welcome";
	}

	@RequestMapping(value = { "/logout", "" }, method = RequestMethod.GET)
	public String logout(Locale locale, Model model, SessionStatus status) {

		status.setComplete();
		return "redirect:";
	}
}
