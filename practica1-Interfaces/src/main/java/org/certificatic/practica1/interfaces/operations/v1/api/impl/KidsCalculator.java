package org.certificatic.practica1.interfaces.operations.v1.api.impl;

import org.certificatic.practica1.interfaces.operations.v1.api.IAdd;
import org.certificatic.practica1.interfaces.operations.v1.api.ISubtract;

public class KidsCalculator extends Calculator implements IAdd, ISubtract {

	@Override
	public KidsCalculator subtract(double number) {
		this.setAccumulator(this.getAccumulator() - number);
		return this;
	}

	@Override
	public KidsCalculator add(double number) {
		this.setAccumulator(this.getAccumulator() + number);
		return this;
	}

}
