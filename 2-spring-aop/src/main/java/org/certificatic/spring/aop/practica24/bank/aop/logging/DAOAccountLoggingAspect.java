package org.certificatic.spring.aop.practica24.bank.aop.logging;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.certificatic.spring.aop.practica24.bank.app.model.Account;
import org.certificatic.spring.aop.util.Color;
import org.certificatic.spring.aop.util.bean.api.IColorWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component("daoAccountLoggingAspect")
@Slf4j
public class DAOAccountLoggingAspect implements Ordered {

	private @Getter int order = 3;

	@Autowired
	private IColorWriter colorWriter;

	@Pointcut(value = "org.certificatic.spring.aop.practica24.bank.aop.PointcutDefinition.dataAccessLayer() && "
			+ "args(xx, ..)")
	public void beforeDAOAccountMethodExecutionAccountPointcut(Account xx) {
	}

	@Before(value = "beforeDAOAccountMethodExecutionAccountPointcut(yy)")
	public void beforeDAOAccountMethodExecutionAccount(Account yy) {

		log.info("{}",
				colorWriter.getColoredMessage(Color.RED,
						String.format("Logging DAO Account access. Account: %s", yy.getAccountNumber())));
	}

	@Pointcut(value = "org.certificatic.spring.aop.practica24.bank.aop.PointcutDefinition.dataAccessLayer() && "
			+ "args(aa, ..)")
	public void beforeDAOAccountMethodExecutionLongPointcut(Long aa) {
	}

	@Before(value = "beforeDAOAccountMethodExecutionLongPointcut(bb)")
	public void beforeDAOAccountMethodExecutionLong(Long bb) {

		log.info("{}",
				colorWriter.getColoredMessage(Color.RED,
						String.format("Logging DAO Account access. Customer Id: %s", bb)));
	}

}
