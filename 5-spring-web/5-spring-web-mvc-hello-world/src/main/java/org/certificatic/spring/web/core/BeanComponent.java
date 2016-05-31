package org.certificatic.spring.web.core;

import org.certificatic.spring.web.mvc.service.WebService;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BeanComponent {

	// @Autowired
	private WebService webService;

	public String sayHello(String name) {
		return "Hello " + name + " !";
	}

}
