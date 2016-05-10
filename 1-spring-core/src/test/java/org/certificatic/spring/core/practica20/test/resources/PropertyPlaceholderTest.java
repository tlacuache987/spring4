package org.certificatic.spring.core.practica20.test.resources;

import javax.inject.Inject;

import org.certificatic.spring.core.practica20.resources.bean.FavouriteRockBands;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/practica20/resources-application-context.xml")
public class PropertyPlaceholderTest {

	@Inject
	private FavouriteRockBands rockbands;

	@Value("${service.name}")
	private String serviceName;

	@Value("${service.description}")
	private String serviceDescription;

	@Value("${datasource.name}")
	private String datasourceName;

	@Value("${datasource.description}")
	private String datasourceDescription;

	@Value("${app.name}")
	private String appName;

	@Value("${app.description}")
	private String appDescription;

	@Test
	public void favouriteRockBandsTest() {

		log.info("favouriteRockBandsTest -------------------");

		Assert.assertNotNull(rockbands);

		Assert.assertEquals("Guns n' Roses", rockbands.getFirstRockBand());
		Assert.assertEquals("AC/DC", rockbands.getSecondRockBand());

		log.info("rockbands: {}", rockbands);
	}

	@Test
	public void propertyPlaceholderTest() {

		log.info("propertyPlaceholderTest -------------------");

		Assert.assertEquals("MyApp", appName);
		Assert.assertEquals("App to manage users and admins", appDescription);

		Assert.assertEquals("MyService", serviceName);
		Assert.assertEquals("Service bean to provide some data", serviceDescription);

		Assert.assertEquals("MyDatasource", datasourceName);
		Assert.assertEquals("Datasource to manage data to some database", datasourceDescription);

		log.info("appName: {}", appName);
		log.info("appDescription: {}", appDescription);
		log.info("serviceName: {}", serviceName);
		log.info("serviceDescription: {}", serviceDescription);
		log.info("datasourceName: {}", datasourceName);
		log.info("datasourceDescription: {}", datasourceDescription);
	}

}
