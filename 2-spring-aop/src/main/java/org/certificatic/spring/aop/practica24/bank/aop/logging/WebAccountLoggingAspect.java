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
@Component("webAccountLoggingAspect")
@Slf4j
public class WebAccountLoggingAspect implements Ordered {

	private @Getter int order = 1;

	@Autowired
	private IColorWriter colorWriter;

	@Before(value = "org.certificatic.spring.aop.practica24.bank.aop.PointcutDefinition.webLayer() && "
			+ "args(cuenta,..)", argNames = "cuenta")
	public void beforeAccountMethodExecutionAccount(JoinPoint jp, Account acc) {

		log.info("{}",
				colorWriter.getColoredMessage(Color.BLUE,
						String.format("Logging Web View Account access. Account: %s", acc.getAccountNumber())));

	}

	@Before(value = "org.certificatic.spring.aop.practica24.bank.aop.PointcutDefinition.webLayer() && "
			+ "args(id,..)", argNames = "id")
	public void beforeAccountMethodExecutionLong(JoinPoint jp, Long numberId) {

		log.info("{}",
				colorWriter.getColoredMessage(Color.BLUE,
						String.format("Logging Web View Account access. Customer Id: %s", numberId)));

	}
}
