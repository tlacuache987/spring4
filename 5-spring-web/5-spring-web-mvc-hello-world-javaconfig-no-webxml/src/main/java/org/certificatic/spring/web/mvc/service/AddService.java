package org.certificatic.spring.web.mvc.service;

import org.certificatic.spring.rootbeans.BeanComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AddService {

	@Autowired
	private BeanComponent beanComponent;

	public Double zum(Double... numbers) {

		Double z = 0D;

		if (numbers != null)
			for (Double n : numbers)
				z += n;

		return z;

	}
}
