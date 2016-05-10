package org.certificatic.practica1.interfaces.operations.api.impl;

import org.certificatic.practica1.interfaces.operations.api.ICalculator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class Calculator<K> implements ICalculator<K> {
	private @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED) double accumulator;

	@SuppressWarnings("unchecked")
	@Override
	public K set(double number) {
		this.setAccumulator(number);
		return (K) this;
	}

	@Override
	public double result() {
		return accumulator;
	}

}
