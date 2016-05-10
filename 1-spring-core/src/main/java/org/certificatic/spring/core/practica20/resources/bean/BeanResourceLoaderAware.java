package org.certificatic.spring.core.practica20.resources.bean;

import javax.inject.Inject;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BeanResourceLoaderAware implements ResourceLoaderAware {

	@Inject
	private Resources resources;

	private ResourceLoader resourceLoader;
}
