package org.certificatic.spring.core.practica5.scopes.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Persona {

	private String nombre = "Ivan";

	public Persona() {
		System.out.println("Construyendo Persona()");
	}
}
