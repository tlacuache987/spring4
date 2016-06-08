package org.certificatic.spring.mvc.practica30.parte2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.certificatic.spring.mvc.practica30.parte2.forms.ContactForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/contact")
@SessionAttributes({ "contactForm", "confirmationId" })
public class ContactFormController {

	@Resource
	private Validator contactFormValidator;

	@InitBinder("contactForm")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(contactFormValidator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(Model model) {
		ContactForm contactForm = new ContactForm();
		contactForm.setHiddenMessage("contact-form-" + randomNumber(3));

		log.info("initform: {}", contactForm);

		model.addAttribute("contactForm", contactForm);

		return "form/contact_form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(Model model, @Valid @ModelAttribute("contactForm") ContactForm form,
			BindingResult result) {

		log.info("submited form: {}", form);

		if (result.hasErrors()) {
			return "form/contact_form";
		}

		String confirmationId = randomNumber(10);
		model.addAttribute("confirmationId", confirmationId);
		model.addAttribute("contactForm", form);

		log.info("confirmation id: {}", confirmationId);

		// return "form/show_data";
		return "redirect:/contact/success";
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String successProcess(@ModelAttribute("contactForm") ContactForm form) {
		log.info("success Process: {}", form);

		return "form/show_data";
	}

	@ModelAttribute("availableCourses")
	public List<String> availableCourses() {
		List<String> courses = new ArrayList<String>();
		courses.add("Java");
		courses.add("Python");
		courses.add("NodeJS");
		courses.add("AngularJS");
		courses.add("PHP");
		return courses;
	}

	@ModelAttribute("availableTutors")
	public List<String> availableTutors() {
		List<String> tutors = new ArrayList<String>();
		tutors.add("Mrs Smith");
		tutors.add("Mrs Croft");
		tutors.add("Mr Wayne");
		tutors.add("Mr Kent");
		return tutors;
	}

	@ModelAttribute("genders")
	public List<String> genders() {
		List<String> genders = new ArrayList<String>();
		genders.add("Female");
		genders.add("Male");
		return genders;
	}

	private static String randomNumber(int length) {
		Random random = new Random();
		char[] digits = new char[length];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < length; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return new String(digits);
	}
}
