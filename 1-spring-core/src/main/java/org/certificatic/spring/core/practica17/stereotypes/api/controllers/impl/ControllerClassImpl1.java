package org.certificatic.spring.core.practica17.stereotypes.api.controllers.impl;

import javax.annotation.Resource;

import org.certificatic.spring.core.practica17.stereotypes.api.IControllerClass;
import org.springframework.stereotype.Controller;

import lombok.Data;

@Data
@Controller
public class ControllerClassImpl1 implements IControllerClass {
	@Resource
	private String controllerClassName;
}
