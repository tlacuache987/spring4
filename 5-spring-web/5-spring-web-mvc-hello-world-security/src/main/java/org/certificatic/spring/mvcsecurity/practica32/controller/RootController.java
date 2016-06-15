package org.certificatic.spring.mvcsecurity.practica32.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/rootSection")
public class RootController {

	@RequestMapping(method = RequestMethod.GET)
	public String showRootPage(Model model) {

		log.info("show root page ------------------");

		model.addAttribute("currentSecc", "rootSection");

		return "secure/view_root_section";
	}

}
