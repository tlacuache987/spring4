package org.certificatic.spring.core.practica17.stereotypes.api.restcontrollers.impl;

import javax.annotation.Resource;

import org.certificatic.spring.core.practica17.stereotypes.api.IRestControllerClass;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@Data
@RestController
public class RestControllerClassImpl1 implements IRestControllerClass {
	@Resource
	private String restControllerClassName;
}
