package org.certificatic.spring.test.validator;

import java.util.Locale;

import org.certificatic.spring.validation.practica29.domain.Person;
import org.certificatic.spring.validation.practica29.validator.config.ValidatorTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ValidatorTestConfig.class)
public class PersonValidatorTest {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private Validator personValidator;

	private Locale mxLocale = new Locale("es", "MX");
	private Locale usLocale = new Locale("en", "US");

	public void setUp() {
		Assert.assertNotNull(messageSource);
		Assert.assertNotNull(personValidator);
	}

	@Test
	public void testPerson() {

		Person person = new Person();

		// person.setName("Ivan");
		person.setAge(16);

		BindException errors = new BindException(person, Person.class.getName());

		ValidationUtils.invokeValidator(personValidator, person, errors);

		if (errors.hasErrors()) {
			log.info("Errors {}", errors.getErrorCount());
			for (ObjectError error : errors.getAllErrors()) {
				FieldError fieldError = (FieldError) error;
				log.info("{}: {}", fieldError.getField(), messageSource.getMessage(error, mxLocale));
			}
		}

	}

}
