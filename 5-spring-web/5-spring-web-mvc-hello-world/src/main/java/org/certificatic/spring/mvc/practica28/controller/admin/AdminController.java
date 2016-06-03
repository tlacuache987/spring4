package org.certificatic.spring.mvc.practica28.controller.admin;

import org.certificatic.spring.mvc.practica28.rootbeans.BeanComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
class AdminController {

	@Autowired(required = false)
	private BeanComponent beanComponent;

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String showIndexPage(Model model) {

		model.addAttribute("message", "Hello World (ADMIN) - Spring MVC (" + beanComponent.sayHello("Ivan") + ")");
		return "admin";
	}

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public @ResponseBody String showIndexMessage() {

		return "Hello World - Spring MVC (ADMIN) (" + beanComponent.sayHello("Ivan") + ")";
	}

}