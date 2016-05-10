package org.certificatic.spring.core.practica15.autowired.requiredFalse.qualifier.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Airplane {

	@Autowired(required = false)
	private @Setter(AccessLevel.NONE) String airplaneCode;

	@Autowired
	@Qualifier("airline1")
	private @Setter(AccessLevel.NONE) Airline airline;

}
