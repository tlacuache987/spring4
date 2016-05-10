package org.certificatic.spring.aop.practica24.bank.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutDefinition {

	@Pointcut("within(org.certificatic.spring.aop.practica24.bank.web..*)")
	public void webLayer() {
	}

	@Pointcut("within(org.certificatic.spring.aop.practica24.bank.service..*)")
	public void serviceLayer() {
	}

	@Pointcut("within(org.certificatic.spring.aop.practica24.bank.dao..*)")
	public void dataAccessLayer() {
	}
}
