package org.certificatic.spring.core.practica15.autowired.properties.bean;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Reporter {

	@Autowired
	private @Setter(AccessLevel.NONE) String name;

	@Autowired
	private @Setter(AccessLevel.NONE) Integer age;

	private String dni;

	@Autowired
	private @Setter(AccessLevel.NONE) Booklet notebook;

	@Autowired
	private @Setter(AccessLevel.NONE) Pencil pen;

}
