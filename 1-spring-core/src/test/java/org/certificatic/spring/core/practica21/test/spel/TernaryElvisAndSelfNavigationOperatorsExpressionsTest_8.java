package org.certificatic.spring.core.practica21.test.spel;

import java.util.Random;

import org.certificatic.spring.core.practica21.spel.bean.MyBeanResolver;
import org.certificatic.spring.core.practica21.spel.configuration.ApplicationConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TernaryElvisAndSelfNavigationOperatorsExpressionsTest_8 {

	private static ExpressionParser spelParser = new SpelExpressionParser();

	private static StandardEvaluationContext springContext;

	private static ApplicationContext applicationContext;

	@BeforeClass
	public static void setUp() {
		applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		springContext = new StandardEvaluationContext();
		((StandardEvaluationContext) springContext).setBeanResolver(applicationContext.getBean(MyBeanResolver.class));
	}

	@Test
	public void ternaryOperatorExpressionsTest() {

		log.info("templatingExpressionsTest -------------------");

		springContext.setVariable("name", "Ivan García");
		springContext.setVariable("random", new Random());

		String greeting = spelParser
				.parseExpression(
						"Hi #{ #name +' '+'you''re' } #{ #random.nextInt(100)%2 == 0 ? 'awesome' : 'handsome' } !",
						new TemplateParserContext())
				.getValue(springContext,
						String.class);

		Assert.assertNotNull(greeting);
		log.info("greeting: {}", greeting);

	}

	@Test
	public void elvisOperatorExpressionsTest() {

		log.info("elvisOperatorExpressionsTest -------------------");

		springContext.setVariable("name", "Ivan García");

		String name = spelParser.parseExpression("#name?:'Without name'").getValue(springContext, String.class);
		Assert.assertNotNull(name);
		Assert.assertEquals("Ivan García", name);
		log.info("name: {}", name);

		spelParser.parseExpression("#name").setValue(springContext, null);

		name = spelParser.parseExpression("#name?:'Without name'").getValue(springContext, String.class);
		Assert.assertNotNull(name);
		Assert.assertEquals("Without name", name);
		log.info("name: {}", name);

		name = spelParser.parseExpression("#name").getValue(springContext, String.class);
		Assert.assertNull(name);
		log.info("name: {}", name);
	}

	@Test(expected = SpelEvaluationException.class)
	public void selfNavigationOperatorExpressionsTest2() {

		log.info("selfNavigationOperatorExpressionsTest2 -------------------");

		springContext.setVariable("name", "Ivan García");

		String name = spelParser.parseExpression("#name?.substring(0,4)").getValue(springContext, String.class);
		Assert.assertNotNull(name);
		Assert.assertEquals("Ivan", name);
		log.info("name: {}", name);

		spelParser.parseExpression("#name").setValue(springContext, null);

		name = spelParser.parseExpression("#name?.substring(0,4)").getValue(springContext, String.class);
		Assert.assertNull(name);
		log.info("name: {}", name);

		name = spelParser.parseExpression("#name.substring(0,4)").getValue(springContext, String.class);
		Assert.fail();
	}

}
