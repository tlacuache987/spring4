package org.certificatic.spring.core.practica4.movies.service;

import org.certificatic.spring.core.practica4.movies.api.IMovieFinder;
import org.certificatic.spring.core.practica4.movies.model.Movie;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MovieListener {

	private IMovieFinder movieFinder;

	public void setMovieFinder(IMovieFinder movieFinder) {
		this.movieFinder = movieFinder;
	}

	public Movie buscarPelicula(String pelicula) {
		//this.movieFinder = new MovieFinderImpl();

		Movie movie = this.movieFinder.find(pelicula);

		if (movie != null)
			System.out.println("pelicula encontrada: " + movie);
		else
			System.out.println("pelicula no encontrada");

		return movie;
	}

}
