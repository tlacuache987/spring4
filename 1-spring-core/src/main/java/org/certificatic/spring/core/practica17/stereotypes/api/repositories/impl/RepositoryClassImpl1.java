package org.certificatic.spring.core.practica17.stereotypes.api.repositories.impl;

import javax.annotation.Resource;

import org.certificatic.spring.core.practica17.stereotypes.api.IRepositoryClass;
import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository
public class RepositoryClassImpl1 implements IRepositoryClass {
	@Resource
	private String repositoryClassName;
}
