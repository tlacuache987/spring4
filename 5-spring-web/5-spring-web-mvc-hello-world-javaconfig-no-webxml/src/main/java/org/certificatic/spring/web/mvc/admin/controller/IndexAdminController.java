package org.certificatic.spring.web.mvc.admin.controller;

import org.certificatic.spring.web.core.BeanComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
class IndexAdminController {

	@Autowired
	private BeanComponent beanComponent;

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String showIndexPage(Model model) {

		model.addAttribute("message", "Hello World (ADMIN) - Spring MVC (no web.xml, spring JavaConfig approach) ("
				+ beanComponent.sayHello("Ivan") + ")");
		return "index";
	}

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public @ResponseBody String showIndexMessage() {

		return "Hello World - Spring MVC (ADMIN) (no web.xml, spring JavaConfig approach) (" + beanComponent.sayHello(
				"Ivan") + ")";
	}

}