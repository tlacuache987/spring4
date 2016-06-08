package org.certificatic.spring.mvc.practica29.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.certificatic.spring.validation.practica30.parte1.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/person")
@SessionAttributes({ "personCreated", "servertime" })
class PersonController {

	private List<Person> persons = Collections.synchronizedList(new ArrayList<Person>());

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String showPersonsPage(Model model) {

		log.info("showPersonsPage ---------------->");

		model.addAttribute("persons", persons);
		model.addAttribute("personForm", new Person());

		return "person/list_and_create_person";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPerson(Model model, @ModelAttribute Person personForm) {

		log.info("createPerson ---------------->");

		log.info("processing form ...............");

		persons.add(personForm);

		model.addAttribute("personCreated", personForm);

		model.addAttribute("servertime", new Date());

		// return "person/show_person_data";
		return "redirect:/person/showdata";
	}

	@RequestMapping(value = "/showdata", method = RequestMethod.GET)
	public String showPerson(Model model, @ModelAttribute("personCreated") Person personCreated,
			@ModelAttribute("servertime") Date servertime) {

		log.info("showPerson ---------------->");

		model.addAttribute("personCreated", personCreated);
		model.addAttribute("servertime", servertime);

		return "person/show_person_data";
	}

}