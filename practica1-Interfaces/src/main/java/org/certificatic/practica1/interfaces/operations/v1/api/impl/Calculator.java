package org.certificatic.practica1.interfaces.operations.v1.api.impl;

import org.certificatic.practica1.interfaces.operations.v1.api.ICalculator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class Calculator implements ICalculator {
	private @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED) double accumulator;

	@Override
	public ICalculator set(double number) {
		this.setAccumulator(number);
		return  this;
	}

	@Override
	public double result() {
		return accumulator;
	}

}
