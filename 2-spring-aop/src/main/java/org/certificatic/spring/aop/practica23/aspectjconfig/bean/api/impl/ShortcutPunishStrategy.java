package org.certificatic.spring.aop.practica23.aspectjconfig.bean.api.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.certificatic.spring.aop.practica23.aspectjconfig.bean.api.IPunishStrategy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;

@Component
@Profile("shortcut")
public class ShortcutPunishStrategy implements IPunishStrategy {

	@Override
	@SneakyThrows
	public Object punish(ProceedingJoinPoint pjp, boolean hacerTrampa) {
		if (hacerTrampa)
			return "No me he bañado en 5 dias";

		Object o = pjp.proceed();
		return o;
	}

}
