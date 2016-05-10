package org.certificatic.spring.core.practica16.jsr250.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import lombok.Data;

@Data
public class Student {

	@Resource
	private String name;

	@Resource
	private String enrollment;

	@Resource
	private Subject subject;

	@Resource
	private Subject mathematics;

	@PostConstruct
	public void postConstructCallback() {
		System.out.println("Initializing Student Bean");
	}

	@PreDestroy
	public void preDestroyCallback() {
		System.out.println("Destroying Student Bean");
	}

}
