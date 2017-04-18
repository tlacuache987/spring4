package org.certificatic.spring.core.practica3.test.jugador;

import org.certificatic.spring.core.practica3.jugador.JugadorFutbol;
import org.certificatic.spring.core.practica3.liga.Evento;
import org.certificatic.spring.core.practica3.liga.Partido;
import org.certificatic.spring.core.practica3.liga.Torneo;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EstadioNoSpringTest {

	@Test
	public void estadioNoSpringTest() {
		log.info("estadioNoSpringTest -------------------");

		Evento evento = new Evento();
		evento.setNombre("Mundial Rusia 2018");

		Assert.assertNotNull(evento);

		Torneo torneo = new Torneo("4tos de Final", evento);

		Assert.assertNotNull(torneo);

		Partido partido = new Partido();
		partido.setNombre("Mexico vs Australia");

		Assert.assertNotNull(partido);

		JugadorFutbol jugador = new JugadorFutbol();

		Assert.assertNotNull(jugador);

		jugador.setNombre("Chicharito");
		jugador.setTorneo(torneo);
		jugador.setPartido(partido);

		Assert.assertNotNull(jugador);
		Assert.assertNotNull(jugador.getPartido());
		Assert.assertNotNull(jugador.getTorneo());
		Assert.assertNotNull(jugador.getTorneo().getEvento());

		jugador.saludar();
		log.info("{}", jugador.getPartido());
		log.info("{}", jugador.getTorneo());
		log.info("{}", jugador.getTorneo().getEvento());
	}

}
