package org.certificatic.spring.validation.practica29.test.validator;

import java.util.Locale;

import org.certificatic.spring.validation.practica29.domain.Person;
import org.certificatic.spring.validation.practica29.test.validator.utils.PersonValidatorMxTestUtils;
import org.certificatic.spring.validation.practica29.validator.config.ValidatorTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ValidatorTestConfig.class)
public class PersonValidatorMxTest {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private Validator personValidator;

	private Locale locale = new Locale("es", "MX");

	public void setUp() {
		Assert.assertNotNull(messageSource);
		Assert.assertNotNull(personValidator);
	}

	@Test
	public void validationPersonMxTest1() {

		log.info("validationPersonMxTest1 -------------------");

		Person person = new Person();
		// persona sin nombre ni edad.

		BindException errors = new BindException(person, Person.class.getName());

		ValidationUtils.invokeValidator(personValidator, person, errors);

		if (errors.hasErrors()) {

			Assert.assertEquals(2, errors.getErrorCount());

			PersonValidatorMxTestUtils.printErrors(errors, messageSource, locale);

		}

	}

	@Test
	public void validationPersonMxTest2() {

		log.info("validationPersonMxTest2 -------------------");

		Person person = new Person();
		// persona sin nombre y menor de edad.
		person.setAge(16);

		BindException errors = new BindException(person, Person.class.getName());

		ValidationUtils.invokeValidator(personValidator, person, errors);

		if (errors.hasErrors()) {

			Assert.assertEquals(2, errors.getErrorCount());

			PersonValidatorMxTestUtils.printErrors(errors, messageSource, locale);
		}

	}

	@Test
	public void validationPersonMxTest3() {

		log.info("validationPersonMxTest3 -------------------");

		Person person = new Person();
		// persona con nombre y menor de edad.
		person.setName("Ivan");
		person.setAge(16);

		BindException errors = new BindException(person, Person.class.getName());

		ValidationUtils.invokeValidator(personValidator, person, errors);

		if (errors.hasErrors()) {

			Assert.assertEquals(1, errors.getErrorCount());

			PersonValidatorMxTestUtils.printErrors(errors, messageSource, locale);
		}

	}

	@Test
	public void validationPersonMxTest4() {

		log.info("validationPersonMxTest4 -------------------");

		Person person = new Person();
		// persona con nombre y mayor de edad.
		person.setName("Ivan");
		person.setAge(20);

		BindException errors = new BindException(person, Person.class.getName());

		ValidationUtils.invokeValidator(personValidator, person, errors);

		Assert.assertFalse(errors.hasErrors());
	}

}
