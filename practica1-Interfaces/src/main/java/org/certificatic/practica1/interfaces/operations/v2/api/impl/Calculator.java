package org.certificatic.practica1.interfaces.operations.v2.api.impl;

import org.certificatic.practica1.interfaces.operations.v2.api.ICalculator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class Calculator<K> implements ICalculator<K> {
	private @Getter(AccessLevel.PROTECTED) @Setter(AccessLevel.PROTECTED) double accumulator;

	@Override
	public K set(double number) {
		this.setAccumulator(number);
		
		@SuppressWarnings("unchecked")
		K k = (K) this;
		return k;
	}

	@Override
	public double result() {
		return accumulator;
	}

}
