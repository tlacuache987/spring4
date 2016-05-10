package org.certificatic.spring.soba.mvc.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.certificatic.spring.soba.util.api.IColorWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DashboardController {

	@Autowired
	private IColorWriter colorWriter;

	@RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
	public String showDashboardPage(HttpSession session, Locale locale, Model model) {

		model.addAttribute("currentSecc", "dashboard");

		return "dashboard";
	}
}
