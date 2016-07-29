package org.certificatic.practica1.interfaces.operations.v3.test;

import java.text.DecimalFormat;

import org.certificatic.practica1.interfaces.operations.v3.api.ICalculator;
import org.certificatic.practica1.interfaces.operations.v3.api.impl.SimpleCalculator;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleCalculatorTest {

	private DecimalFormat df = new DecimalFormat("#.00##");

	@Test
	public void kidsCalculatorTest() {
		log.info("operacionesTest -------------------");

		final ICalculator<?> simpleCal = new SimpleCalculator();
		final ICalculator<?> simpleCal2 = new SimpleCalculator();

		final double expectedValue = 24D;

		((SimpleCalculator) simpleCal).set(5).add(5).add(5).add(-5).subtract(4).multiply(8).divide(2);
		((SimpleCalculator) simpleCal2).set(-5).add(5).add(5).subtract(4).add(5).divide(2).multiply(8);

		log.info("simpleCal.result(): {}", df.format(simpleCal.result()));
		log.info("simpleCal2.result(): {}", df.format(simpleCal2.result()));

		Assert.assertEquals(expectedValue, simpleCal.result(), 0.0001);
		Assert.assertEquals(expectedValue, simpleCal2.result(), 0.0001);

	}
}
