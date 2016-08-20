package org.certificatic.spring.core.practica10.beanpostprocessors.bpp;

import org.certificatic.spring.core.practica10.beanpostprocessors.bean.api.IWorker;
import org.certificatic.spring.core.practica10.beanpostprocessors.bean.proxy.WorkerProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class BeanPostProcessor4 implements BeanPostProcessor, Ordered {

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("[Bean Post Process Before Initialization " + this.getOrder() + "]");

		System.out.println("[BPP] Bean class: " + bean.getClass().getSimpleName());

		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("[Bean Post Process After Initialization " + this.getOrder() + "]");

		if (bean instanceof IWorker) {
			IWorker w = (IWorker) bean;

			WorkerProxy workerProxy = new WorkerProxy();

			workerProxy.setAge(30);
			workerProxy.setName("Chuck Norris");
			workerProxy.setWorker(w);

			System.out.println("[BPP] Worker name: " + w.getName());

			System.out.println("[BPP] Changed Worker name: " + w.getName());

			bean = workerProxy;
		}

		return bean;
	}

	@Override
	public int getOrder() {
		return 4;
	}
}
