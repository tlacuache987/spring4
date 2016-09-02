package org.certificatic.spring.core.practica20.resources.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component("favouriteRockBand")
public class FavouriteRockBands {

	@Value("${favourite.rockband.first}")
	private String firstRockBand;

	@Value("${favourite.rockband.second}")
	private String secondRockBand;

	@Value("My favourites rockbands are: ${favourite.rockband.first} and ${favourite.rockband.second}")
	private String toString;

	public String toString() {
		return toString;
	}
}
