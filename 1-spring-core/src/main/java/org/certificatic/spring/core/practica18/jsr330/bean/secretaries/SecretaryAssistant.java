package org.certificatic.spring.core.practica18.jsr330.bean.secretaries;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.certificatic.spring.core.practica18.jsr330.bean.Employee;
import org.certificatic.spring.core.practica18.jsr330.bean.Secretary;
import org.certificatic.spring.core.practica18.jsr330.qualifiers.SecretaryAssistantQualifier;
import org.springframework.beans.factory.annotation.Qualifier;

@Named
@Singleton
@SecretaryAssistantQualifier
public class SecretaryAssistant extends Secretary {

	@Override
	@Inject
	@Qualifier("secretaryAssistantEmployee")
	public void setEmployee(Employee employee) {
		super.setEmployee(employee);
	}

}
