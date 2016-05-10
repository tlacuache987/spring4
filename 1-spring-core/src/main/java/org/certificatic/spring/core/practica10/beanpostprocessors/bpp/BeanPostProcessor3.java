package org.certificatic.spring.core.practica10.beanpostprocessors.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class BeanPostProcessor3 implements BeanPostProcessor, Ordered {

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {

		System.out.println("[Bean Post Process Before Initialization " + this.getOrder() + "]");

		System.out.println("[BPP] Bean class: " + bean.getClass().getSimpleName());

		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {

		System.out.println("[Bean Post Process After Initialization " + this.getOrder() + "]");

		System.out.println("[BPP] Bean class: " + bean.getClass().getSimpleName());

		return bean;
	}

	@Override
	public int getOrder() {
		return 3;
	}
}
