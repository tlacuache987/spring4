package org.certificatic.spring.core.practica10.beanpostprocessors.bean.api;

public interface IWorker {
	String getName();

	void setName(String name);

	int getAge();

	void setAge(int age);

	void init();

	void showInfo();

	void destroy();

}
