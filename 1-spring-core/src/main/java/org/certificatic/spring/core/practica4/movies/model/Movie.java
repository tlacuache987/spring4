package org.certificatic.spring.core.practica4.movies.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Movie {

	private String titulo;

	public Movie(String titulo) {
		this.titulo = titulo;
	}
}
