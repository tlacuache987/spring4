package org.certificatic.spring.aop.practica23.aspectjconfig.bean.api.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.certificatic.spring.aop.practica23.aspectjconfig.bean.api.IAdivinador;
import org.certificatic.spring.aop.practica23.aspectjconfig.bean.api.IPunishStrategy;
import org.certificatic.spring.aop.util.Color;
import org.certificatic.spring.aop.util.bean.api.IColorWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class Mago implements IAdivinador {

	@Autowired
	private IColorWriter colorWriter;

	@Autowired
	private IPunishStrategy punishStrategy;

	@Pointcut("execution( public void org.certificatic.spring.aop.practica23..IVoluntario.pensar*(String) ) && args(pensamiento)")
	private void cuandoUnVoluntarioPiensaEnAlgo(String pensamiento) {
	}

	@Pointcut("execution( public String org.certificatic.spring.aop.practica23..IVoluntario.getP*(boolean) ) && args(hacerTrampa)")
	private void cuandoUnVoluntarioQuiereHAcerTrampa(boolean hacerTrampa) {
	}

	@Override
	@Before("cuandoUnVoluntarioPiensaEnAlgo(pensamiento)")
	public void interceptarPensamiento(JoinPoint jp, String pensamiento) {
		print(colorWriter.getColoredMessage(Color.YELLOW,
				"[Mago] El voluntario se prepara para pensar en algo... [Pensará en: " + pensamiento + "]"));

		// print(Arrays.deepToString(jp.getArgs()));
	}

	@Around("cuandoUnVoluntarioQuiereHAcerTrampa(hacerTrampa)")
	public Object hacerMagia(ProceedingJoinPoint pjp, boolean hacerTrampa)
			throws Throwable {

		return punishStrategy.punish(pjp, hacerTrampa);
	}

	private void print(String mensaje) {
		log.info("{}", mensaje);
	}

}
