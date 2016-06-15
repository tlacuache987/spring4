package org.certificatic.spring.mvcsecurity.practica32.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/adminSection")
public class AdminController {

	@RequestMapping(method = RequestMethod.GET)
	public String showAdminPage(Model model) {

		log.info("show admin page ------------------");

		model.addAttribute("currentSecc", "adminSection");

		return "secure/view_admin_section";
	}

}
