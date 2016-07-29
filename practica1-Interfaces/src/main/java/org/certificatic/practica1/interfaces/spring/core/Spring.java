package org.certificatic.practica1.interfaces.spring.core;

import org.certificatic.practica1.interfaces.livingbeing.LivingBeingType;
import org.certificatic.practica1.interfaces.livingbeing.api.ILivingBeing;
import org.certificatic.practica1.interfaces.livingbeing.api.impl.Aardvark;
import org.certificatic.practica1.interfaces.livingbeing.api.impl.VenusFlyTrap;

public class Spring {

	public static ILivingBeing getBean(LivingBeingType type) {

		switch (type) {
		case VENUS_FLY_TRAP:
			return new VenusFlyTrap("venus fly trap");

		case AARDVARK:
			return new Aardvark("aardvark");

		default:
			return null;
		}
	}

}
