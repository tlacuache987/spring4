package org.certificatic.spring.core.practica17.stereotypes.api.components.impl;

import javax.annotation.Resource;

import org.certificatic.spring.core.practica17.stereotypes.api.IComponentClass;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ComponentClassImpl1 implements IComponentClass {
	@Resource
	private String componentClassName;
}
