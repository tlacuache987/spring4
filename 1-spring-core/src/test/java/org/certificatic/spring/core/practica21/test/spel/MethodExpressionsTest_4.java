package org.certificatic.spring.core.practica21.test.spel;

import org.certificatic.spring.core.practica21.spel.model.Inventor;
import org.certificatic.spring.core.practica21.test.spel.model.stub.SocietyStub;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MethodExpressionsTest_4 {

	private static ExpressionParser spelParser = new SpelExpressionParser();

	private static EvaluationContext teslaContext;
	private static EvaluationContext societyContext;

	@BeforeClass
	public static void setUp() {
		Inventor tesla = SocietyStub.createTesla();
		teslaContext = new StandardEvaluationContext(tesla);

		societyContext = new StandardEvaluationContext(
				SocietyStub.createSociety("IEEE"));
	}

	@Test
	public void methodExpressionsTest() {

		log.info("methodExpressionsTest -------------------");

		String teslaFirstName = (String) spelParser.parseExpression("name.substring(0,6)").getValue(teslaContext);
		Assert.assertEquals("Nikola", teslaFirstName);
		log.info("teslaFirstName: {}", teslaFirstName);

		boolean isMemberRudolfCarlVirchow = (boolean) spelParser.parseExpression("isMember('Rudolf Carl Virchow')")
				.getValue(societyContext);
		Assert.assertFalse(isMemberRudolfCarlVirchow);
		log.info("isMember RudolfCarlVirchow: {}", isMemberRudolfCarlVirchow);

		boolean isMemberCharlesBabbage = (boolean) spelParser.parseExpression("isMember('Charles Babbage')")
				.getValue(societyContext);
		Assert.assertTrue(isMemberCharlesBabbage);
		log.info("isMember CharlesBabbage: {}", isMemberCharlesBabbage);

		// method with if then else.
		String charlesBabage = "Charles Babbage";
		societyContext.setVariable("inventor", charlesBabage);
		String isMemberCharlesBabbageString = spelParser
				.parseExpression("isMember(#inventor) ? #inventor + ' is member !' : #inventor + ' isn''t member.'")
				.getValue(societyContext, String.class);
		Assert.assertEquals(charlesBabage + " is member !", isMemberCharlesBabbageString);
		log.info("isMember CharlesBabbageString: {}", isMemberCharlesBabbageString);

	}
}
