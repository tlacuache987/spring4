package org.certificatic.spring.aop.practica23.aspectjconfig.bean.api.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.certificatic.spring.aop.practica23.aspectjconfig.bean.api.IPunishStrategy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;

@Component
@Profile("change-parameters")
public class ChangeParametersPunishStrategy implements IPunishStrategy {

	@Override
	@SneakyThrows
	public Object punish(ProceedingJoinPoint pjp, boolean hacerTrampa) {
		Object o = null;

		if (!hacerTrampa)
			o = pjp.proceed();
		else
			o = pjp.proceed(new Object[] { false });

		return o;
	}

}
