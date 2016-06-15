package org.certificatic.spring.mvcsecurity.practica32.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/userSection")
public class UserController {

	@RequestMapping(method = RequestMethod.GET)
	public String showUserPage(Model model) {

		log.info("show user page ------------------");

		model.addAttribute("currentSecc", "userSection");

		return "secure/view_user_section";
	}

}
