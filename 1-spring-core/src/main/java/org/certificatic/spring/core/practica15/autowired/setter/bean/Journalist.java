package org.certificatic.spring.core.practica15.autowired.setter.bean;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class Journalist {
	private String name;
	private Integer age;
	private String dni;

	private NoteBook notebook;
	private Pen pen;

	@Autowired
	public void setName(String name) {
		this.name = name;
	}

	@Autowired
	public void setAge(Integer age) {
		this.age = age;
	}

	@Autowired
	public void setNotebook(NoteBook notebook) {
		this.notebook = notebook;
	}

	@Autowired
	public void setAge(Pen pen) {
		this.pen = pen;
	}

}
