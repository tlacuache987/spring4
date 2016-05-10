package org.certificatic.spring.core.practica20.test.resources;

import javax.inject.Inject;

import org.certificatic.spring.core.practica20.resources.bean.BeanResourceLoaderAware;
import org.certificatic.spring.core.practica20.test.resources.utils.ResourcesTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/practica20/resources-application-context.xml")
public class ResourceLoaderAwareTest {

	@Inject
	private BeanResourceLoaderAware beanResourceLoaderAware;

	@Test
	public void loadTextFileResourceLoaderAwareTest() {

		log.info("loadTextFileResourceLoaderAwareTest -------------------");

		ResourcesTestUtils.loadTextFile(beanResourceLoaderAware.getResourceLoader()
				.getResource("file:c:/certificatic-resources/my-text-file.txt"));
	}

	@Test
	public void loadPropertiesResourceLoaderAwareTest() {

		log.info("loadPropertiesResourceLoaderAwareTest -------------------");

		ResourcesTestUtils.loadPropertiesFile(
				beanResourceLoaderAware.getResourceLoader().getResource("spring/practica20/my-properties.properties"));
	}

	@Test
	public void loadUrlFileResourceLoaderAwareTest() {

		log.info("loadUrlFileResourceLoaderAwareTest -------------------");

		ResourcesTestUtils.loadURLFile(beanResourceLoaderAware.getResourceLoader().getResource("http://spring.io/"));
	}

	@Test
	public void loadAndCopyImageResourceLoaderAwareTest() {

		log.info("loadAndCopyImageResourceLoaderAwareTest -------------------");

		ResourcesTestUtils.loadAndCopyImage(beanResourceLoaderAware.getResourceLoader()
				.getResource("file:src/main/resources/spring/practica20/logo.png"),
				"src/main/resources/spring/practica20/copy-resource-loader-aware/");
	}
}
