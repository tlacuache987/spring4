package org.certificatic.spring.core.practica21.test.spel;

import java.util.List;

import org.apache.commons.math3.complex.Complex;
import org.certificatic.spring.core.practica19.javaconfig.bean.api.QuadraticEquationResult;
import org.certificatic.spring.core.practica21.spel.bean.Chicharronera;
import org.certificatic.spring.core.practica21.spel.bean.GuessNumber;
import org.certificatic.spring.core.practica21.spel.bean.Magician;
import org.certificatic.spring.core.practica21.spel.bean.MyBeanResolver;
import org.certificatic.spring.core.practica21.spel.configuration.ApplicationConfig;
import org.certificatic.spring.core.practica21.spel.model.Inventor;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VariablesFunctionsBeanReferencesExpressionsTest_7 {

	private static ExpressionParser spelParser = new SpelExpressionParser();

	private static StandardEvaluationContext springContext;

	private static ApplicationContext applicationContext;

	@BeforeClass
	public static void setUp() {
		applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		springContext = new StandardEvaluationContext();
		springContext.setBeanResolver(applicationContext.getBean(MyBeanResolver.class));
	}

	@Test
	public void variablesExpressionsTest() {

		log.info("variablesExpressionsTest -------------------");

		Magician magician = applicationContext.getBean(Magician.class);

		springContext.setVariable("magicNumber", magician.getInitialNumber());

		Integer randomNumber = spelParser.parseExpression("@gessNumberBean.randomNumber").getValue(springContext,
				Integer.class);

		springContext.setVariable("randomNumber", randomNumber);

		Boolean isCorrectNumber = spelParser.parseExpression("#randomNumber == #magicNumber").getValue(springContext,
				Boolean.class);
		Assert.assertNotNull(isCorrectNumber);
		log.info("isCorrectNumber: {}", isCorrectNumber);

		log.info("magician.initialNumber: {}", magician.getInitialNumber());
		log.info("gessNumberBean.randomNumber: {}", randomNumber);

	}

	@Test
	@SneakyThrows
	public void functionsExpressionsTest() {

		log.info("functionsExpressionsTest -------------------");

		springContext.setVariable("a", 5);
		springContext.setVariable("b", 4);
		springContext.setVariable("c", -10);

		springContext.registerFunction("chicharronera", Chicharronera.class.getDeclaredMethod("calculate",
				new Class[] { double.class, double.class, double.class }));

		QuadraticEquationResult expectedResult = QuadraticEquationResult.builder().x1(new Complex(1.0697, 0.0))
				.x2(new Complex(-1.8697, 0.0)).build();

		QuadraticEquationResult quadraticEquationResult = spelParser.parseExpression("#chicharronera(#a, #b, #c)")
				.getValue(springContext, QuadraticEquationResult.class);

		Assert.assertNotNull(quadraticEquationResult);
		Assert.assertEquals(expectedResult, quadraticEquationResult);
		log.info("quadraticEquationResult: {}", quadraticEquationResult);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void beanReferencesExpressionsTest() {

		log.info("beanReferencesExpressionsTest -------------------");

		GuessNumber guessNumber = spelParser.parseExpression("@gessNumberBean").getValue(springContext,
				GuessNumber.class);
		Assert.assertNotNull(guessNumber);
		log.info("guessNumber: {}", guessNumber);

		Inventor tesla = spelParser.parseExpression("@teslaBean").getValue(springContext, Inventor.class);
		Assert.assertNotNull(tesla);
		log.info("tesla: {}", tesla);

		Integer inventionsLength = spelParser.parseExpression("@teslaBean.inventions.length").getValue(springContext,
				int.class);
		Assert.assertNotNull(inventionsLength);
		log.info("inventionsLength: {}", inventionsLength);

		List<String> inventions = spelParser.parseExpression("@teslaBean.inventions").getValue(springContext,
				List.class);
		Assert.assertNotNull(inventions);
		log.info("inventions: {}", inventions);

		Assert.assertEquals(3, inventions.size());
	}

}
