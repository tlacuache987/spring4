package org.certificatic.spring.core.practica10.beanpostprocessors.bean.proxy;

import org.certificatic.spring.core.practica10.beanpostprocessors.bean.api.IWorker;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = { "worker" })
public class WorkerProxy implements IWorker {
	private @Getter @Setter String name;
	private @Getter @Setter int age;

	private @Setter IWorker worker;

	public WorkerProxy() {
		System.out.println("[WorkerProxy] Constructing worker proxy.");
	}

	public void init() {
		System.out.println("[WorkerProxy] Initializing worker proxy.");
	}

	public void showInfo() {
		String msg = String.format("> I'm Worker: %s. [%s]", this.name, this.hashCode());

		System.out.println("[showInfo] " + msg);
	}

	public String getProxiedWorkerData() {
		return this.worker.getName() + " " + this.worker.getAge();
	}

	@Override
	public void destroy() {
		System.out.println("[WorkerProxy] Destroying worker proxy.");
	}

}
