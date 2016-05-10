package org.certificatic.spring.core.practica10.beanpostprocessors.bpp;

import org.certificatic.spring.core.practica10.beanpostprocessors.bean.Worker;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class BeanPostProcessor2 implements BeanPostProcessor, Ordered {

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {

		System.out.println("[Bean Post Process Before Initialization " + this.getOrder() + "]");

		if (bean instanceof Worker) {
			Worker t = (Worker) bean;

			System.out.println("[BPP] Worker age: " + t.getAge());

			t.setAge(-15);

			System.out.println("[BPP] Changed Worker age: " + t.getAge());
		}

		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {

		System.out.println("[Bean Post Process After Initialization " + this.getOrder() + "]");

		if (bean instanceof Worker) {
			Worker t = (Worker) bean;

			System.out.println("[BPP] Worker age: " + t.getAge());

			t.setAge(5);

			System.out.println("[BPP] Changed Worker age: " + t.getAge());
		}

		return bean;
	}

	@Override
	public int getOrder() {
		return 2;
	}
}
