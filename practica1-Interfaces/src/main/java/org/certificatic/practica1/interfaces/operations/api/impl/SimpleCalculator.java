package org.certificatic.practica1.interfaces.operations.api.impl;

import org.certificatic.practica1.interfaces.operations.api.IAdd;
import org.certificatic.practica1.interfaces.operations.api.IDivide;
import org.certificatic.practica1.interfaces.operations.api.IMultiply;
import org.certificatic.practica1.interfaces.operations.api.ISubtract;

public class SimpleCalculator extends Calculator<SimpleCalculator> implements IAdd, ISubtract, IMultiply, IDivide {

	@Override
	public SimpleCalculator subtract(double number) {
		this.setAccumulator(this.getAccumulator() - number);
		return this;
	}

	@Override
	public SimpleCalculator add(double number) {
		this.setAccumulator(this.getAccumulator() + number);
		return this;
	}

	@Override
	public SimpleCalculator divide(double number) {
		this.setAccumulator(this.getAccumulator() / number);
		return this;
	}

	@Override
	public SimpleCalculator multiply(double number) {
		this.setAccumulator(this.getAccumulator() * number);
		return this;
	}

}
