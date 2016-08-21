package org.certificatic.spring.core.practica20.test.resources;

import javax.inject.Inject;

import org.certificatic.spring.core.practica20.resources.bean.Resources;
import org.certificatic.spring.core.practica20.test.resources.utils.ResourcesTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ResourcesTest.location)
public class ResourcesTest {

	public static final String location = "classpath:/spring/practica20/resources-application-context.xml";

	@Inject
	private Resources resources;

	@Test
	public void loadTextFileClasspathXmlApplicationContextTest() {

		log.info("loadTextFileClasspathXmlApplicationContextTest -------------------");

		ResourcesTestUtils.loadTextFile(resources.getTxtFile());
	}

	@Test
	public void loadPropertiesFileClasspathXmlApplicationContextTest() {

		log.info("loadPropertiesFileClasspathXmlApplicationContextTest -------------------");

		ResourcesTestUtils.loadPropertiesFile(resources.getPropertiesFile());
	}

	@Test
	public void loadUrlFileClasspathXmlApplicationContextTest() {

		log.info("loadUrlFileClasspathXmlApplicationContextTest -------------------");

		ResourcesTestUtils.loadURLFile(resources.getUrlFile());
	}

	@Test
	public void loadAndCopyImageFileClasspathXmlApplicationContextTest() {

		log.info("loadAndCopyImageFileClasspathXmlApplicationContextTest -------------------");

		ResourcesTestUtils.loadAndCopyImage(resources.getImageFile(),
				"src/main/resources/spring/practica20/copy-resources/");
	}
}
