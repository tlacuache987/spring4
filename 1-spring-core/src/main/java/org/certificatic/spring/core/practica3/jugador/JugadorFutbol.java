package org.certificatic.spring.core.practica3.jugador;

import lombok.Data;

import org.certificatic.spring.core.practica3.jugador.api.IJugador;
import org.certificatic.spring.core.practica3.liga.Partido;
import org.certificatic.spring.core.practica3.liga.Torneo;

@Data
public class JugadorFutbol implements IJugador{
	private String nombre;
	private Torneo torneo;
	private Partido partido;

	public void saludar() {
		System.out.println("hola soy un jugador de Futbol");
		System.out.println("me llamo: " + this.nombre + " ["
				+ Integer.toHexString(super.hashCode()) + "]");

	}
}
