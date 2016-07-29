package org.certificatic.practica1.interfaces.operations.v2.api.impl;

import org.certificatic.practica1.interfaces.operations.v2.api.IKidsCalculator;

public class KidsCalculator extends Calculator<IKidsCalculator> implements IKidsCalculator {

	@Override
	public IKidsCalculator subtract(double number) {
		this.setAccumulator(this.getAccumulator() - number);
		return this;
	}

	@Override
	public IKidsCalculator add(double number) {
		this.setAccumulator(this.getAccumulator() + number);
		return this;
	}

}
