package org.certificatic.spring.soba.mvc.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.certificatic.spring.soba.util.Color;
import org.certificatic.spring.soba.util.api.IColorWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WelcomeController {

	@Autowired
	private IColorWriter colorWriter;

	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String showWelcomePage(HttpSession session, Locale locale, Model model) {

		model.addAttribute("currentSecc", "welcome");

		log.info("{}", colorWriter.getColoredMessage(Color.GREEN, "" + session.getAttribute("userSession")));

		return "welcome";
	}
}
