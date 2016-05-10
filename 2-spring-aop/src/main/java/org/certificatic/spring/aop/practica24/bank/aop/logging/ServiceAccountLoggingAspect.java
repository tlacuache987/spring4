package org.certificatic.spring.aop.practica24.bank.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.certificatic.spring.aop.practica24.bank.app.model.Account;
import org.certificatic.spring.aop.util.Color;
import org.certificatic.spring.aop.util.bean.api.IColorWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component("serviceAccountLoggingAspect")
@Slf4j
public class ServiceAccountLoggingAspect implements Ordered {

	private @Getter int order = 2;

	@Autowired
	private IColorWriter colorWriter;

	@Before(value = "org.certificatic.spring.aop.practica24.bank.aop.PointcutDefinition.serviceLayer() && "
			+ "args(account, ..)")
	public void beforeServiceAccountMethodExecutionAccount(Account account) {

		log.info("{}",
				colorWriter.getColoredMessage(Color.MAGENTA,
						String.format("Logging Service Account access. Account: %s", account.getAccountNumber())));
	}

	@Before(value = "org.certificatic.spring.aop.practica24.bank.aop.PointcutDefinition.serviceLayer() && "
			+ "args(customerId,..)")
	public void beforeServiceAccountMethodExecutionLong(JoinPoint jp, Long customerId) {

		log.info("{}",
				colorWriter.getColoredMessage(Color.MAGENTA,
						String.format("Logging Service Account access. Customer Id: %s", customerId)));

	}
}
